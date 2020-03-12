BEGIN;

DROP TABLE IF EXISTS Users;

CREATE TABLE UserS(
Users_id INTEGER,
user_name VARCHAR(45),
User_email VARCHAR(45),
User_password VARCHAR(45),
primary key(Users_id));

