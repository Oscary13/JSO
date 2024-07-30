CREATE TABLE Editorial (
IdEditorial NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
Nombre Varchar(255)
);

ALTER TABLE Editorial MODIFY Nombre NOT NULL;

CREATE TABLE Libro (
IdLibro NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(255) NOT NULL,
Descripcion CLOB,
NumeroPaginas Number NOT NULL,
IdEditorial NUMBER,
FOREIGN KEY(IdEditorial) REFERENCES Editorial(IdEditorial)
);


INSERT INTO Editorial (Nombre) Values ('Penguin Random House');
INSERT INTO Editorial (Nombre) Values ('HarperCollins');
INSERT INTO Editorial (Nombre) Values ('Simon');
INSERT INTO Editorial (Nombre) Values ('Hachette Livre');
INSERT INTO Editorial (Nombre) Values ('Macmillan Publishers');

INSERT INTO Libro (Nombre, Descripcion,NumeroPaginas, IdEditorial) VALUES ('1984', 'Libro de 1984', 100, 1);
INSERT INTO Libro (Nombre, Descripcion,NumeroPaginas, IdEditorial) VALUES ('To Kill a Mockingbird', 'To Kill a Mockingbird de lash ', 200, 2);
INSERT INTO Libro (Nombre, Descripcion,NumeroPaginas, IdEditorial) VALUES ('The Girl on the Train', 'The Girl on the Train de jafrsa', 120, 3);
INSERT INTO Libro (Nombre, Descripcion,NumeroPaginas, IdEditorial) VALUES ('The Catcher in the Rye', 'The Catcher in the Rye The Catcher in the Rye de faeews', 300, 5);
INSERT INTO Libro (Nombre, Descripcion,NumeroPaginas, IdEditorial) VALUES ('The Goldfinch', 'The GoldfinchThe Goldfinch To Kill a Mockingbird de 1984', 500, 4);