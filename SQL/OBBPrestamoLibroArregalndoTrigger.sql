create or replace TRIGGER verificarStock
BEFORE INSERT ON Prestamo
FOR EACH ROW
DECLARE 
vStock Libro.stock%TYPE;
BEGIN
SELECT Stock INTO vStock
FROM libro
WHERE idLibro =:NEW.idLibro;
IF vStock = 0 THEN
RAISE_APPLICATION_ERROR(-20001,'NO Se encuentran libros en stock');
END IF;
END;

create or replace TRIGGER trg_actualiza_costo_total
BEFORE UPDATE ON Prestamo
FOR EACH ROW
DECLARE
    v_precio_por_dia_extra Libro.PrecioDiaPenitencia%TYPE;
    v_dias_retraso NUMBER;
    v_id_estatus_regresado Estatus.IdEstatus%TYPE;
    
    v_dias_prestamo NUMBER;
    v_precio_por_dia NUMBER;
BEGIN
    -- Obtener el IdEstatus correspondiente a 'Regresado'
    SELECT IdEstatus INTO v_id_estatus_regresado
    FROM Estatus
    WHERE Nombre = 'Regresado';
    
    v_dias_prestamo := TRUNC(:NEW.FechaDevolucionAsignada - :NEW.FechaPrestamo);
            -- Obtener el PrecioPorDiaExtra del libro asociado
            SELECT PrecioDiaPenitencia INTO v_precio_por_dia_extra
            FROM Libro
            WHERE IdLibro = :NEW.IdLibro;
            
    IF :NEW.IdEstatus = v_id_estatus_regresado THEN
        IF :NEW.FechaDevolucion > :NEW.FechaDevolucionAsignada THEN
            -- Calcular el número de días de retraso
            v_dias_retraso := TRUNC(:NEW.FechaDevolucion - :NEW.FechaDevolucionAsignada);
            
            
            SELECT precioPorDia
            INTO v_precio_por_dia
            FROM libro
            WHERE IdLibro = :NEW.IdLibro;

            -- Actualizar CostoTotal sumando el costo extra por los días de retraso
            :NEW.CostoTotal := (v_dias_prestamo * v_precio_por_dia) + (v_precio_por_dia_extra * v_dias_retraso);
            ELSE
            :NEW.CostoTotal := (v_dias_prestamo * v_precio_por_dia);
        END IF;
            ELSE
            :NEW.CostoTotal := (v_dias_prestamo * v_precio_por_dia);
    END IF;
END;