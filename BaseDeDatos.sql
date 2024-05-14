DROP DATABASE taw;
CREATE DATABASE taw;

USE taw;

CREATE TABLE Rol_Trabajador (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Rol VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Tipo_Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Tipo VARCHAR(255) UNIQUE NOT NULL
);

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
    Usuario_id INT,
    RolId INT,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id),
    FOREIGN KEY (RolId) REFERENCES Rol_Trabajador(Id)
);

-- Creación de la tabla Cliente, heredando de Usuario
CREATE TABLE Cliente (
    Usuario_id INT,
    Peso DECIMAL(5,2),
    Altura DECIMAL(5,2),
    Edad INT,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id)
);

-- Creación de la tabla Ejercicio
CREATE TABLE Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    TipoId INT,
    Nombre VARCHAR(255),
    Video TEXT, -- Asumiendo que es un enlace a un video o un archivo binario
    FOREIGN KEY (TipoId) REFERENCES Tipo_Ejercicio(Id)
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
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Usuario_id),
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(Usuario_id)
);

-- Creación de la tabla Rutina_Semanal
CREATE TABLE Rutina_Semanal (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    FechaInicio DATE,
    FechaFin DATE,
    ClienteId INT,
    TrabajadorId INT, -- Referencia al entrenador responsable
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Usuario_id),
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(Usuario_id)
);

-- Creación de la tabla Dieta
CREATE TABLE Dieta (
    Codigo INT AUTO_INCREMENT PRIMARY KEY,
    NumComidas INT,
    Tipo VARCHAR(255),
    FechaInicio DATE,
    FechaFin DATE,
    TrabajadorId INT, -- Referencia al dietista responsable
    FOREIGN KEY (TrabajadorId) REFERENCES Trabajador(Usuario_id)
);

-- Creación de la tabla Comida
CREATE TABLE Comida (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    Kilocalorias_Totales INT,
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
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Usuario_id)
);

-- Creación de la tabla FeedbackDieta
CREATE TABLE FeedbackDieta (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Calificacion INT,
    Comentarios TEXT,
    DietaCodigo INT,
    ClienteId INT,
    FOREIGN KEY (DietaCodigo) REFERENCES Dieta(Codigo),
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Usuario_id)
);

-- Administrador, se asume que es una extensión de usuario con todos sus atributos.
CREATE TABLE Administrador (
    Usuario_id INT,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id)
);

