CREATE DATABASE IF NOT EXISTS turnos;
USE turnos;

-- Tabla: Usuarios
CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    email VARCHAR(255),
    direccion VARCHAR(255),
    dni INT NOT NULL UNIQUE,
    estado BOOLEAN,
    fechaAlta DATETIME
);

-- Tabla: Clientes
CREATE TABLE clientes (
    idCliente INT PRIMARY KEY,
    nroCliente INT,
    FOREIGN KEY (idCliente) REFERENCES Usuarios(idUsuario)
);

-- Tabla: Empleados
CREATE TABLE empleados (
    idEmpleado INT PRIMARY KEY,
    cuil INT,
    matricula VARCHAR(255),
    FOREIGN KEY (idEmpleado) REFERENCES Usuarios(idUsuario)
);

-- Tabla: Especialidades
CREATE TABLE especialidades (
    idEspecialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Tabla: DiasDeAtencion
CREATE TABLE diasDeAtencion (
    idDiasDeAtencion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Tabla: Servicios
CREATE TABLE Servicios (
    idServicio INT AUTO_INCREMENT PRIMARY KEY,
    nombreServicio VARCHAR(255),
    duracion INT
);

-- Tabla: Establecimientos
CREATE TABLE establecimientos (
    idestablecimiento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    cuit VARCHAR(255),
    direccion VARCHAR(255),
    descripcion VARCHAR(255)
);

-- Tabla: Sucursales
CREATE TABLE sucursales (
    idsucursal INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    horarioApertura TIME,
    horarioCierre TIME,
    espacio INT,
    idestablecimiento INT,
    FOREIGN KEY (idestablecimiento) REFERENCES establecimientos(idestablecimiento)
);

-- Tabla intermedia: especialidad_sucursal
CREATE TABLE especialidad_sucursal (
    sucursal_id INT,
    especialidad_id INT,
    PRIMARY KEY (sucursal_id, especialidad_id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursales(idsucursal),
    FOREIGN KEY (especialidad_id) REFERENCES especialidades(idEspecialidad)
);

-- Tabla intermedia: diasDeAtencion_sucursal
CREATE TABLE diasDeAtencion_sucursal (
    sucursal_id INT,
    diasDeAtencion_id INT,
    PRIMARY KEY (sucursal_id, diasDeAtencion_id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursales(idsucursal),
    FOREIGN KEY (diasDeAtencion_id) REFERENCES diasDeAtencion(idDiasDeAtencion)
);

-- Tabla intermedia: empleado_especialidad
CREATE TABLE empleado_especialidad (
    empleado_id INT,
    especialidad_id INT,
    PRIMARY KEY (empleado_id, especialidad_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(idEmpleado),
    FOREIGN KEY (especialidad_id) REFERENCES especialidades(idEspecialidad)
);

-- Tabla: Turnos
CREATE TABLE Turnos (
    idTurno INT AUTO_INCREMENT PRIMARY KEY,
    fechaHora DATETIME,
    estadoActivo BOOLEAN,
    codigo VARCHAR(255),
    cliente_id INT NOT NULL,
    sucursal_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(idCliente),
    FOREIGN KEY (sucursal_id) REFERENCES sucursales(idsucursal)
);
