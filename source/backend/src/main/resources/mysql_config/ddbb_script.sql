DROP DATABASE IF EXISTS cvorotava_ddbb;
DROP USER IF EXISTS admin_cvorotava@localhost;
CREATE DATABASE cvorotava_ddbb DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
CREATE USER IF NOT EXISTS admin_cvorotava@localhost IDENTIFIED BY 'cvorotava1971';
GRANT ALL ON cvorotava_ddbb.* TO 'admin_cvorotava'@'localhost';
use cvorotava_ddbb;

CREATE TABLE player (
	id INT auto_increment,
    name VARCHAR(30) NOT NULL,
    surname1 VARCHAR(20) NOT NULL,
    surname2 VARCHAR(20) NOT NULL,
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

INSERT INTO player VALUES(null, "Guillermo", "Sicilia", "Hernández", null, null, null, null, null);
INSERT INTO player VALUES(null, "Raquel", "Pérez", "García", null, null, null, null, null);
INSERT INTO player VALUES(null, "Zaira", "Ocampos", "Lorenzo", null, null, null, null, null);