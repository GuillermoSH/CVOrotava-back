-- Inserts de los roles
INSERT INTO role (role) VALUES('admin');
INSERT INTO role (role) VALUES ('manager');
INSERT INTO role (role) VALUES('player');

-- Inserts de los usuarios
INSERT INTO user (username, password) VALUES('admin', '1234');

-- Inserts de los jugadores
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES('43383780F', 'Guillermo', 'Sicilia', 'Hernández', 647283659, 'siciliahernandezguillermo@gmail.com', 'C/El Toscal nº37', '2000-05-16', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES('54115342E', 'Raquel', 'Pérez', 'García', 687475485, 'raquelita2909@gmail.com', 'C/El millo nº14', '2000-09-29', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES('12345678X', 'Zaira', 'Ocampos', 'Lorenzo', 623141937, 'zairaoc@gmail.com', 'C/No se, por ahi', '2000-12-24', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES('18712678X', 'Raúl', 'Moleiro', 'Regalado', 637455419, 'raulregalado@gmail.com', 'C/El Toscal nº56', '2000-09-02', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES('32445678X', 'Luisa', 'Sicilia', 'Hernández', 666125342, 'luisasic@gmail.com', 'C/El Toscal nº37', '2007-01-22', 'JUV FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('44444444D', 'Robert', 'Garcia', 'Martinez', 633844212, 'robert.garcia@example.com', '1011 Pine St', '1993-09-10', 'CAD FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('55555555E', 'Sophia', 'Lopez', 'Rodriguez', 622394837, 'sophia.lopez@example.com', '222 Cedar St', '1994-11-25', 'INF FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('66666666F', 'Michael', 'Fernandez', 'Gomez', 677213487, 'michael.fernandez@example.com', '333 Maple St', '1996-04-05', 'JUN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('77777777G', 'Olivia', 'Perez', 'Sanchez', 688134875, 'olivia.perez@example.com', '444 Birch St', '1997-08-15', 'BEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('88888888H', 'William', 'Ramirez', 'Lopez', 699238576, 'william.ramirez@example.com', '555 Oak St', '1998-12-30', 'ALE FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('99999999I', 'Emma', 'Torres', 'Hernandez', 611837495, 'emma.torres@example.com', '666 Elm St', '1999-06-22', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('10101010J', 'Liam', 'Gonzalez', 'Alvarez', 677489234, 'liam.gonzalez@example.com', '777 Cedar St', '2000-02-14', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('11111111K', 'Ava', 'Martinez', 'Fernandez', 699384718, 'ava.martinez@example.com', '888 Pine St', '2001-07-07', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('12121212L', 'Noah', 'Sanchez', 'Perez', 611294756, 'noah.sanchez@example.com', '999 Birch St', '2002-03-01', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('13131313M', 'Isabella', 'Lopez', 'Ramirez', 622497315, 'isabella.lopez@example.com', '1010 Maple St', '2003-09-19', 'SEN FEM');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('14141414N', 'James', 'Gomez', 'Hernandez', 633174859, 'james.gomez@example.com', '2020 Oak St', '2004-04-12', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('15151515O', 'Charlotte', 'Alvarez', 'Gonzalez', 644285736, 'charlotte.alvarez@example.com', '3030 Pine St', '2005-08-28', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('16161616P', 'Benjamin', 'Fernandez', 'Sanchez', 655193748, 'benjamin.fernandez@example.com', '4040 Elm St', '2006-12-07', 'CAD MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('17171717Q', 'Amelia', 'Perez', 'Martinez', 666482739, 'amelia.perez@example.com', '5050 Cedar St', '2007-03-22', 'ALE MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('18181818R', 'Mason', 'Ramirez', 'Gomez', 677528349, 'mason.ramirez@example.com', '6060 Birch St', '2008-07-15', 'INF MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('19191919S', 'Harper', 'Gonzalez', 'Lopez', 688374921, 'harper.gonzalez@example.com', '7070 Maple St', '2009-11-09', 'BEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('20202020T', 'Elijah', 'Martinez', 'Alvarez', 699482731, 'elijah.martinez@example.com', '8080 Oak St', '2010-02-18', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('21212121U', 'Avery', 'Sanchez', 'Fernandez', 611594827, 'avery.sanchez@example.com', '9090 Pine St', '2011-06-30', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('22222222V', 'Liam', 'Perez', 'Gomez', 622497813, 'liam.perez@example.com', '10101 Elm St', '2012-10-14', 'JUN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('23232323W', 'Mia', 'Ramirez', 'Alvarez', 633193827, 'mia.ramirez@example.com', '11111 Cedar St', '2013-04-26', 'JUV MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('24242424X', 'Noah', 'Gonzalez', 'Martinez', 644284739, 'noah.gonzalez@example.com', '12121 Maple St', '2014-09-08', 'SEN MAS');
INSERT INTO player (dni, name, surname1, surname2, telephone, email, address, birthday, category)
VALUES ('25252525Y', 'Olivia', 'Lopez', 'Sanchez', 655193847, 'olivia.lopez@example.com', '13131 Oak St', '2015-12-22', 'SEN MAS');

-- Inserts de las equipaciones
INSERT INTO equipment (size, type, color) VALUES ('SIZE_10_12', 'WARMING_SHIRT', 'Rojo');
INSERT INTO equipment (size, type, color) VALUES ('SIZE_M', 'COMPETITION_JACKET', 'Negro');
INSERT INTO equipment (size, type, color) VALUES ('SIZE_L', 'TRAINING_SHIRT', 'Rojo');
INSERT INTO equipment (size, type, color) VALUES ('SIZE_S', 'COMPETITION_SHORTS', 'Negro');
INSERT INTO equipment (size, type, color) VALUES ('SIZE_XL', 'COACH_POLO', 'Rojo');

-- Inserts de los pagos
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 9, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(65, 9, 2023, 'Seguro');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 10, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 12, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 11, 2023, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 9, 2024, 'Mensualidad');
INSERT INTO payment (quantity, month, year, concept) VALUES(65, 9, 2024, 'Seguro');
INSERT INTO payment (quantity, month, year, concept) VALUES(25, 10, 2024, 'Mensualidad');