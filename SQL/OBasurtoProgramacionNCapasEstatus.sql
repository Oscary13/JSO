ALTER TABLE Usuario ADD estatus CHAR(1);
UPDATE Usuario SET estatus = 0;
ALTER TABLE Usuario MODIFY estatus NOT NULL;