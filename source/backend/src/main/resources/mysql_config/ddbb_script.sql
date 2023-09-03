DROP DATABASE IF EXISTS cvorotava_ddbb;
DROP USER IF EXISTS admin_cvorotava@localhost;
CREATE DATABASE cvorotava_ddbb DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
CREATE USER IF NOT EXISTS admin_cvorotava@localhost IDENTIFIED BY 'cvorotava1971';
GRANT ALL ON cvorotava_ddbb.* TO 'admin_cvorotava'@'localhost';
use cvorotava_ddbb;

CREATE TABLE player (
	id INT auto_increment,
    dni VARCHAR(10) NOT NULL,
    name VARCHAR(30) NOT NULL,
    surname1 VARCHAR(20) NOT NULL,
    surname2 VARCHAR(20),
    telephone INT,
    email VARCHAR(200),
    address VARCHAR(250),
    birthday DATE,
    category VARCHAR(20),
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE payment (
	id INT auto_increment,
    player_id VARCHAR(5) NOT NULL,
    month INT,
    concept VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE user (
	id INT auto_increment,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(username)
) ENGINE = INNODB;

CREATE TABLE role (
	id INT auto_increment,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY(ID),
    UNIQUE(role)
) ENGINE = INNODB;

CREATE TABLE user_roles (
	user_id INT NOT NULL,
    role_id INT NOT NULL
) ENGINE = INNODB;

INSERT INTO role VALUES(null, "admin");
INSERT INTO role VALUES(null, "manager");
INSERT INTO role VALUES(null, "player");

INSERT INTO user VALUES(null, "admin", "1234");

INSERT INTO user_roles VALUES(1, 1);

INSERT INTO player VALUES(null, "43383780F", "Guillermo", "Sicilia", "Hernández", 616298870, "siciliahernandezguillermo@gmail.com", "C/El Toscal nº37", "2000-05-16", "SEN MAS");
INSERT INTO player VALUES(null, "54115342E", "Raquel", "Pérez", "García", 687475475, "raquelita2909@gmail.com", "C/El millo nº14", "2000-09-29", "SEN FEM");
INSERT INTO player VALUES(null, "12345678X", "Zaira", "Ocampos", "Lorenzo", 62314123, "zairaoc@gmail.com", "C/No se, por ahi", "2000-12-24", "SEN FEM");
INSERT INTO player VALUES(null, "18712678X", "Raúl", "Moleiro", "Regalado", 61745541, "raulregalado@gmail.com", "C/El Toscal nº56", "2000-09-02", "SEN MAS");
INSERT INTO player VALUES(null, "32445678X", "Luisa", "Sicilia", "Hernández", 78612534, "luisasic@gmail.com", "C/El Toscal nº37", "2007-01-22", "JUV FEM");