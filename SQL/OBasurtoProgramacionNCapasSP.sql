
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
pCURP VARCHAR
)AS
BEGIN
INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP) 
VALUES (pUserName, pNombre, pApellidoPaterno, pApellidoMaterno, pEmail, pPassword, pFechaNacimiento, pSexo, pTelefono, pCelular, pCURP);
END UsuarioAdd;

EXEC usuarioadd ('UserName2', 'Oscary', 'Basurto', 'Basurto',  'obasurto@gmail2',  'password1', TO_DATE('26-08-2002','DD-MM-YYYY'),  'M',  '5566774433',  'OPPO',  'KJGKYVYTVR476FYUB');

CREATE OR REPLACE PROCEDURE UsuarioDelete(
pIdUsuario IN NUMBER
)AS
BEGIN
DELETE FROM usuario WHERE idusuario = pIdUsuario;
END UsuarioDelete;


EXEC usuariodelete (38);


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
pCURP VARCHAR
)AS
BEGIN
UPDATE usuario SET UserName = pUsername, Nombre = pNombre, ApellidoPaterno = pApellidoPaterno, ApellidoMaterno = pApellidoMaterno,
Email = pEmail, Password = pPassword, FechaNacimiento = pFechaNacimiento, Sexo = pSexo, Telefono = pTelefono, Celular = pCelular, CURP = pCURP
WHERE IdUsuario = pIdUsuario;
END UsuarioUpdate;

CALL UsuarioUpdate ( 5, 'UserName2', 'Oscary', 'Basurto', 'Basurto',  'obasurto@gmail2',  'password1', TO_DATE('26-08-2002','DD-MM-YYYY'),  'M',  '5566774433',  'OPPO',  'KJGKYVYTVR476FYUB');


CREATE OR REPLACE PROCEDURE UsuarioGetAll(
usuariosCursor OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN usuariosCursor FOR 
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
IdRol,
Rol.Nombre AS NombreRol
FROM Usuario;
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
SELECT idUsuario,UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP FROM USUARIO WHERE idUsuario = pidUsuario;
END UsuarioGetById;

VARIABLE datosCursor REFCURSOR;
CALL UsuarioGetById(5, :datosCursor);
PRINT datosCursor;
