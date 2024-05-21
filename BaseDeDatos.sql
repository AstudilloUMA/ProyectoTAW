DROP DATABASE IF EXISTS taw;
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
    Usuario VARCHAR(255) UNIQUE NOT NULL,
    Contrasena VARCHAR(255),
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    DNI VARCHAR(255),
    Edad INT,
    Sexo VARCHAR(50)
);

CREATE TABLE Trabajador (
    Usuario_id INT,
    Rol_Id INT,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id) ON DELETE CASCADE,
    FOREIGN KEY (Rol_Id) REFERENCES Rol_Trabajador(Id)
);

-- Creación de la tabla Rutina_Semanal
CREATE TABLE Rutina_Semanal (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Fecha_Inicio DATE,
    Fecha_Fin DATE,
    Trabajador_Id INT, -- Referencia al entrenador responsable
    FOREIGN KEY (Trabajador_Id) REFERENCES Trabajador(Usuario_id) ON DELETE CASCADE
);

-- Creación de la tabla Cliente, heredando de Usuario
CREATE TABLE Cliente (
    Usuario_id INT,
    Peso DECIMAL(5,2),
    Altura DECIMAL(5,2),
    Edad INT,
    Rutina_Id INT NULL,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id) ON DELETE CASCADE,
    FOREIGN KEY (Rutina_Id) REFERENCES Rutina_Semanal(Id)
);

-- Creación de la tabla Ejercicio
CREATE TABLE Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Tipo_Id INT,
    Nombre VARCHAR(255),
    Video TEXT, -- Asumiendo que es un enlace a un video o un archivo binario
    FOREIGN KEY (Tipo_Id) REFERENCES Tipo_Ejercicio(Id) ON DELETE CASCADE
);

-- Creación de la tabla Sesion_de_Ejercicio
CREATE TABLE Sesion_de_Ejercicio (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Repeticiones INT,
    Cantidad INT,
    Orden INT,
    Ejercicio_Id INT,
    FOREIGN KEY (Ejercicio_Id) REFERENCES Ejercicio(Id) ON DELETE CASCADE
);

-- Creación de la tabla Sesion_de_Entrenamiento
CREATE TABLE Sesion_de_Entrenamiento (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE,
    Dia VARCHAR(255),
    Trabajador_Id INT, -- Referencia al entrenador responsable
    FOREIGN KEY (Trabajador_Id) REFERENCES Trabajador(Usuario_id) ON DELETE CASCADE
);

-- Tabla intermedia para relacionar Sesion_de_Entrenamiento con Sesion_de_Ejercicio
CREATE TABLE Entrenamiento_Ejercicio (
    Sesion_de_Entrenamiento_Id INT,
    Sesion_de_Ejercicio_Id INT,
    PRIMARY KEY (Sesion_de_Entrenamiento_Id, Sesion_de_Ejercicio_Id),
    FOREIGN KEY (Sesion_de_Entrenamiento_Id) REFERENCES Sesion_de_Entrenamiento(Id) ON DELETE CASCADE,
    FOREIGN KEY (Sesion_de_Ejercicio_Id) REFERENCES Sesion_de_Ejercicio(Id) ON DELETE CASCADE
);

-- Tabla intermedia para relacionar Rutina_Semanal con Sesion_de_Entrenamiento
CREATE TABLE Rutina_Semanal_Entrenamiento (
    Rutina_Semanal_Id INT,
    Sesion_de_Entrenamiento_Id INT,
    PRIMARY KEY (Rutina_Semanal_Id, Sesion_de_Entrenamiento_Id),
    FOREIGN KEY (Rutina_Semanal_Id) REFERENCES Rutina_Semanal(Id) ON DELETE CASCADE,
    FOREIGN KEY (Sesion_de_Entrenamiento_Id) REFERENCES Sesion_de_Entrenamiento(Id) ON DELETE CASCADE
);

-- Creación de la tabla Dieta
CREATE TABLE Dieta (
    Codigo INT AUTO_INCREMENT PRIMARY KEY,
    Num_Comidas INT,
    Tipo VARCHAR(255),
    Fecha_Inicio DATE,
    Fecha_Fin DATE,
    Trabajador_Id INT, -- Referencia al dietista responsable
    FOREIGN KEY (Trabajador_Id) REFERENCES Trabajador(Usuario_id) ON DELETE CASCADE
);

-- Creación de la tabla Comida
CREATE TABLE Comida (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    Kilocalorias_Totales INT,
    Orden INT
);

-- Tabla intermedia para relacionar Dieta con Comida
CREATE TABLE Dieta_Comida (
    Dieta_Codigo INT,
    Comida_Id INT,
    PRIMARY KEY (Dieta_Codigo, Comida_Id),
    FOREIGN KEY (Dieta_Codigo) REFERENCES Dieta(Codigo) ON DELETE CASCADE,
    FOREIGN KEY (Comida_Id) REFERENCES Comida(Id) ON DELETE CASCADE
);

-- Creación de la tabla Menu
CREATE TABLE Menu (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Comida_Id INT,
    Ingredientes TEXT,
    Preparacion TEXT,
    FOREIGN KEY (Comida_Id) REFERENCES Comida(Id) ON DELETE CASCADE
);

-- Creación de la tabla Feedback
CREATE TABLE Feedback (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Calificacion INT,
    Estado_Del_Cliente VARCHAR(255),
    Comentarios TEXT,
    Ejercicio_Id INT,
    Cliente_Id INT,
    Trabajador_Id INT, -- Referencia al entrenador que dio la sesión
    FOREIGN KEY (Ejercicio_Id) REFERENCES Ejercicio(Id) ON DELETE CASCADE,
    FOREIGN KEY (Cliente_Id) REFERENCES Cliente(Usuario_id) ON DELETE CASCADE,
    FOREIGN KEY (Trabajador_Id) REFERENCES Trabajador(Usuario_id) ON DELETE CASCADE
);

-- Creación de la tabla FeedbackDieta
CREATE TABLE FeedbackDieta (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Calificacion INT,
    Comentarios TEXT,
    Dieta_Codigo INT,
    Cliente_Id INT,
    FOREIGN KEY (Dieta_Codigo) REFERENCES Dieta(Codigo) ON DELETE CASCADE,
    FOREIGN KEY (Cliente_Id) REFERENCES Cliente(Usuario_id) ON DELETE CASCADE
);

-- Administrador, se asume que es una extensión de usuario con todos sus atributos.
CREATE TABLE Administrador (
    Usuario_id INT,
    PRIMARY KEY (Usuario_id),
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(Id) ON DELETE CASCADE
);
