-- Usar la base de datos recién creada

CREATE DATABASE IF NOT EXISTS loscardos;

USE loscardos;

-- Crear la tabla
CREATE TABLE role (
  role_id INT PRIMARY KEY AUTO_INCREMENT,
  roles VARCHAR(255)
);

-- INCORPORA LOS REGISTROS
INSERT INTO role (role_id, roles)
VALUES
(1, 'USER');
INSERT INTO role (role_id, roles)
VALUES
(2, 'ADMIN');

-- Crear la tabla
CREATE TABLE permissions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  permission_name VARCHAR(255)
);

-- INCORPORA LOS REGISTROS
INSERT INTO permissions (id, permission_name)
VALUES
(1, 'DELETE');
INSERT INTO permissions (id, permission_name)
VALUES
(2, 'CREATE');
INSERT INTO permissions (id, permission_name)
VALUES
(3, 'UPDATE');
INSERT INTO permissions (id, permission_name)
VALUES
(4, 'READ');
