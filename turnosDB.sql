CREATE DATABASE turnos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE turnos;

CREATE TABLE Usuarios (
  idUsuario    INT            NOT NULL AUTO_INCREMENT,
  nombre       VARCHAR(255),
  apellido     VARCHAR(255),
  email        VARCHAR(255),
  direccion    VARCHAR(255),
  dni          INT            NOT NULL,
  estado       BOOLEAN,
  fechaAlta    DATETIME,
  PRIMARY KEY (idUsuario),
  UNIQUE KEY uk_usuario_dni   (dni),
  UNIQUE KEY uk_usuario_email (email)
) ENGINE=InnoDB;

CREATE TABLE clientes (
  idUsuario INT NOT NULL,
  PRIMARY KEY (idUsuario),
  CONSTRAINT fk_clientes_usuario FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
) ENGINE=InnoDB;

CREATE TABLE empleados (
  idUsuario  INT            NOT NULL,
  cuil       VARCHAR(255),
  matricula  VARCHAR(255),
  PRIMARY KEY (idUsuario),
  CONSTRAINT fk_empleados_usuario FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
) ENGINE=InnoDB;

CREATE TABLE especialidades (
  idEspecialidad INT         NOT NULL AUTO_INCREMENT,
  nombre         VARCHAR(255),
  PRIMARY KEY (idEspecialidad)
) ENGINE=InnoDB;

CREATE TABLE establecimientos (
  idestablecimiento INT       NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(255),
  cuit              VARCHAR(255),
  direccion         VARCHAR(255),
  descripcion       VARCHAR(255),
  PRIMARY KEY (idestablecimiento)
) ENGINE=InnoDB;

CREATE TABLE sucursales (
  idsucursal        INT       NOT NULL AUTO_INCREMENT,
  direccion         VARCHAR(255),
  telefono          VARCHAR(255),
  horarioApertura   TIME,
  horarioCierre     TIME,
  idestablecimiento INT,
  PRIMARY KEY (idsucursal),
  CONSTRAINT fk_sucursales_establecimiento FOREIGN KEY (idestablecimiento) REFERENCES establecimientos(idestablecimiento)
) ENGINE=InnoDB;

CREATE TABLE puntosDeAtencion (
  idpuntoDeAtencion     INT NOT NULL AUTO_INCREMENT,
  numeroPuntoDeAtencion INT,
  sucursal_id            INT,
  empleado_id           INT,
  PRIMARY KEY (idpuntoDeAtencion),
  CONSTRAINT fk_puntos_sucursal FOREIGN KEY (sucursal_id) REFERENCES sucursales(idsucursal)
) ENGINE=InnoDB;

CREATE TABLE Servicios (
  idServicio    INT       NOT NULL AUTO_INCREMENT,
  nombreServicio VARCHAR(255),
  duracion      INT,
  PRIMARY KEY (idServicio)
) ENGINE=InnoDB;

CREATE TABLE Turnos (
  idTurno       INT       NOT NULL AUTO_INCREMENT,
  fechaHora     DATETIME,
  estadoActivo  BOOLEAN,
  codigo        VARCHAR(255),
  PRIMARY KEY (idTurno)
) ENGINE=InnoDB;

CREATE TABLE empleado_especialidad (
  empleado_id     INT NOT NULL,
  especialidad_id INT NOT NULL,
  PRIMARY KEY (empleado_id, especialidad_id),
  CONSTRAINT fk_emp_esp_emp FOREIGN KEY (empleado_id) REFERENCES empleados(idUsuario),
  CONSTRAINT fk_emp_esp_esp FOREIGN KEY (especialidad_id) REFERENCES especialidades(idEspecialidad)
) ENGINE=InnoDB;

CREATE TABLE especialidad_sucursal (
  sucursal_id      INT NOT NULL,
  especialidad_id  INT NOT NULL,
  PRIMARY KEY (sucursal_id, especialidad_id),
  CONSTRAINT fk_esp_suc_suc FOREIGN KEY (sucursal_id) REFERENCES sucursales(idsucursal),
  CONSTRAINT fk_esp_suc_esp FOREIGN KEY (especialidad_id) REFERENCES especialidades(idEspecialidad)
) ENGINE=InnoDB;

CREATE TABLE turno_servicio (
  turno_id    INT NOT NULL,
  servicio_id INT NOT NULL,
  PRIMARY KEY (turno_id, servicio_id),
  CONSTRAINT fk_turno_serv_turno FOREIGN KEY (turno_id) REFERENCES Turnos(idTurno),
  CONSTRAINT fk_turno_serv_serv FOREIGN KEY (servicio_id) REFERENCES Servicios(idServicio)
) ENGINE=InnoDB;

CREATE TABLE turno_cliente (
  turno_id   INT NOT NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (turno_id, cliente_id),
  CONSTRAINT fk_turno_cli_turno FOREIGN KEY (turno_id) REFERENCES Turnos(idTurno),
  CONSTRAINT fk_turno_cli_cli FOREIGN KEY (cliente_id) REFERENCES clientes(idUsuario)
) ENGINE=InnoDB;

CREATE TABLE turno_puntoDeAtencion (
  turno_id           INT NOT NULL,
  puntoDeAtencion_id INT NOT NULL,
  PRIMARY KEY (turno_id, puntoDeAtencion_id),
  CONSTRAINT fk_turno_pun_turno FOREIGN KEY (turno_id) REFERENCES Turnos(idTurno),
  CONSTRAINT fk_turno_pun_pun FOREIGN KEY (puntoDeAtencion_id) REFERENCES puntosDeAtencion(idpuntoDeAtencion)
) ENGINE=InnoDB;
