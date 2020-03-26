BEGIN;

DROP TABLE IF EXISTS song_have_many_artist;

CREATE TABLE song_have_many_artist (
artist_name VARCHAR(45) REFERENCES Artists(artist_name),
song_num INTEGER REFERENCES Songs(song_num),
PRIMARY KEY (artist_name, song_num));