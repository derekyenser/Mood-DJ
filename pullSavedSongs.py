import os
import json
import spotipy
import pandas as pd
import tkinter as tk
import spotipy.util as util

def getLikedSongs():
    global sp, songs
    count = 0

    results = sp.current_user_saved_tracks(limit=20, offset=0)
    tracks = results
    for i in tracks['items']:
        tName = i['track']['name']
        aName = i['track']['artists'][0]['name']
        feat = sp.audio_features(i['track']['id'])[0]
        feat.update([('track', tName) , ('artist', aName)])
        songs.append(feat)
        count += 1
    while tracks['next']:
        tracks = sp.next(tracks)
        for i in tracks['items']:
            tName = i['track']['name']
            aName = i['track']['artists'][0]['name']
            feat = sp.audio_features(i['track']['id'])[0]
            feat.update([('track', tName) , ('artist', aName)])
            songs.append(feat)
            count += 1
    print('done')

def login():   # Get username form tkinter box
    global root, entry1
    name = entry1.get()
    root.destroy()
    authenticate(name)
    # user id: p4gp8n5f94zulnhpvesutmgel
    # monkeyballer98

def authenticate(username):    # Request account access and handle token athentication
    global sp
    try:
        token = util.prompt_for_user_token(username, 'user-library-read', 
        client_id='be3ff50517384a24907a8eed8c80bca1', 
        client_secret='7e38a4328695403b902462610db339c6',
        redirect_uri='http://google.com/')
    except:
        os.remove(f".cache-{username}")
    #Create spotify object
    print(token)
    sp = spotipy.Spotify(auth=token)
    user = sp.current_user()
    print(json.dumps(user, sort_keys=True, indent=4))
    displayName = user['display_name']

def displayWindow():     # Display the login tkinter box
    global root, entry1
    canvas1 = tk.Canvas(root, width = 400, height = 300,  relief = 'raised')
    canvas1.pack()
    label1 = tk.Label(root, text='Spotify Account Access')
    label1.config(font=('helvetica', 14))
    canvas1.create_window(200, 25, window=label1)
    label2 = tk.Label(root, text='Enter your Spotify user ID:')
    label2.config(font=('helvetica', 10))
    canvas1.create_window(200, 100, window=label2)
    entry1 = tk.Entry (root) 
    canvas1.create_window(200, 140, window=entry1)
    button1 = tk.Button(root, text='OK', command=login, bg='green', fg='white', font=('helvetica', 9, 'bold'))
    canvas1.create_window(200, 180, window=button1)
    root.mainloop()

sp, entry1 = None, None
songs = []
root = tk.Tk()

displayWindow()
getLikedSongs()
frame = pd.DataFrame.from_dict(songs)
export_csv = frame.to_csv (r'Norm_Favs.csv', index = None, header=True)
