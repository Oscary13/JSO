/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class EstadoDAOImplementation implements EstadoDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByIdPais(int IdPais) {
        Result result = new Result();
        List<Estado> estadosJPA = new ArrayList<>();
        try {
            TypedQuery<Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais =: idPais", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado.class);
            queryEstado.setParameter("idPais", IdPais);
            estadosJPA = queryEstado.getResultList();
//            for (Estado estadoJPA : estadosJPA) {
//                estadosJPA.add(estadoJPA);
//            }
//            return estadosJPA;
            result.correct = true;
            result.object = estadosJPA;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            return null;
        }
        return result;

    }

}
