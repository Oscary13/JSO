CREATE TABLE Usuario (
IdUsuario Number GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50),
Apellido VARCHAR(50),
Edad NUMERIC(3),
UserName VARCHAR(50),
FechaNacimiento DATE
);

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Pedro','Slasar',12 ,'PSalasar', TO_DATE('12-05-2002','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Pamela','Torres', 22,'PTorres', TO_DATE('09-12-2002','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Juan','Medina',25 ,'JMEdina', TO_DATE('01-01-1890','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Dylan','Mendiola',30 ,'DMendiola', TO_DATE('02-07-1990','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Nancy','Ayala',35 ,'NAyala', TO_DATE('09-11-1980','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Carmen','Nuñes',30 ,'CNuñes', TO_DATE('10-12-1970','DD-MM-YYYY'));

INSERT INTO USUARIO (nombre, apellido, edad, username, fechanacimiento) VALUES 
('Pablo','Gonzales',22 ,'CNuñes', TO_DATE('01-03-2001','DD-MM-YYYY'));

UPDATE USUARIO SET fechanacimiento = TO_DATE('12-05-2002','DD-MM-YYYY') WHERE nombre = 'Pedro';

SELECT nombre, apellido FROM USUARIO WHERE EXTRACT (DAY FROM fechanacimiento) = 01;


ALTER TABLE USUARIO ADD (subscripcion NUMBER(1));

UPDATE usuario SET subscripcion = 0;

UPDATE USUARIO SET subscripcion = 1 WHERE edad = 30;

DELETE FROM USUARIO WHERE nombre = 'Pablo';

ALTER TABLE USUARIO DROP COLUMN subscripcion;