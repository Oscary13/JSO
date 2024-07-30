CREATE VIEW ViewUsuarioDireccion AS
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
Usuario.Estatus,
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
LEFT JOIN Pais ON Estado.IdPais = Pais.IdPais;


SELECT * FROM ViewUsuarioDireccion WHERE LOWER(NombreUsuario) LIKE LOWER('%%') AND LOWER(ApellidoPaterno) LIKE LOWER('%%') AND LOWER(ApellidoMAterno) LIKE LOWER('%%') AND LOWER(NombreRol) LIKE LOWER('%a%')