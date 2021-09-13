CREATE TABLE movie (
    id int auto_increment primary key,
    name varchar(255) unique not null,
    release_date DATE not null
);
