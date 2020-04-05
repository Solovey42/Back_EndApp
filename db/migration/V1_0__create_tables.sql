CREATE TABLE users(
login VARCHAR(10) PRIMARY KEY,
hash VARCHAR(255),
salt VARCHAR(255)
);

CREATE TABLE resource(
id INT PRIMARY KEY AUTO_INCREMENT,
res VARCHAR(255),
role VARCHAR(10),
user VARCHAR(10)  REFERENCES Users (login)
);

CREATE TABLE session(
id INT PRIMARY KEY AUTO_INCREMENT,
user VARCHAR(10) REFERENCES users (login),
res VARCHAR(255),
ds VARCHAR(10),
de VARCHAR(10),
vol INT
);