-- Crear la base de datos
CREATE DATABASE tiendecitaGEF
charset utf8mb4
COLLATE utf8mb4_spanish2_ci;
USE tiendecitaGEF;

-- Tabla Articulos
CREATE TABLE Articulos (
    idArticulo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL
);

-- Tabla Tickets
CREATE TABLE Tickets (
    idTicket INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(10,2) NOT NULL,
    fecha DATE 
);

-- Tabla intermedia para relacionar Tickets con Articulos
CREATE TABLE TicketArticulos (
    idTicket INT,
    idArticulo INT,
    cantidad INT NOT NULL,
    PRIMARY KEY (idTicket, idArticulo),
    FOREIGN KEY (idTicket) REFERENCES Tickets(idTicket) ,
    FOREIGN KEY (idArticulo) REFERENCES Articulos(idArticulo) 
);
-- Desactivar temporalmente las restricciones de llave foránea
SET FOREIGN_KEY_CHECKS = 0;

-- Vaciar tablas en el orden correcto
TRUNCATE TABLE TicketArticulos;
TRUNCATE TABLE Tickets;
TRUNCATE TABLE Articulos;

-- Reiniciar los AUTO_INCREMENT (TRUNCATE ya lo hace, pero por si acaso)
ALTER TABLE Articulos AUTO_INCREMENT = 1;
ALTER TABLE Tickets AUTO_INCREMENT = 1;

-- Reactivar las restricciones de llave foránea
SET FOREIGN_KEY_CHECKS = 1;

