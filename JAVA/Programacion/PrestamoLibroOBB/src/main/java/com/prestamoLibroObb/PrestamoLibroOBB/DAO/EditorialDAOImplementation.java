/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Editorial;
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
public class EditorialDAOImplementation implements EditorialDAO{
    
     @Autowired
    private EntityManager entityManager;
     
    @Override
    @Transactional
    public Result AddJPA(Editorial editorial) {
        Result result = new Result();
        
        try {

            entityManager.persist(editorial);
//            return new ResponseEntity<>(usuarioDireccion, HttpStatus.CREATED);
            result.correct = true;
            result.object = editorial;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllJPA() {
         Result result = new Result();
        List<Editorial> editotialJPA = new ArrayList<>();
        try {
            TypedQuery<Editorial> queryPais = entityManager.createQuery("FROM Editorial", Editorial.class);
            editotialJPA = queryPais.getResultList();
//            return paisesJPA;
//            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais paisJPA : paisesJPA) {
//                Pais paisML = new Pais();
//                paisML.setIdPais(paisJPA.getIdPais());;
//                paisML.setNombre(paisJPA.getNombre());
//                paisesML.add(paisML);
//            }
            result.correct = true;
            result.object = editotialJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            return null;
        }
        return result;
    }
    
}
