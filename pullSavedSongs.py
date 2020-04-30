# Malcolm Sykes
# 3/29/2020
# Project: Mood Dj
# This program is using the Spotify API via a python library called Spotipy.
# It requests access to a user's Spotify account and grabs the songs from their
# liked songs playlist along with the song's attributes. It then connects to a 
# database where it stores the information in.
#NOTICE: Anywhere you see "sys.stdout.write", this is for piping output to the parent Java program that calls this script

from os import remove
from sys import stdout, stdin
import spotipy
from pandas import DataFrame
import mysql.connector

sp = None  # Global variables for sp: Spotify Token for login and getSavedSongs  entry1: GUI box input
#root = tk.Tk() # GUI box pointer
username = ""

def authenticate():  # Request account access and handle token athentication (Had to modify Spotipy library with another GUI box)
    global sp, username
    try:
        # user-library-read is the type of access we are requesting
        # client_id and client_secret come from app created in Spotify developer account
        # redirect_uri is the place it takes you to login
        token = spotipy.util.prompt_for_user_token(username, 'user-library-read', 
        client_id='be3ff50517384a24907a8eed8c80bca1', 
        client_secret='7e38a4328695403b902462610db339c6',
        redirect_uri='http://google.com/')
    except:
        remove(f".cache-{username}")
    #Create spotify object
    sp = spotipy.Spotify(auth=token)
    user = sp.current_user()
    displayName = user['display_name']

def getLikedSongs():  # Get all the songs in a user's Liked Songs playlist including artist name and all Spotify attributes
    stdout.write("Getting songs from Spotify...\n")
    stdout.flush()
    global sp
    songs = []
    count = 0 # To track progress
    punc = "!\"#%'()*+,/:;<=>[\]^_`{|}~“”" # All punctuation we want to get rid of for sql queries to work
    table = str.maketrans('', '', punc)

    results = sp.current_user_saved_tracks(limit=20, offset=0) # Gets the first 20(max) songs 
    tracks = results
    for i in tracks['items']: # Loops through the 20 songs we just got
        tName = i['track']['name'].translate(table) # Takes unwanted punctuation out of the track name
        aName = i['track']['artists'][0]['name'].translate(table) # Takes unwanted punctuation out of the author name
        feat = sp.audio_features(i['track']['id'])[0] # Gets the Spotify audio features(attributes) for the song
        feat.update([('track', tName) , ('artist', aName)])
        songs.append(feat) # Adds track name, artist name, and song attributes to a data structure(in this case a list)
        count += 1
    stdout.write(str(count) + '\n')
    stdout.flush()
    while tracks['next']: # Continue to grab the next 20 songs until there are no more (and/or until it reaches a max) and repeats above steps
        tracks = sp.next(tracks)
        for i in tracks['items']:
            tName = i['track']['name'].translate(table)
            aName = i['track']['artists'][0]['name'].translate(table)
            feat = sp.audio_features(i['track']['id'])[0]
            feat.update([('track', tName) , ('artist', aName)])
            songs.append(feat)
            count += 1
        stdout.write(str(count) + '\n')
        stdout.flush()
    stdout.write("Found " + str(count) + " songs\n")
    stdout.flush()
    return songs

def makeConection(credentials): # Connect to database
    return mysql.connector.connect(**credentials)

def addToDB(cnx, info): #Adds all songs, attributes, and artists to the appropriate tables in the database
    stdout.write("Adding to database...\n")
    stdout.flush()
    frame = DataFrame.from_dict(info) # Convert the list data structure from getLikedSongs to a dataframe for ease of use
    count = 0 # Track progress
    for index, song in frame.iterrows():
        insertArtist(song["artist"], cnx) # Put artist in the artists table
        songTuple = (song["id"], song["track"]) # Info for song table
        insertSong(songTuple, song["artist"], cnx) # Put song in songs table
        insertSHA(song["artist"], song["id"], cnx) # Link the artist and song in database
        # Convert all 0's to major and 1's to minor to match database
        if song['mode'] == 0:
            song['mode'] = 'major'
        elif song['mode'] == 1:
            song['mode'] = 'minor'
        # Get all attributes for the song in one place
        attributeTuple = (round(song["acousticness"], 9), round(song["danceability"], 9), song["duration_ms"],
                    round(song["energy"], 9), song["instrumentalness"], song["key"], round(song["liveness"], 9),
                    round(song["loudness"], 9), song["mode"], round(song["speechiness"], 9), round(song["tempo"], 4),
                    round(song["valence"], 9))
        insertAttributes(attributeTuple, song["id"], cnx) # Put song attributes in the tracks table

        # Keep track of progress
        count += 1
        if count % 50 == 0:
            stdout.write(str(count) + '\n')
            stdout.flush()

def insertArtist(value, cnx):  # Inserts the artist info into the artists table
    try:
        insert_query = "INSERT IGNORE INTO artists (artist_name) VALUES ('" + value + "');" # SQL insert command
        cursor = cnx.cursor()
        cursor.execute(insert_query) # Run the command on database
        cnx.commit()
        cursor.close()

    except mysql.connector.Error as error: # If we get an error other than "Duplicate entry" print it (The same artist name is expected multiple times)
        err = ("{}".format(error)).split()
        if(err[1] != 'Duplicate'):
            print("Failed to insert record into artist table {}".format(error))

def insertSong(values, artist, cnx):  # Inserts the artist info into the artists table
    try:
        exist, num = getSongNum(values[0], cnx) # Check if the song is in the database
        gNum = getGenreNum(cnx) # Get a genre_num
        if not exist: # If song does not exist in database, insert it
            lst = []
            lst.append(gNum)
            lst.append("?")
            lst.append(artist)
            lst = tuple(lst) # lst now has all info for the song entry in one place
            insert_query = "INSERT INTO Genre (genre_num, song_genre, artist_name) VALUES (%s, %s, %s);" # SQL insert command
            cursor = cnx.cursor()
            cursor.execute(insert_query, lst) # Run the command on database
            cnx.commit()
            cursor.close()

            lst = []
            lst.append(num)
            lst.extend(values)
            lst.append(gNum)
            lst = tuple(lst) # lst now has all info for the song entry in one place
            insert_query = "INSERT INTO Songs (song_num, track_id, track_name, genre_num) VALUES (%s, %s, %s, %s);" # SQL insert command
            cursor = cnx.cursor()
            cursor.execute(insert_query, lst) # Run the command on database
            cnx.commit()
            cursor.close()
        else:
            cursor = cnx.cursor(buffered=True)
            sql_select_query = "select genre_num from Songs where track_id =  '" + values[0] + "';" # SQL command to get a new genre_num
            cursor.execute(sql_select_query) # Run the command
            record = cursor.fetchall() # Get all results of the query (Should only be one)
            check = 0
            for row in record:
                check = row[0]
            if check == None:
                lst = []
                lst.append(gNum)
                lst.append("?")
                lst.append(artist)
                lst = tuple(lst) # lst now has all info for the song entry in one place
                insert_query = "INSERT INTO Genre (genre_num, song_genre, artist_name) VALUES (%s, %s, %s);"# SQL insert command
                cursor = cnx.cursor()
                cursor.execute(insert_query, lst) # Run the command on database
                cnx.commit()
                cursor.close()

                insert_query = "UPDATE Songs SET genre_num = %s WHERE track_id = %s;" # SQL insert command
                cursor = cnx.cursor()
                cursor.execute(insert_query, (gNum, values[0])) # Run the command on database
                cnx.commit()
                cursor.close()
            else:
                print('Duplicate:', values[1])

        insertPlaylist(cnx, num)

    except mysql.connector.Error as error: # If we get an error other than "Duplicate entry" print it (The same artist name is expected multiple times)
        print("Failed to insert record into Songs table {}".format(error))

def getGenreNum(cnx): # Get a genre_num for the DB to use
    try:
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select MAX(genre_num) from Genre;" # SQL command to get a new genre_num
        cursor.execute(sql_select_query) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            return row[0]+1
    except mysql.connector.Error as error:
        print("Failed to get record from MySQL Genre table: {}".format(error))

def getSongNum(track, cnx):  # Returns the song_num if the song is in the databse, otherwise returns an ID number to use
    try:
        # If the song already exists, return the song_num
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select song_num from Songs where track_id = '" + track + "';" # SQL command to get the song_num for a song
        cursor.execute(sql_select_query) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            return True, row[0]

        # If the song does not exist, return a song_num for it
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select MAX(song_num) from Songs;" # SQL command to get a new song_num
        cursor.execute(sql_select_query) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            return False, row[0]+1
    except mysql.connector.Error as error:
        print("Failed to get record from MySQL Songs table: {}".format(error))

def insertSHA(artist, track, cnx):
    exists, num = getSongNum(track, cnx)
    if exists:
        try:
            values = (artist, num)
            insert_query = "INSERT IGNORE INTO song_have_many_artist (artist_name, song_num) VALUES (%s, %s);" # SQL insert command
            cursor = cnx.cursor()
            cursor.execute(insert_query, values) # Run the command on database
            cnx.commit()
            cursor.close()
        except mysql.connector.Error as error: # If we get an error other than "Duplicate entry" print it (The same artist name is expected multiple times)
            err = ("{}".format(error)).split()
            if(err[1] != 'Duplicate'):
                print("Failed to insert record into SHA table {}".format(error))
    else:
        print("Couldn't find the track " + str(track))

def insertAttributes(values, track, cnx):  # Inserts the attributes into the attributes table
    exists, sNum = getSongNum(track, cnx)
    if exists:
        try:
            # Get a new attribute_num 
            cursor = cnx.cursor(buffered=True)
            sql_select_query = "select MAX(attribute_num) from attributes;" # SQL command to get a new attribute_num
            cursor.execute(sql_select_query) # Run the command
            record = cursor.fetchall() # Get all results of the query (Should only be one)
            attNum = None
            for row in record:
                attNum = row[0]+1

            # Get the genre_num 
            cursor = cnx.cursor(buffered=True)
            sql_select_query = "select genre_num from Songs where track_id = '" + track + "';" # SQL command to get the genre_num for a song
            cursor.execute(sql_select_query) # Run the command
            record = cursor.fetchall() # Get all results of the query (Should only be one)
            gNum = None
            for row in record:
                gNum = row[0]

            lst = []
            lst.append(attNum)
            lst.extend(values)
            lst.append(sNum)
            lst.append(gNum)
            lst = tuple(lst)
            # SQL insert query (%s is a place holder for a value to be specified later)
            insert_query = """INSERT INTO attributes (attribute_num, accousticness, danceability, duration_ms, energy, 
                            instrumentalness, key_s, liveness, loudness, mode_A, speechiness, tempo,
                            valence, song_num, genre_num) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);"""
            cursor = cnx.cursor()
            cursor.execute(insert_query, lst) # Run the command and fill in %s with actual values
            cnx.commit()
            cursor.close()

        except mysql.connector.Error as error:
            err = ("{}".format(error)).split()
            if(err[1] != 'Duplicate'):
                print("Failed to insert record into attributes table {}".format(error))
    else:
        print("Coundn't add " + str(track))

def insertPlaylist(cnx, sNum): # Insert songs into the playlist table under the users id so we know which songs they like
    global username
    exists = False
    try:
        irr, userId = checkUser(cnx)
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select song_num from playlist where song_num = %s AND user_id = %s;" # SQL command to get the song_num for a song
        cursor.execute(sql_select_query, (sNum, userId)) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            exists = True

        if not exists:
            cursor = cnx.cursor(buffered=True)
            sql_select_query = "select COUNT(playlist_id) from playlist;" # SQL command to get a new song_num
            cursor.execute(sql_select_query) # Run the command
            record = cursor.fetchall() # Get all results of the query (Should only be one)
            pId = 0
            for row in record:
                pId = row[0]

            insert_query = "INSERT INTO playlist VALUES (%s, 'Favorites', %s, %s);" # SQL insert command
            cursor = cnx.cursor()
            cursor.execute(insert_query, (pId, sNum, userId)) # Run the command on database
            cnx.commit()
            cursor.close()

    except mysql.connector.Error as error: # If we get an error other than "Duplicate entry" print it (The same artist name is expected multiple times)
        err = ("{}".format(error)).split()
        if(err[1] != 'Duplicate'):
            print("Failed to insert record into playlist table {}".format(error))

def addUser(cnx): # Add a user to our database
    global username
    exists, userId = checkUser(cnx)
    if not exists:
        try:
            insert_query = "INSERT INTO UserS (Users_id, user_name) VALUES (%s, %s);" # SQL insert command (will add password and email later)
            cursor = cnx.cursor()
            cursor.execute(insert_query, (userId, username)) # Run the command on database
            cnx.commit()
            cursor.close()

        except mysql.connector.Error as error:
            print("Failed to insert record into UserS table: {}".format(error))

def checkUser(cnx): # Check if a user is already in our database. If not, generate a new Id
    global username
    try:
        # If the user already exists, return the Users_id
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select Users_id from UserS where user_name = '" + username + "';" # SQL command to get the song_num for a song
        cursor.execute(sql_select_query) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            return True, row[0]

        # If the suser does not exist, return a user_id for it
        cursor = cnx.cursor(buffered=True)
        sql_select_query = "select MAX(Users_id) from UserS;" # SQL command to get a new song_num
        cursor.execute(sql_select_query) # Run the command
        record = cursor.fetchall() # Get all results of the query (Should only be one)
        for row in record:
            if row[0] == None or row[0] == 'null':
                return False, 0
            else:
                return False, row[0]+1
    except mysql.connector.Error as error:
        print("Failed to get record from MySQL UserS table: {}".format(error))

def main():
    global username
    creds = []
    s = stdin.readline().strip()
    while s not in ['Done','Never']:
        creds.append(s)
        s = stdin.readline().strip()

    if s == 'Done': # If you used the getDBCreds method in java to use the DB on your system (recommended)
        config = {
        'user': creds[0],
        'password': creds[1],
        'host': creds[2], # IP address unless database is on local machine, then 127.0.0.1
        'database': creds[3], # database name
        'raise_on_warnings': True
        }
    else:
        # My database credentials required for connection
        config = {
        'user': 'sql9334219',
        'password': 'FrqdgTsjLk',
        'host': 'sql9.freemysqlhosting.net', # IP address unless database is on local machine, then 127.0.0.1
        'database': 'sql9334219', # database name
        'raise_on_warnings': True
        }

    stdout.write("CheckPoint...\n")
    stdout.flush()

    name = []
    st = stdin.readline().strip()
    while st not in ['Done2']:
        name.append(st)
        st = stdin.readline().strip()
    username = name[0]
    stdout.write(str(username) + '\n')
    stdout.flush()

    stdout.write("Starting...\n")
    stdout.flush()

    authenticate()
    #displayWindow()
    songInfo = getLikedSongs()
    connection = makeConection(config) # Make connection to database
    addUser(connection)
    addToDB(connection, songInfo)

    connection.close()
    stdout.write('Done\n')
    stdout.flush()
    stdout.write("quit")

if __name__ == "__main__":
    main()