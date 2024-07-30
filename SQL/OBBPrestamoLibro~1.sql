CREATE OR REPLACE TRIGGER calcularcostoprestamo
BEFORE INSERT ON prestamo
FOR EACH ROW
DECLARE
    v_dias_prestamo NUMBER;
    v_precio_por_dia FLOAT;
BEGIN

    v_dias_prestamo := TRUNC(:NEW.FechaDevolucionAsignada - :NEW.FechaPrestamo);

    SELECT precioPorDia
    INTO v_precio_por_dia
    FROM libro
    WHERE IdLibro = :NEW.IdLibro;


    :NEW.costoTotal := v_dias_prestamo * v_precio_por_dia;
END;






