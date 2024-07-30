/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Estatus;
import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Usuario;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class UsuarioDAOImplementation implements UsuarioDAO{
    @Autowired
    public EntityManager entityManager;
    @Override
    @Transactional
    public Result Add(Usuario usuario) {
         Result result = new Result();
        try {
            entityManager.persist(usuario);
            result.correct = true;
            result.object = usuario;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAll() {
         Result result = new Result();
        List<Usuario> usuarioJPA = new ArrayList<>();

        try {
            TypedQuery<Usuario> estatusQuery = entityManager.createQuery("From Usuario", Usuario.class);
            usuarioJPA = estatusQuery.getResultList();
            result.correct = true;
            result.object = usuarioJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
