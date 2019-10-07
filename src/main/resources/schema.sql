CREATE TABLE videos
(
 url varchar(150) NOT NULL,
 id serial NOT NULL,
 description varchar(100) DEFAULT NULL,
 title varchar(100) DEFAULT NULL,
 PRIMARY KEY (id)
);