INSERT INTO role (role) VALUES('admin');
INSERT INTO role (role) VALUES ('manager');
INSERT INTO role (role) VALUES('player');

INSERT INTO user (username, password) VALUES('admin', '1234');

INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category) VALUES('43383780F', 'Guillermo', 'Sicilia', 'Hernández', 616298870, 'siciliahernandezguillermo@gmail.com', 'C/El Toscal nº37', '2000-05-16', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category) VALUES('54115342E', 'Raquel', 'Pérez', 'García', 687475475, 'raquelita2909@gmail.com', 'C/El millo nº14', '2000-09-29', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category) VALUES('12345678X', 'Zaira', 'Ocampos', 'Lorenzo', 62314123, 'zairaoc@gmail.com', 'C/No se, por ahi', '2000-12-24', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category) VALUES('18712678X', 'Raúl', 'Moleiro', 'Regalado', 61745541, 'raulregalado@gmail.com', 'C/El Toscal nº56', '2000-09-02', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category) VALUES('32445678X', 'Luisa', 'Sicilia', 'Hernández', 78612534, 'luisasic@gmail.com', 'C/El Toscal nº37', '2007-01-22', 'JUV FEM');

INSERT INTO payment (quantity, month, year, concept) VALUES(25, 9, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(65, 9, 2023, 'Seguro');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 10, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 12, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 11, 2023, 'Mensualidad');