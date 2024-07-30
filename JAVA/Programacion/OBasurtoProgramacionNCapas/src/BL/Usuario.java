/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Alien 2
 */
public class Usuario {

    public static String query;

    public static ML.Result Add(ML.Usuario usuario) {
        ML.Result result = new ML.Result();
        query = "INSERT INTO usuario (UserName, Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password, FechaNacimiento, Sexo, Telefono, Celular, CURP) VALUES (?, ?, ?, ?,  ?,  ?, ?, ?, ?,  ?, ?)";
        try (Connection context = DL.ConnectionDB.GetConnection(); PreparedStatement preparedStatement = context.prepareStatement(query)) {

            preparedStatement.setString(1, usuario.getUserName());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidoPaterno());
            preparedStatement.setString(4, usuario.getApellidoMaterno());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getPassword());
            preparedStatement.setDate(7, fechaParse(usuario.getFechaNacimiento()));
            preparedStatement.setString(8, usuario.getSexo());
            preparedStatement.setString(9, usuario.getTelefono());
            preparedStatement.setString(10, usuario.getCelular());
            preparedStatement.setString(11, usuario.getCURP());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;

            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public static ML.Result Delete(ML.Usuario usuario) {
        ML.Result result = new ML.Result();
        query = "DELETE FROM USUARIO WHERE idusuario = ?";
        try (Connection context = DL.ConnectionDB.GetConnection(); PreparedStatement preparedStatement = context.prepareStatement(query)) {

            preparedStatement.setInt(1, usuario.getIdUsuario());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;

            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public static ML.Result Update(ML.Usuario usuario) {
        ML.Result result = new ML.Result();
        query = "UPDATE USUARIO SET nombre = ? WHERE idusuario = ?";
        try (Connection context = DL.ConnectionDB.GetConnection(); PreparedStatement preparedStatement = context.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setInt(2, usuario.getIdUsuario());
//            preparedStatement.executeQuery();
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public static ML.Result GetAll() {

        ML.Result result = new ML.Result();

        query = "SELECT idUsuario,Nombre, Apellido,Edad,UserName,FechaNacimiento FROM USUARIO";
        try (Connection context = DL.ConnectionDB.GetConnection(); Statement statement = context.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            result.objects = new ArrayList<>();
            while (resultSet.next()) {
                ML.Usuario usuario = new ML.Usuario();
                int idUsuario = resultSet.getInt("IdUsuario");
                String userName = resultSet.getString("UserName");
                String nombre = resultSet.getString("Nombre");
                String apellidoPaterno = resultSet.getString("ApellidoPaterno");
                String apellidoMaterno = resultSet.getString("ApellidoMaterno");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                Date fechaNacimiento = resultSet.getDate("FechaNacimiento");
                String sexo = resultSet.getString("Sexo");
                String telefono = resultSet.getString("Telefono");
                String celular = resultSet.getString("Celular");
                String cURP = resultSet.getString("CURP");

                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setApellidoPaterno(apellidoPaterno);
                usuario.setApellidoMaterno(apellidoMaterno);
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setSexo(sexo);
                usuario.setTelefono(telefono);
                usuario.setCelular(celular);
                usuario.setCURP(cURP);
                result.objects.add(usuario);

            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public static ML.Result GetById(ML.Usuario usuario) {

        ML.Result result = new ML.Result();

        query = "SELECT IdUsuario,Nombre, Apellido,Edad,UserName,FechaNacimiento FROM USUARIO WHERE IdUsuario = ?";
        try (Connection context = DL.ConnectionDB.GetConnection(); PreparedStatement preparedStatement = context.prepareStatement(query)) {
            preparedStatement.setInt(1, usuario.getIdUsuario());
            ResultSet resultSet = preparedStatement.executeQuery();
            result.object = new Object();
            if (resultSet.next()) {

                int idUsuario = resultSet.getInt("IdUsuario");
                String userName = resultSet.getString("UserName");
                String nombre = resultSet.getString("Nombre");
                String apellidoPaterno = resultSet.getString("ApellidoPaterno");
                String apellidoMaterno = resultSet.getString("ApellidoMaterno");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                Date fechaNacimiento = resultSet.getDate("FechaNacimiento");
                String sexo = resultSet.getString("Sexo");
                String telefono = resultSet.getString("Telefono");
                String celular = resultSet.getString("Celular");
                String cURP = resultSet.getString("CURP");

                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setApellidoPaterno(apellidoPaterno);
                usuario.setApellidoMaterno(apellidoMaterno);
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setSexo(sexo);
                usuario.setTelefono(telefono);
                usuario.setCelular(celular);
                usuario.setCURP(cURP);

                result.object = usuario;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public static ML.Result AddSP(ML.UsuarioDireccion usuarioDireccion) {
        ML.Result result = new ML.Result();
        query = "{CALL UsuarioAdd ( ?, ?, ?, ?,  ?,  ?, ?,  ?, ?,  ?,  ?,?, ?,?,?,?)}";

        try (Connection context = DL.ConnectionDB.GetConnection(); CallableStatement callableStatement = context.prepareCall(query)) {
            callableStatement.setString(1, usuarioDireccion.Usuario.getUserName());
            callableStatement.setString(2, usuarioDireccion.Usuario.getNombre());
            callableStatement.setString(3, usuarioDireccion.Usuario.getApellidoPaterno());
            callableStatement.setString(4, usuarioDireccion.Usuario.getApellidoMaterno());
            callableStatement.setString(5, usuarioDireccion.Usuario.getEmail());
            callableStatement.setString(6, usuarioDireccion.Usuario.getPassword());
            callableStatement.setDate(7, fechaParse(usuarioDireccion.Usuario.getFechaNacimiento()));
            callableStatement.setString(8, usuarioDireccion.Usuario.getSexo());
            callableStatement.setString(9, usuarioDireccion.Usuario.getTelefono());
            callableStatement.setString(10, usuarioDireccion.Usuario.getCelular());
            callableStatement.setString(11, usuarioDireccion.Usuario.getCURP());
            callableStatement.setInt(12, usuarioDireccion.Usuario.Rol.getIdRol());
            callableStatement.setString(13, usuarioDireccion.Direccion.getCalle());
            callableStatement.setString(14, usuarioDireccion.Direccion.getNumeroInterior());
            callableStatement.setString(15, usuarioDireccion.Direccion.getNumeroExterior());
            callableStatement.setInt(16, usuarioDireccion.Direccion.Colonia.getIdColonia());
            int rowAffected = callableStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;

            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public static ML.Result DeleteSP(ML.UsuarioDireccion usuarioDireccion) {
        ML.Result result = new ML.Result();
        query = "{call UsuarioDelete(?)}";
        try (Connection contex = DL.ConnectionDB.GetConnection(); CallableStatement callableStatement = contex.prepareCall(query)) {
            callableStatement.setInt(1, usuarioDireccion.Usuario.getIdUsuario());
            int rowAffected = callableStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;

            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    public static ML.Result UpdateSP(ML.UsuarioDireccion usuarioDireccion) {
        ML.Result result = new ML.Result();
        query = "{call UsuarioUpdate ( ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection contex = DL.ConnectionDB.GetConnection(); CallableStatement callableStatement = contex.prepareCall(query)) {
            callableStatement.setInt(1, usuarioDireccion.Usuario.getIdUsuario());
            callableStatement.setString(2, usuarioDireccion.Usuario.getUserName());
            callableStatement.setString(3, usuarioDireccion.Usuario.getNombre());
            callableStatement.setString(4, usuarioDireccion.Usuario.getApellidoPaterno());
            callableStatement.setString(5, usuarioDireccion.Usuario.getApellidoMaterno());
            callableStatement.setString(6, usuarioDireccion.Usuario.getEmail());
            callableStatement.setString(7, usuarioDireccion.Usuario.getPassword());
            callableStatement.setDate(8, fechaParse(usuarioDireccion.Usuario.getFechaNacimiento()));
            callableStatement.setString(9, usuarioDireccion.Usuario.getSexo());
            callableStatement.setString(10, usuarioDireccion.Usuario.getTelefono());
            callableStatement.setString(11, usuarioDireccion.Usuario.getCelular());
            callableStatement.setString(12, usuarioDireccion.Usuario.getCURP());
            callableStatement.setInt(13, usuarioDireccion.Usuario.Rol.getIdRol());
            callableStatement.setString(14, usuarioDireccion.Direccion.getCalle());
            callableStatement.setString(15, usuarioDireccion.Direccion.getNumeroInterior());
            callableStatement.setString(16, usuarioDireccion.Direccion.getNumeroExterior());
            callableStatement.setInt(17, usuarioDireccion.Direccion.Colonia.getIdColonia());
            int rowAffected = callableStatement.executeUpdate();
            if (rowAffected != 0) {
                result.correct = true;

            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    public static ML.Result GetAllSP() {

        ML.Result result = new ML.Result();

        query = "{call UsuarioGetAll(?)}";
        try (Connection context = DL.ConnectionDB.GetConnection(); CallableStatement callableStatement = context.prepareCall(query)) {
            callableStatement.registerOutParameter(1, OracleTypes.REF_CURSOR);
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            result.objects = new ArrayList<>();
            while (resultSet.next()) {
//                ML.Usuario usuario = new ML.Usuario();
//                usuario.Rol = new ML.Rol();

                ML.UsuarioDireccion usuarioDireccion = new ML.UsuarioDireccion();
                usuarioDireccion.Usuario = new ML.Usuario();
                usuarioDireccion.Usuario.Rol = new ML.Rol();

                usuarioDireccion.Direccion = new ML.Direccion();
                usuarioDireccion.Direccion.Colonia = new ML.Colonia();
                usuarioDireccion.Direccion.Colonia.Municipio = new ML.Municipio();
                usuarioDireccion.Direccion.Colonia.Municipio.Estado = new ML.Estado();
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais = new ML.Pais();

                int idUsuario = resultSet.getInt("IdUsuario");
                String nombreUsuario = resultSet.getString("NombreUsuario");
                String apellidoPaterno = resultSet.getString("ApellidoPaterno");
                String apellidoMaterno = resultSet.getString("ApellidoMaterno");
                String userName = resultSet.getString("UserName");
                Date fechaNacimiento = resultSet.getDate("FechaNacimiento");
                String sexo = resultSet.getString("Sexo");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String telefono = resultSet.getString("Telefono");
                String celular = resultSet.getString("Celular");
                String cURP = resultSet.getString("CURP");
                int idRol = resultSet.getInt("IdRol");
                String nombreRol = resultSet.getNString("NombreRol");
                int idDireccion = resultSet.getInt("IdDireccion");
                String calle = resultSet.getString("Calle");
                String numeroInterior = resultSet.getString("NumeroInterior");
                String numeroExterior = resultSet.getString("NumeroExterior");
                int idColonia = resultSet.getInt("IdColonia");
                String nombreColonia = resultSet.getString("NombreColonia");
                String codigoPostal = resultSet.getString("CodigoPostal");
                int idMunicipio = resultSet.getInt("IdMunicipio");
                String nombreMunicipio = resultSet.getString("NombreMunicipio");
                int idEstado = resultSet.getInt("IdEstado");
                String nombreEstado = resultSet.getString("NombreEstado");
                int idPais = resultSet.getInt("IdPais");
                String nombrePais = resultSet.getString("NombrePais");

                usuarioDireccion.Usuario.setIdUsuario(idUsuario);
                usuarioDireccion.Usuario.setNombre(nombreUsuario);
                usuarioDireccion.Usuario.setApellidoPaterno(apellidoPaterno);
                usuarioDireccion.Usuario.setApellidoMaterno(apellidoMaterno);
                usuarioDireccion.Usuario.setUserName(userName);
                usuarioDireccion.Usuario.setFechaNacimiento(fechaNacimiento);
                usuarioDireccion.Usuario.setSexo(sexo);
                usuarioDireccion.Usuario.setEmail(email);
                usuarioDireccion.Usuario.setPassword(password);
                usuarioDireccion.Usuario.setTelefono(telefono);
                usuarioDireccion.Usuario.setCelular(celular);
                usuarioDireccion.Usuario.setCURP(cURP);
                usuarioDireccion.Usuario.Rol.setIdRol(idRol);
                usuarioDireccion.Usuario.Rol.setNombreRol(nombreRol);

                usuarioDireccion.Direccion.setIdDireccion(idDireccion);
                usuarioDireccion.Direccion.setCalle(calle);
                usuarioDireccion.Direccion.setNumeroInterior(numeroInterior);
                usuarioDireccion.Direccion.setNumeroExterior(numeroExterior);
                usuarioDireccion.Direccion.Colonia.setIdColonia(idColonia);
                usuarioDireccion.Direccion.Colonia.setNombre(nombreColonia);
                usuarioDireccion.Direccion.Colonia.setCodigoPostal(codigoPostal);
                usuarioDireccion.Direccion.Colonia.Municipio.setIdMunicipio(idMunicipio);
                usuarioDireccion.Direccion.Colonia.Municipio.setNombre(nombreMunicipio);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(idEstado);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.setNombre(nombreEstado);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setIdPais(idPais);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setNombre(nombrePais);
                result.objects.add(usuarioDireccion);

            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public static ML.Result GetByIdSP(ML.Usuario usuario) {
        ML.Result result = new ML.Result();
        ML.UsuarioDireccion usuarioDireccion = new ML.UsuarioDireccion();
        
        usuarioDireccion.Usuario = new ML.Usuario();
        usuarioDireccion.Usuario.Rol = new ML.Rol();

        usuarioDireccion.Direccion = new ML.Direccion();
        usuarioDireccion.Direccion.Colonia = new ML.Colonia();
        usuarioDireccion.Direccion.Colonia.Municipio = new ML.Municipio();
        usuarioDireccion.Direccion.Colonia.Municipio.Estado = new ML.Estado();
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais = new ML.Pais();
        query = "{call UsuarioGetById(?, ?)}";
        try (Connection context = DL.ConnectionDB.GetConnection(); CallableStatement callabledStatemen = context.prepareCall(query)) {
            callabledStatemen.setInt(1, usuario.getIdUsuario());
            callabledStatemen.registerOutParameter(2, OracleTypes.REF_CURSOR);
            callabledStatemen.execute();

            ResultSet resultSet = (ResultSet) callabledStatemen.getObject(2);
            result.object = new Object();
            if (resultSet.next()) {

                int idUsuario = resultSet.getInt("IdUsuario");
                String nombreUsuario = resultSet.getString("NombreUsuario");
                String apellidoPaterno = resultSet.getString("ApellidoPaterno");
                String apellidoMaterno = resultSet.getString("ApellidoMaterno");
                String userName = resultSet.getString("UserName");
                Date fechaNacimiento = resultSet.getDate("FechaNacimiento");
                String sexo = resultSet.getString("Sexo");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String telefono = resultSet.getString("Telefono");
                String celular = resultSet.getString("Celular");
                String cURP = resultSet.getString("CURP");
                int idRol = resultSet.getInt("IdRol");
                String nombreRol = resultSet.getNString("NombreRol");
                int idDireccion = resultSet.getInt("IdDireccion");
                String calle = resultSet.getString("Calle");
                String numeroInterior = resultSet.getString("NumeroInterior");
                String numeroExterior = resultSet.getString("NumeroExterior");
                int idColonia = resultSet.getInt("IdColonia");
                String nombreColonia = resultSet.getString("NombreColonia");
                String codigoPostal = resultSet.getString("CodigoPostal");
                int idMunicipio = resultSet.getInt("IdMunicipio");
                String nombreMunicipio = resultSet.getString("NombreMunicipio");
                int idEstado = resultSet.getInt("IdEstado");
                String nombreEstado = resultSet.getString("NombreEstado");
                int idPais = resultSet.getInt("IdPais");
                String nombrePais = resultSet.getString("NombrePais");

                usuarioDireccion.Usuario.setIdUsuario(idUsuario);
                usuarioDireccion.Usuario.setNombre(nombreUsuario);
                usuarioDireccion.Usuario.setApellidoPaterno(apellidoPaterno);
                usuarioDireccion.Usuario.setApellidoMaterno(apellidoMaterno);
                usuarioDireccion.Usuario.setUserName(userName);
                usuarioDireccion.Usuario.setFechaNacimiento(fechaNacimiento);
                usuarioDireccion.Usuario.setSexo(sexo);
                usuarioDireccion.Usuario.setEmail(email);
                usuarioDireccion.Usuario.setPassword(password);
                usuarioDireccion.Usuario.setTelefono(telefono);
                usuarioDireccion.Usuario.setCelular(celular);
                usuarioDireccion.Usuario.setCURP(cURP);
                usuarioDireccion.Usuario.Rol.setIdRol(idRol);
                usuarioDireccion.Usuario.Rol.setNombreRol(nombreRol);

                usuarioDireccion.Direccion.setIdDireccion(idDireccion);
                usuarioDireccion.Direccion.setCalle(calle);
                usuarioDireccion.Direccion.setNumeroInterior(numeroInterior);
                usuarioDireccion.Direccion.setNumeroExterior(numeroExterior);
                usuarioDireccion.Direccion.Colonia.setIdColonia(idColonia);
                usuarioDireccion.Direccion.Colonia.setNombre(nombreColonia);
                usuarioDireccion.Direccion.Colonia.setCodigoPostal(codigoPostal);
                usuarioDireccion.Direccion.Colonia.Municipio.setIdMunicipio(idMunicipio);
                usuarioDireccion.Direccion.Colonia.Municipio.setNombre(nombreMunicipio);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(idEstado);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.setNombre(nombreEstado);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setIdPais(idPais);
                usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setNombre(nombrePais);

                result.object = usuarioDireccion;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;
    }

    public static java.sql.Date fechaParse(java.util.Date fechaNacimiento) {
        try {
            java.sql.Date sqlDate = new Date(fechaNacimiento.getTime());
            return sqlDate;
        } catch (Exception e) {
            System.out.println("Fecha incorrecta :(");
            return null;
        }
    }
}
