/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Result GetAllJPA(Usuario usuario) {
        //JPQL

        Result result = new Result();
        if (usuario.getNombre() == null) {
            usuario.setNombre("");
        }
        if (usuario.getApellidoPaterno() == null) {
            usuario.setApellidoPaterno("");
        }
        if (usuario.getApellidoMaterno() == null) {
            usuario.setApellidoMaterno("");
        }
        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario> queryusuarios = entityManager.createQuery("FROM Usuario WHERE LOWER(Nombre) LIKE LOWER(:nombre) AND LOWER(ApellidoPaterno) LIKE LOWER(:apellidoPaterno) AND LOWER(ApellidoMaterno) LIKE LOWER(:apellidoMaterno)", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class);
            queryusuarios.setParameter("nombre", "%" + usuario.getNombre().trim().replaceAll("\\s+", " ") + "%");
            queryusuarios.setParameter("apellidoPaterno", "%" + usuario.getApellidoPaterno().trim().replaceAll("\\s+", " ") + "%");
            queryusuarios.setParameter("apellidoMaterno", "%" + usuario.getApellidoMaterno().trim().replaceAll("\\s+", " ") + "%");
            List<Usuario> usuarios = queryusuarios.getResultList();
            List<UsuarioDireccion> usuariosDireccion = new ArrayList<>();
            for (Usuario usuarioJPA : usuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.usuario = usuarioJPA;
                TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion.class);
                queryDireccion.setParameter("IdUsuario", usuarioJPA.getIdUsuario());
                try {
                    Direccion direccionJPA = queryDireccion.getSingleResult();
                    usuarioDireccion.direccion = direccionJPA;
                } catch (Exception ex) {
                    continue;
                } finally {
                    usuariosDireccion.add(usuarioDireccion);
                }
            }
            result.correct = true;
            result.object = usuariosDireccion;

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

            entityManager.persist(usuarioDireccion.usuario);
            usuarioDireccion.direccion.Usuario = usuarioDireccion.usuario;
            entityManager.persist(usuarioDireccion.direccion);
//            return new ResponseEntity<>(usuarioDireccion, HttpStatus.CREATED);
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
    public Result UpdateJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {

            entityManager.merge(usuarioDireccion.usuario);
            usuarioDireccion.direccion.Usuario = usuarioDireccion.usuario;

            TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", Direccion.class);
            queryDireccion.setParameter("IdUsuario", usuarioDireccion.usuario.getIdUsuario());
            try {
                com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();
                usuarioDireccion.direccion.setIdDireccion(direccionJPA.getIdDireccion());
                entityManager.merge(usuarioDireccion.direccion);
            } catch (Exception ex) {
                entityManager.persist(usuarioDireccion.direccion);
            }
            result.correct = true;
            result.object = usuarioDireccion;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            return new ResponseEntity<>(usuarioDireccion, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @Override
    @Transactional
    public Result DeleteJPA(int idUsuario) {
        Result result = new Result(); 
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario =: IdUsuario", Direccion.class);
        queryDireccion.setParameter("IdUsuario", idUsuario);
        try {
            Direccion direccionJPA = queryDireccion.getSingleResult();
            usuarioDireccion.direccion = direccionJPA;
            entityManager.remove(direccionJPA);
        } catch (Exception ex) {
            System.out.println("No existe dirección asociada al usuario");
        } finally {
            try {
                Usuario usuarioJPA = entityManager.find(Usuario.class, idUsuario);
                usuarioDireccion.usuario = usuarioJPA;
                entityManager.remove(usuarioJPA);
//                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
//                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            result.correct = true;
            result.object = usuarioDireccion;
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
            Direccion direccionJPA = queryDireccion.getSingleResult();
            usuarioDireccion.direccion = direccionJPA;

        } catch (Exception ex) {
        } finally {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario> queryDireccion = entityManager.createQuery("FROM Usuario WHERE IdUsuario =: IdUsuario", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario.class);
            queryDireccion.setParameter("IdUsuario", idUsuario);
            Usuario usuarioJPA = queryDireccion.getSingleResult();
            usuarioDireccion.usuario = usuarioJPA;
            
           result.correct = true;
           result.object = usuarioDireccion;
        }

        return result;
    }

    @Override
    public Result Login(Usuario usuario) {
        Result result = new Result();
        try {
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario WHERE Email =:email AND Password =: password", Usuario.class);
            queryUsuario.setParameter("email", usuario.getEmail());
            queryUsuario.setParameter("password", usuario.getPassword());
            Usuario usuarioResult = queryUsuario.getSingleResult();
            result.correct = true;
            result.object = usuarioResult;
//            return new ResponseEntity<>(usuarioResult, HttpStatus.OK);
        } catch (Exception ex) {
            result.correct= false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Exception", ex.getLocalizedMessage());
//            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return  result;

    }
}
