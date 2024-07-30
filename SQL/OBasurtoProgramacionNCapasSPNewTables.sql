CREATE TABLE Pais (
IdPais NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50)
);

ALTER TABLE Pais MODIFY Nombre NOT NULL;

INSERT INTO Pais (Nombre) VALUES ('México');
INSERT INTO Pais (Nombre) VALUES ('Brazil');
INSERT INTO Pais (Nombre) VALUES ('Estados Unidos');

CREATE TABLE Estado (
IdEstado NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
IdPais NUMBER,
FOREIGN KEY (IdPais) REFERENCES Pais(IdPais) 
);

INSERT INTO Estado (Nombre, IdPais) VALUES ('México', 1);
INSERT INTO Estado (Nombre, IdPais) VALUES ('Guerrero', 1);
INSERT INTO Estado (Nombre, IdPais) VALUES ('Jalisco', 1);

CREATE TABLE Municipio (
IdMunicipio NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
IdEstado NUMBER,
FOREIGN KEY (IdEstado) REFERENCES Estado(IdEstado)
);

INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Naucalpan de Juárez', 1);
INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Tlanepantla de Baz', 1);
INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Nezahualcóyotl', 1);
INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Ecatepec de Morelos', 1);
INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Zumpango', 1);


CREATE TABLE Colonia(
IdColonia NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
CodigoPostal VARCHAR(50) NOT NULL,
IdMunicipio NUMBER,
FOREIGN KEY (IdMunicipio) REFERENCES Municipio (IdMunicipio) 
);

INSERT INTO Colonia (Nombre, CodigoPostal, IdMunicipio) VALUES ('Benito Juárez','57000', 1);
INSERT INTO Colonia (Nombre, CodigoPostal, IdMunicipio) VALUES ('Ciudad Lago','57810', 1);
INSERT INTO Colonia (Nombre, CodigoPostal, IdMunicipio) VALUES ('El Sol','57110', 1);
INSERT INTO Colonia (Nombre, CodigoPostal, IdMunicipio) VALUES ('Vicente Villada','57410', 1);
INSERT INTO Colonia (Nombre, CodigoPostal, IdMunicipio) VALUES ('Ezperanza','57820', 1);

CREATE TABLE Direccion (
IdDireccion NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Calle VARCHAR(50) NOT NULL,
NumeroInterior VARCHAR(20) NULL,
NumeroExterior VARCHAR(20) NOT NULL,
IdColonia NUMBER,
IdUsuario NUMBER,
FOREIGN KEY (IdColonia) REFERENCES Colonia(IdColonia),
FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario)
);


INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES ('La perla','2','5',1,5);
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES ('Cipreses','9','12',2,34);
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES ('Herreria','3','20',3, 31);
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES ('Carranza','17','11',4,25);
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES ('El Rosario','21','02',5,6);



SELECT 
Usuario.IdUsuario,
Usuario.Nombre AS NombreUsuario,
Usuario.ApellidoPaterno,
Usuario.ApellidoMaterno,
Usuario.UserName,
Usuario.FechaNacimiento,
Usuario.Sexo,
Usuario.Email,
Usuario.Password,
Usuario.Telefono,
Usuario.Celular,
Usuario.CURP,
Usuario.IdRol,
Rol.Nombre AS NombreRol,
Direccion.IdDireccion,
Direccion.Calle,
Direccion.NumeroInterior,
Direccion.NumeroExterior,
Direccion.IdColonia,
Colonia.Nombre AS NombreColonia,
Colonia.CodigoPostal,
Colonia.IdMunicipio,
Municipio.Nombre AS NombreMunicipio,
Municipio.IdEstado,
Estado.Nombre AS NombreEstado,
Estado.IdPais,
Pais.Nombre AS NombrePais
FROM Usuario 
INNER JOIN Rol ON Usuario.IdRol = Rol.IdRol
LEFT JOIN Direccion ON Usuario.IdUsuario = Direccion.IdUsuario
LEFT JOIN Colonia ON Direccion.IdColonia = Colonia.IdColonia
LEFT JOIN Municipio ON Colonia.IdMunicipio = Municipio.IdMunicipio
LEFT JOIN Estado ON Municipio.IdEstado = Estado.IdEstado
LEFT JOIN Pais ON Estado.IdPais = Pais.IdPais;
