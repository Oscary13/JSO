CREATE OR REPLACE TRIGGER verificarStock
BEFORE INSERT ON Prestamo
FOR EACH ROW
DECLARE 
vStock Libro.stock%TYPE;
BEGIN
SELECT Stock INTO vStock
FROM libro
WHERE idLibro =:NEW.idLibro;
IF vStock = 0 THEN
RAISE_APPLICATION_ERROR(-200,'NO Se encuentran libros en stock');
END IF;
END;


CREATE OR REPLACE TRIGGER restaStock
AFTER INSERT ON Prestamo 
FOR EACH ROW
DECLARE 
vStock NUMBER;
BEGIN
SELECT stock INTO vStock
FROM Libro
WHERE idLibro = :NEW.idlibro;
UPDATE Libro
SET stock = vStock -1
WHERE idLibro =: NEW.idLibro;
END;




