DROP DATABASE IF EXISTS cvorotava_ddbb;
CREATE DATABASE cvorotava_ddbb;

CREATE USER 'admin_cvorotava'@'localhost' IDENTIFIED BY 'cvorotava1971';
GRANT ALL ON cvorotava_ddbb.* TO 'admin_cvorotava'@'localhost';
FLUSH PRIVILEGES;