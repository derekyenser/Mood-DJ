BEGIN;

DROP TABLE IF EXISTS playlist;

CREATE TABLE playlist(
playlist_id VARCHAR(45),
playlist_name VARCHAR(45),
song_num INTEGER,
user_id INTEGER,
primary key(playlist_id),
CONSTRAINT playlist_song_num_FK FOREIGN KEY (song_num) REFERENCES Songs(song_num));


