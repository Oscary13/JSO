ALTER TABLE  Usuario ADD Imagen CLOB;

create or replace PROCEDURE UsuarioGetAll(
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
Usuario.Imagen,
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


create or replace PROCEDURE UsuarioAdd(
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
pImagen CLOB,
pIdRol NUMBER,
pCalle VARCHAR,
pNumeroInterior VARCHAR,
pNumeroExterior VARCHAR,
pIdColonia NUMBER
)AS

vIdUsuario NUMBER;
BEGIN
INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP, Imagen, IdRol) 
VALUES (pUserName, pNombre, pApellidoPaterno, pApellidoMaterno, pEmail, pPassword, pFechaNacimiento, pSexo, pTelefono, pCelular, pCURP, pImagen, pIdRol)
RETURNING IdUsuario INTO vIdUsuario;
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES (pCalle,pNumeroInterior,pNumeroExterior,pIdColonia,vIdUsuario);
END UsuarioAdd;


create or replace PROCEDURE UsuarioDelete(
pIdUsuario IN NUMBER
)AS
vCount NUMBER;
BEGIN
SELECT COUNT(*) INTO vCount FROM Direccion WHERE IdUsuario = pIdUsuario;
IF vCount > 0 THEN
DELETE FROM Direccion WHERE idusuario = pIdUsuario;
DELETE FROM usuario WHERE idusuario = pIdUsuario;
ELSE
DELETE FROM usuario WHERE idusuario = pIdUsuario;
END IF;

END UsuarioDelete;

create or replace  PROCEDURE UsuarioGetById(
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
Usuario.Imagen,
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


create or replace PROCEDURE UsuarioUpdate(
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
pImagen CLOB,
pIdRol NUMBER,
pCalle VARCHAR,
pNumeroInterior VARCHAR,
pNumeroExterior VARCHAR,
pIdColonia NUMBER
)AS
vCount NUMBER;
BEGIN
UPDATE usuario SET UserName = pUsername, Nombre = pNombre, ApellidoPaterno = pApellidoPaterno, ApellidoMaterno = pApellidoMaterno,
Email = pEmail, Password = pPassword, FechaNacimiento = pFechaNacimiento, Sexo = pSexo, Telefono = pTelefono, Celular = pCelular, CURP = pCURP, Imagen = pImagen, IdRol = pIdRol
WHERE IdUsuario = pIdUsuario;

SELECT COUNT(*) INTO vCount FROM Direccion WHERE IdUsuario = pIdUsuario;
IF vCount > 0 THEN
UPDATE Direccion SET  Calle = pCalle, NumeroInterior = pNumeroInterior, NumeroExterior = pNumeroExterior, IdColonia = pIdColonia 
WHERE IdUsuario = pIdUsuario;
ELSE
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES (pCalle,pNumeroInterior,pNumeroExterior,pIdColonia,pIdUsuario);
END IF;
END UsuarioUpdate;