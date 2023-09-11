CREATE TABLE role (
	id INT auto_increment,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE player (
	id INT auto_increment,
    dni varchar(10) not null,
    name varchar(50) not null,
    surname1 varchar(50) not null,
    surname2 varchar(50) not null,
    telephone int,
    address varchar(255),
    birthday date not null,
    category varchar(50),
    primary key(id)
);

CREATE TABLE payment (
	id int auto_increment,
    quantity float not null,
    month int not null,
    concept varchar(100) not null,
    primary key(id)
);