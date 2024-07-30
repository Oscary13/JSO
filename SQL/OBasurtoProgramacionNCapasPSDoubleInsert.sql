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
pIdRol NUMBER,
pCalle VARCHAR,
pNumeroInterior VARCHAR,
pNumeroExterior VARCHAR,
pIdColonia NUMBER
)AS

vIdUsuario NUMBER;
BEGIN
INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP, IdRol) 
VALUES (pUserName, pNombre, pApellidoPaterno, pApellidoMaterno, pEmail, pPassword, pFechaNacimiento, pSexo, pTelefono, pCelular, pCURP, pIdRol)
RETURNING IdUsuario INTO vIdUsuario;
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES (pCalle,pNumeroInterior,pNumeroExterior,pIdColonia,vIdUsuario);
END UsuarioAdd;

CALL UsuarioAdd ( 'UserName25678', 'Oscary', 'Basurto', 'Basurto',  'obasurto@gmail2378.com',  'password1', TO_DATE('26-08-2002','DD-MM-YYYY'),  'M',  '5566774433',  'OPPO',  'KJGKYVYTVR476FYUB',2, 'El Gallo','473','228',5);CREATE OR REPLACE PROCEDURE UsuarioAdd(
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
pIdRol NUMBER,
pCalle VARCHAR,
pNumeroInterior VARCHAR,
pNumeroExterior VARCHAR,
pIdColonia NUMBER
)AS

vIdUsuario NUMBER;
BEGIN
INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP, IdRol) 
VALUES (pUserName, pNombre, pApellidoPaterno, pApellidoMaterno, pEmail, pPassword, pFechaNacimiento, pSexo, pTelefono, pCelular, pCURP, pIdRol)
RETURNING IdUsuario INTO vIdUsuario;
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES (pCalle,pNumeroInterior,pNumeroExterior,pIdColonia,vIdUsuario);
END UsuarioAdd;

CALL UsuarioAdd ( 'UserName25678', 'Oscary', 'Basurto', 'Basurto',  'obasurto@gmail2378.com',  'password1', TO_DATE('26-08-2002','DD-MM-YYYY'),  'M',  '5566774433',  'OPPO',  'KJGKYVYTVR476FYUB',2, 'El Gallo','473','228',5);
CALL UsuarioAdd ( ?, ?, ?, ?,  ?,  ?, ?,  ?, ?,  ?,  ?,?, ?,?,?,?);


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
pIdRol NUMBER,
pCalle VARCHAR,
pNumeroInterior VARCHAR,
pNumeroExterior VARCHAR,
pIdColonia NUMBER
)AS
vCount NUMBER;
BEGIN
UPDATE usuario SET UserName = pUsername, Nombre = pNombre, ApellidoPaterno = pApellidoPaterno, ApellidoMaterno = pApellidoMaterno,
Email = pEmail, Password = pPassword, FechaNacimiento = pFechaNacimiento, Sexo = pSexo, Telefono = pTelefono, Celular = pCelular, CURP = pCURP, IdRol = pIdRol
WHERE IdUsuario = pIdUsuario;

SELECT COUNT(*) INTO vCount FROM Direccion WHERE IdUsuario = pIdUsuario;
IF vCount > 0 THEN
UPDATE Direccion SET  Calle = pCalle, NumeroInterior = pNumeroInterior, NumeroExterior = pNumeroExterior, IdColonia = pIdColonia 
WHERE IdUsuario = pIdUsuario;
ELSE
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario) VALUES (pCalle,pNumeroInterior,pNumeroExterior,pIdColonia,pIdUsuario);
END IF;
END UsuarioUpdate;

CALL UsuarioUpdate ( 54,'1', '2', '3', '4',  '5',  '6', TO_DATE('10-10-2010','DD-MM-YYYY'),  'F',  '11111111',  'NOTHING',  'VVVVVVV',1, 'Cobalto','109','105',2);
CALL UsuarioUpdate ( ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?);
DECLARE
 dato NUMBER;
 BEGIN
SELECT COUNT(*) INTO dato FROM Direccion WHERE IdUsuario = 64;
IF dato > 0 THEN
DBMS_OUTPUT.PUT_LINE('JA');
ELSE
DBMS_OUTPUT.PUT_LINE('NO');
END IF;
END;

CREATE OR REPLACE PROCEDURE PruebaIF(
pIdUsuario NUMBER,
dato OUT NUMBER

)
AS
vCount NUMBER;
BEGIN
SELECT COUNT(*) INTO vCount FROM Direccion WHERE IdUsuario = pIdUsuario;
IF vCount > 0 THEN
dato := 11;
ELSE
dato := 12;
END IF;
END PruebaIF;


VARIABLE dato NUMBER;
CALL PruebaIF(522,:dato);
PRINT dato;
 

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

call UsuarioDelete (27);

create or replace PROCEDURE UsuarioDelete(
pIdUsuario IN NUMBER
)AS
BEGIN
DELETE FROM Direccion WHERE idusuario = pIdUsuario;
DELETE FROM usuario WHERE idusuario = pIdUsuario;
END UsuarioDelete;
