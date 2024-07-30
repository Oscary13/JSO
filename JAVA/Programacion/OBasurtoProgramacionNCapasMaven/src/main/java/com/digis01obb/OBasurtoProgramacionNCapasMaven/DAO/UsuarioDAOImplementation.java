/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alien 2
 */
@Repository
public class UsuarioDAOImplementation implements UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Para JDBC Coneccion directa

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAllSP() {
//        String query ="SELECT  idUsuario, UserName,  Nombre,  ApellidoPaterno,  ApellidoMaterno,  Email,  Password,  FechaNacimiento,  Sexo,  Telefono,  Celular,  CURP FROM Usuario";
//        return jdbcTemplate.query(query, new UsuarioRowMapper());
        Result result = new Result();
        try {
            List<UsuarioDireccion> usuarioDireccion = jdbcTemplate.execute("{call UsuarioGetAll(?)}",
                    (CallableStatementCallback<List<UsuarioDireccion>>) callableStatement -> {
                        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                        callableStatement.execute();

                        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                        List<UsuarioDireccion> listaUsuarios = new ArrayList<>();
                        UsuarioRowMapper usuarioRowMapper = new UsuarioRowMapper();
                        while (resultSet.next()) {
                            listaUsuarios.add(usuarioRowMapper.mapRow(resultSet, resultSet.getRow()));
                        }
                        return listaUsuarios;
                    });
            result.object = usuarioDireccion;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result AddSP(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioAdd ( ?, ?, ?, ?,  ?,  ?, ?,  ?, ?,  ?,  ?,?, ?,?,?,?,?)}",
                    (CallableStatementCallback<List<UsuarioDireccion>>) callableStatement -> {
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
                        callableStatement.setString(12, usuarioDireccion.Usuario.getImagen());
                        callableStatement.setInt(13, usuarioDireccion.Usuario.Rol.getIdRol());
                        callableStatement.setString(14, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(15, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(16, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(17, usuarioDireccion.Direccion.Colonia.getIdColonia());
                        int rowAffected = callableStatement.executeUpdate();
                        if (rowAffected != 0) {
                            result.correct = true;

                        }
                        return null;
                    });

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

    @Override
    public Result DeleteSP(int idUsuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{call UsuarioDelete(?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {
                        callableStatement.setInt(1, idUsuario);
                        int rowAffected = callableStatement.executeUpdate();
                        if (rowAffected != 0) {
                            result.correct = true;

                        }
                        return null;
                    });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetByIdSP(int idUsuario) {
//        String query ="SELECT  idUsuario, UserName,  Nombre,  ApellidoPaterno,  ApellidoMaterno,  Email,  Password,  FechaNacimiento,  Sexo,  Telefono,  Celular,  CURP FROM Usuario";
//        return jdbcTemplate.query(query, new UsuarioRowMapper());
        Result result = new Result();
        try {
            UsuarioDireccion usuarioDireccion = jdbcTemplate.execute("{call UsuarioGetById(?, ?)}",
                    (CallableStatementCallback<UsuarioDireccion>) callableStatement -> {
                        callableStatement.setInt(1, idUsuario);
                        callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                        callableStatement.execute();

                        ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                        UsuarioDireccion listaUsuarios = new UsuarioDireccion();
                        UsuarioRowMapper usuarioRowMapper = new UsuarioRowMapper();
                        if (resultSet.next()) {
                            listaUsuarios = usuarioRowMapper.mapRow(resultSet, resultSet.getRow());
                        }
                        return listaUsuarios;
                    });
            result.object = usuarioDireccion;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result UpdateSP(UsuarioDireccion usuarioDireccion) {

        Result result = new Result();
        try {
            jdbcTemplate.execute("{call UsuarioUpdate ( ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {
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
                        callableStatement.setString(13, usuarioDireccion.Usuario.getImagen());
                        callableStatement.setInt(14, usuarioDireccion.Usuario.Rol.getIdRol());
                        callableStatement.setString(16, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(16, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(17, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(18, usuarioDireccion.Direccion.Colonia.getIdColonia());
                        int rowAffected = callableStatement.executeUpdate();
                        if (rowAffected != 0) {
                            result.correct = true;
                        }
                        return null;
                    });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllJPA(Usuario usuario) {
        Result result = new Result();
        //JPQL
        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario> queryusuarios = entityManager.createQuery("FROM Usuario WHERE LOWER(Nombre) LIKE LOWER(:nombre) AND LOWER(ApellidoPaterno) LIKE LOWER(:apellidoPaterno) AND LOWER(ApellidoMaterno) LIKE LOWER(:apellidoMaterno)", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class);
            queryusuarios.setParameter("nombre", "%" + usuario.getNombre().trim().replaceAll("\\s+", " ") + "%");
            queryusuarios.setParameter("apellidoPaterno", "%" + usuario.getApellidoPaterno().trim().replaceAll("\\s+", " ") + "%");
            queryusuarios.setParameter("apellidoMaterno", "%" + usuario.getApellidoMaterno().trim().replaceAll("\\s+", " ") + "%");
            List<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario> usuarios = queryusuarios.getResultList();
            List<UsuarioDireccion> usuarioDireccions = new ArrayList<>();
            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA : usuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();
                usuarioDireccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
                usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
                usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
                usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
                usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
                usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
                usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());
                usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
                usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
                usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
                usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
                usuarioDireccion.Usuario.setCURP(usuarioJPA.getCURP());
                usuarioDireccion.Usuario.setImagen(usuarioJPA.getImagen());
                usuarioDireccion.Usuario.setEstaus(usuarioJPA.getEstatus());
                usuarioDireccion.Usuario.Rol = new Rol();
                usuarioDireccion.Usuario.Rol.setIdRol(usuarioJPA.Rol.getIdRol());
                usuarioDireccion.Usuario.Rol.setNombre(usuarioJPA.Rol.getNombre());
                TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion.class);
                queryDireccion.setParameter("IdUsuario", usuarioJPA.getIdUsuario());
                try {
                    com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();
                    usuarioDireccion.Direccion = new Direccion();
                    usuarioDireccion.Direccion.setIdDireccion(direccionJPA.getIdDireccion());
                    usuarioDireccion.Direccion.setCalle(direccionJPA.getCalle());
                    usuarioDireccion.Direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
                    usuarioDireccion.Direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
                    usuarioDireccion.Direccion.Colonia = new Colonia();
                    usuarioDireccion.Direccion.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
                    usuarioDireccion.Direccion.Colonia.setNombre(direccionJPA.Colonia.getNombre());
                    usuarioDireccion.Direccion.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
                    usuarioDireccion.Direccion.Colonia.Municipio = new Municipio();
                    usuarioDireccion.Direccion.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
                    usuarioDireccion.Direccion.Colonia.Municipio.setNombre(direccionJPA.Colonia.Municipio.getNombre());
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado = new Estado();
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado.setNombre(direccionJPA.Colonia.Municipio.Estado.getNombre());
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais = new Pais();
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
                    usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setNombre(direccionJPA.Colonia.Municipio.Estado.Pais.getNombre());
                } catch (Exception ex) {
                    continue;
                } finally {
                    usuarioDireccions.add(usuarioDireccion);
                }
            }
            result.correct = true;
            result.object = usuarioDireccions;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional
    public Result AddJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario();
            usuarioJPA.setNombre(usuarioDireccion.Usuario.getNombre());
            usuarioJPA.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
            usuarioJPA.setUserName(usuarioDireccion.Usuario.getUserName());
            usuarioJPA.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
            usuarioJPA.setSexo(usuarioDireccion.Usuario.getSexo());
            usuarioJPA.setEmail(usuarioDireccion.Usuario.getEmail());
            usuarioJPA.setPassword(usuarioDireccion.Usuario.getPassword());
            usuarioJPA.setCelular(usuarioDireccion.Usuario.getCelular());
            usuarioJPA.setCURP(usuarioDireccion.Usuario.getCURP());
            usuarioJPA.setTelefono(usuarioDireccion.Usuario.getTelefono());
            usuarioJPA.setImagen(usuarioDireccion.Usuario.getImagen());
            usuarioJPA.Rol = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol();
            usuarioJPA.Rol.setIdRol(usuarioDireccion.Usuario.Rol.getIdRol());

            entityManager.persist(usuarioJPA);

            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion();
            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
            direccionJPA.Usuario = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario();
            direccionJPA.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
            direccionJPA.Colonia = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia();
            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
            entityManager.persist(direccionJPA);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    @Transactional
    public Result UpdateJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario();
            usuarioJPA.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
            usuarioJPA.setNombre(usuarioDireccion.Usuario.getNombre());
            usuarioJPA.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
            usuarioJPA.setUserName(usuarioDireccion.Usuario.getUserName());
            usuarioJPA.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
            usuarioJPA.setSexo(usuarioDireccion.Usuario.getSexo());
            usuarioJPA.setEmail(usuarioDireccion.Usuario.getEmail());
            usuarioJPA.setPassword(usuarioDireccion.Usuario.getPassword());
            usuarioJPA.setCelular(usuarioDireccion.Usuario.getCelular());
            usuarioJPA.setCURP(usuarioDireccion.Usuario.getCURP());
            usuarioJPA.setTelefono(usuarioDireccion.Usuario.getTelefono());
            usuarioJPA.setImagen(usuarioDireccion.Usuario.getImagen());
            usuarioJPA.Rol = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol();
            usuarioJPA.Rol.setIdRol(usuarioDireccion.Usuario.Rol.getIdRol());
            entityManager.merge(usuarioJPA);

            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion.class);
            queryDireccion.setParameter("IdUsuario", usuarioJPA.getIdUsuario());
            try {
                com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();
                direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
                direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
                direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
                direccionJPA.Usuario = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario();
                direccionJPA.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
                direccionJPA.Colonia = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia();
                direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
                entityManager.merge(direccionJPA);
            } catch (Exception ex) {
                com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion();
                direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
                direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
                direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
                direccionJPA.Usuario = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario();
                direccionJPA.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
                direccionJPA.Colonia = new com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia();
                direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
                entityManager.persist(direccionJPA);
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional
    public Result DeleteJPA(int idUsuario) {
        Result result = new Result();

        TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion.class);
        queryDireccion.setParameter("IdUsuario", idUsuario);
        try {
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();
            entityManager.remove(direccionJPA);
        } catch (Exception ex) {
            System.out.println("No existe dirección asociada al usuario");
        } finally {
            try {
                com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA = entityManager.find(com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class, idUsuario);
                entityManager.remove(usuarioJPA);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
            }
            result.correct = true;
        }

        return result;
    }

    @Override
    public Result GetByIdJPA(int idUsuario) {
        Result result = new Result();
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion.class);
            queryDireccion.setParameter("IdUsuario", idUsuario);
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();

            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setIdDireccion(direccionJPA.getIdDireccion());
            usuarioDireccion.Direccion.setCalle(direccionJPA.getCalle());
            usuarioDireccion.Direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
            usuarioDireccion.Direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
            usuarioDireccion.Direccion.Colonia = new Colonia();
            usuarioDireccion.Direccion.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
            usuarioDireccion.Direccion.Colonia.setNombre(direccionJPA.Colonia.getNombre());
            usuarioDireccion.Direccion.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
            usuarioDireccion.Direccion.Colonia.Municipio = new Municipio();
            usuarioDireccion.Direccion.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
            usuarioDireccion.Direccion.Colonia.Municipio.setNombre(direccionJPA.Colonia.Municipio.getNombre());
            usuarioDireccion.Direccion.Colonia.Municipio.Estado = new Estado();
            usuarioDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
            usuarioDireccion.Direccion.Colonia.Municipio.Estado.setNombre(direccionJPA.Colonia.Municipio.Estado.getNombre());
            usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais = new Pais();
            usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
            usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setNombre(direccionJPA.Colonia.Municipio.Estado.Pais.getNombre());
        } catch (Exception e) {
        } finally {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario> queryDireccion = entityManager.createQuery("FROM Usuario WHERE IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class);
            queryDireccion.setParameter("IdUsuario", idUsuario);
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA = queryDireccion.getSingleResult();

            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
            usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
            usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
            usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
            usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
            usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
            usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());
            usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
            usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
            usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
            usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
            usuarioDireccion.Usuario.setCURP(usuarioJPA.getCURP());
            usuarioDireccion.Usuario.setImagen(usuarioJPA.getImagen());
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Usuario.Rol.setIdRol(usuarioJPA.Rol.getIdRol());
            usuarioDireccion.Usuario.Rol.setNombre(usuarioJPA.Rol.getNombre());
        }
        result.correct = true;
        result.object = usuarioDireccion;

        return result;
    }

    @Override
    public Result AddTransaccion(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            StoredProcedureQuery sPQuery = entityManager.createStoredProcedureQuery("UsuarioAdd");
            sPQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(7, Date.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(12, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(13, Integer.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(14, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(15, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(16, String.class, ParameterMode.IN);
            sPQuery.registerStoredProcedureParameter(17, Integer.class, ParameterMode.IN);

            sPQuery.setParameter(1, usuarioDireccion.Usuario.getUserName());
            sPQuery.setParameter(2, usuarioDireccion.Usuario.getNombre());
            sPQuery.setParameter(3, usuarioDireccion.Usuario.getApellidoPaterno());
            sPQuery.setParameter(4, usuarioDireccion.Usuario.getApellidoPaterno());
            sPQuery.setParameter(5, usuarioDireccion.Usuario.getEmail());
            sPQuery.setParameter(6, usuarioDireccion.Usuario.getPassword());
            sPQuery.setParameter(7, usuarioDireccion.Usuario.getFechaNacimiento());
            sPQuery.setParameter(8, usuarioDireccion.Usuario.getSexo());
            sPQuery.setParameter(9, usuarioDireccion.Usuario.getTelefono());
            sPQuery.setParameter(10, usuarioDireccion.Usuario.getCelular());
            sPQuery.setParameter(11, usuarioDireccion.Usuario.getCURP());
            sPQuery.setParameter(12, usuarioDireccion.Usuario.getImagen());
            sPQuery.setParameter(13, usuarioDireccion.Usuario.Rol.getIdRol());
            sPQuery.setParameter(14, usuarioDireccion.Direccion.getCalle());
            sPQuery.setParameter(15, usuarioDireccion.Direccion.getNumeroInterior());
            sPQuery.setParameter(16, usuarioDireccion.Direccion.getNumeroExterior());
            sPQuery.setParameter(17, usuarioDireccion.Direccion.Colonia.getIdColonia());
            sPQuery.execute();
            result.correct = true;
            result.object = usuarioDireccion;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional
    public Result ChangeStatus(int idUsuario, char estatus) {
        Result result = new Result();
        try {
            com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario usuarioJPA = entityManager.find(com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class, idUsuario);
            if (usuarioJPA != null) {
                usuarioJPA.setEstatus(estatus);
                entityManager.merge(usuarioJPA);

                result.correct = true;
                result.object = usuarioJPA;
            } else {
                result.correct = false;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllView(Usuario usuario) {
        Result result = new Result();
        try {
            List<UsuarioDireccion> usuarioDireccion = jdbcTemplate.execute("SELECT * FROM viewusuariodireccion WHERE LOWER(NombreUsuario) "
                    + "LIKE LOWER(?) AND LOWER(ApellidoPaterno) LIKE LOWER(?) AND LOWER(ApellidoMAterno) LIKE LOWER(?) "
                    + "AND LOWER(NombreRol) LIKE LOWER(?)",
                    (PreparedStatementCallback<List<UsuarioDireccion>>) preparedStatement -> {
                        preparedStatement.setString(1, "%" + usuario.getNombre().trim().replaceAll("\\s+", " ") + "%");
                        preparedStatement.setString(2, "%" + usuario.getApellidoPaterno().trim().replaceAll("\\s+", " ") + "%");
                        preparedStatement.setString(3, "%" + usuario.getApellidoMaterno().trim().replaceAll("\\s+", " ") + "%");
                        preparedStatement.setString(4, "%" + usuario.Rol.getNombre().trim().replaceAll("\\s+", " ") + "%");

                        ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
                        List<UsuarioDireccion> listaUsuarios = new ArrayList<>();
                        UsuarioRowMapper usuarioRowMapper = new UsuarioRowMapper();
                        while (resultSet.next()) {
                            listaUsuarios.add(usuarioRowMapper.mapRow(resultSet, resultSet.getRow()));
                        }
                        return listaUsuarios;
                    });
            result.object = usuarioDireccion;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
