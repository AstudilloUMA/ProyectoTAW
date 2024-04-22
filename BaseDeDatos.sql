DROP DATABASE taw;
CREATE DATABASE taw;

USE taw;

-- Creación de la tabla Usuario
CREATE TABLE Usuario (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Contrasena VARCHAR(255),
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    DNI VARCHAR(255),
    Edad INT,
    Sexo VARCHAR(50)
);

-- Creación de la tabla Trabajador heredando de Usuario y añadiendo el rol
CREATE TABLE Trabajador (
    UsuarioId INT,
    Rol VARCHAR(255), -- 'Entrenador' o 'Dietista'
    PRIMARY KEY (UsuarioId),
    FOREIGN KEY (UsuarioId) REFERENCES Usuario(Id)
);

-- Creación de la tabla Cliente, heredando de Usuario
CREATE TABLE Cliente (
    UsuarioId INT,
    Peso DECIMAL(5,2),
    Altura DECIMAL(5,2),
    Edad INT,
    PRIMARY KEY (UsuarioId),
    FOREIGN KEY (UsuarioId) REFERENCES Usuario(Id)
);

-- Creación de la tabla Ejercicio
CREATE TABLE Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Tipo VARCHAR(255),
    Nombre VARCHAR(255),
    Video TEXT -- Asumiendo que es un enlace a un video o un archivo binario
);

-- Creación de la tabla Sesion_de_Ejercicio
CREATE TABLE Sesion_de_Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Repeticiones INT,
    Cantidad INT,
    Orden INT,
    EjercicioId INT,
    FOREIGN KEY (EjercicioId) REFERENCES Ejercicio(Id)
);

-- Creación de la tabla Sesion_de_Entrenamiento
CREATE TABLE Sesion_de_Entrenamiento (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE,
    Dia VARCHAR(255),
    ClienteId INT,
    TrabajadorId INT, -- Referencia al entrenador responsable
    FOREIGN KEY (ClienteId) REFERENCES Cliente(UsuarioId),
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(UsuarioId)
);

-- Creación de la tabla Rutina_Semanal
CREATE TABLE Rutina_Semanal (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    FechaInicio DATE,
    FechaFin DATE,
    ClienteId INT,
    TrabajadorId INT, -- Referencia al entrenador responsable
    FOREIGN KEY (ClienteId) REFERENCES Cliente(UsuarioId),
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(UsuarioId)
);

-- Creación de la tabla Dieta
CREATE TABLE Dieta (
    Codigo INT AUTO_INCREMENT PRIMARY KEY,
    NumComidas INT,
    Tipo VARCHAR(255),
    FechaInicio DATE,
    FechaFin DATE,
    TrabajadorId INT, -- Referencia al dietista responsable
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(UsuarioId)
);

-- Creación de la tabla Comida
CREATE TABLE Comida (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    KilocaloriasTotales INT,
    Orden INT
);

-- Creación de la tabla Menu
CREATE TABLE Menu (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    ComidaId INT,
    Ingredientes TEXT,
    Preparacion TEXT,
    FOREIGN KEY (ComidaId) REFERENCES Comida(Id)
);

-- Creación de la tabla Feedback
CREATE TABLE Feedback (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Calificacion INT,
    EstadoDelCliente VARCHAR(255),
    Comentarios TEXT,
    EjercicioId INT,
	ClienteId INT,
    TrabajadorId INT, -- Referencia al entrenador que dio la sesión
    FOREIGN KEY (EjercicioId) REFERENCES Ejercicio(Id),
    FOREIGN KEY (ClienteId) REFERENCES Cliente(UsuarioId)
);

-- Creación de la tabla FeedbackDieta
CREATE TABLE FeedbackDieta (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Calificacion INT,
    Comentarios TEXT,
    DietaCodigo INT,
    ClienteId INT,
    FOREIGN KEY (DietaCodigo) REFERENCES Dieta(Codigo),
    FOREIGN KEY (ClienteId) REFERENCES Cliente(UsuarioId)
);

-- Administrador, se asume que es una extensión de usuario con todos sus atributos.
CREATE TABLE Administrador (
    UsuarioId INT,
    PRIMARY KEY (UsuarioId),
    FOREIGN KEY (UsuarioId) REFERENCES Usuario(Id)
);

