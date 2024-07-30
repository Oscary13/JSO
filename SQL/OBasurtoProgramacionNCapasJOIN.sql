UPDATE Usuario SET apellidomaterno = apellidoPaterno || '2' WHERE apellidomaterno IS NULL;

UPDATE Usuario SET CURP = '88118828282' WHERE CURP IS NULL;

CREATE TABLE Rol (IdRol NUMBER(6) GENERATED ALWAYS AS IDENTITY PRIMARY KEY, NOMBRE VARCHAR(50)NOT NULL);

INSERT INTO Rol (nombre) VALUES ('Administrador');
INSERT INTO Rol (nombre) VALUES ('Cliente');
INSERT INTO Rol (nombre) VALUES ('Provedor');
ALTER TABLE Rol MODIFY nombre NOT NULL;

ALTER TABLE Rol ADD CONSTRAINT pKRol2 PRIMARY KEY (IdRol); 
ALTER TABLE Usuario ADD IdRol NUMBER(6);
ALTER TABLE Usuario ADD FOREIGN KEY (idRol) REFERENCES Rol(idRol);

ALTER TABLE Usuario ADD IdRol NUMBER(6);



CREATE OR REPLACE PROCEDURE UsuarioGetAll(
usuariosCursor OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN usuariosCursor FOR 
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
END UsuarioGetAll;

VARIABLE datosCursor REFCURSOR;
CALL UsuarioGetAll(:datosCursor);
PRINT datosCursor;

CREATE OR REPLACE PROCEDURE UsuarioGetById(
pIdUsuario IN NUMBER,
usuariosIdCursor OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN usuariosIdCursor FOR 
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
LEFT JOIN Pais ON Estado.IdPais = Pais.IdPais
WHERE usuario.idusuario = pIdUsuario;
END UsuarioGetById;

VARIABLE datosCursor REFCURSOR;
CALL UsuarioGetById(5, :datosCursor);
PRINT datosCursor;


CREATE OR REPLACE PROCEDURE UsuarioAdd(
pUserName VARCHAR,
pNombre VARCHAR,
pApellidoPaterno VARCHAR,
pApellidoMaterno VARCHAR,
pEmail VARCHAR,
pPassword VARCHAR,
pFechaNacimiento DATE,
pSexo CHAR,
pTelefono VARCHAR,
pCelular VARCHAR,
pCURP VARCHAR,
pIdRol NUMBER
)AS
BEGIN
INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP, IdRol) 
VALUES (pUserName, pNombre, pApellidoPaterno, pApellidoMaterno, pEmail, pPassword, pFechaNacimiento, pSexo, pTelefono, pCelular, pCURP, pIdRol);
END UsuarioAdd;


CREATE OR REPLACE PROCEDURE UsuarioUpdate(
pIdUsuario NUMBER,
pUserName VARCHAR,
pNombre VARCHAR,
pApellidoPaterno VARCHAR,
pApellidoMaterno VARCHAR,
pEmail VARCHAR,
pPassword VARCHAR,
pFechaNacimiento DATE,
pSexo CHAR,
pTelefono VARCHAR,
pCelular VARCHAR,
pCURP VARCHAR,
pIdRol NUMBER
)AS
BEGIN
UPDATE usuario SET UserName = pUsername, Nombre = pNombre, ApellidoPaterno = pApellidoPaterno, ApellidoMaterno = pApellidoMaterno,
Email = pEmail, Password = pPassword, FechaNacimiento = pFechaNacimiento, Sexo = pSexo, Telefono = pTelefono, Celular = pCelular, CURP = pCURP, IdRol = pIdRol
WHERE IdUsuario = pIdUsuario;
END UsuarioUpdate;

CALL UsuarioUpdate ( 30, 'UserName25', 'Oscary', 'Basurto', 'Basurto',  'obasurto@gmail2.com',  'password1', TO_DATE('26-08-2002','DD-MM-YYYY'),  'M',  '5566774433',  'OPPO',  'KJGKYVYTVR476FYUB',2);


SELECT 
idUsuario,
UserName, 
Usuario.Nombre AS NombreUsuario, 
ApellidoPaterno, 
ApellidoMaterno, 
Email, 
Password, 
FechaNacimiento, 
Sexo, 
Telefono, 
Celular, 
CURP,
Usuario.IdRol,
rol.nombre AS NombreRol
FROM Usuario INNER JOIN Rol ON Usuario.IdRol = Rol.IdRol WHERE usuario.idusuario = 30;