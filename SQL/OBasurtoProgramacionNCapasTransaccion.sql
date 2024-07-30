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
INSERT INTO usuario 
(UserName, 
Nombre, 
ApellidoPaterno, 
ApellidoMaterno, 
Email, 
Password, 
FechaNacimiento, 
Sexo, 
Telefono, 
Celular, 
CURP, 
Imagen, 
IdRol) 
VALUES (
pUserName, 
pNombre, 
pApellidoPaterno, 
pApellidoMaterno, 
pEmail, 
pPassword, 
pFechaNacimiento, 
pSexo, 
pTelefono, 
pCelular, 
pCURP, 
pImagen, 
pIdRol)
RETURNING IdUsuario INTO vIdUsuario;
INSERT INTO Direccion (
Calle, 
NumeroInterior, 
NumeroExterior, 
IdColonia, 
IdUsuario) 
VALUES (
pCalle,
pNumeroInterior,
pNumeroExterior,
pIdColonia,
vIdUsuario);

COMMIT;

EXCEPTION
WHEN OTHERS THEN
ROLLBACK;
RAISE_APPLICATION_ERROR(-20001,'Ocurrio un error al ejecutar la inserción ' || SQLERRM);

END UsuarioAdd;