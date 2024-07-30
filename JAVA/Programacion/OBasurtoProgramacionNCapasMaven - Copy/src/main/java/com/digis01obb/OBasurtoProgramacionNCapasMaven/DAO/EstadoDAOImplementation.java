/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
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
public class EstadoDAOImplementation implements EstadoDAO{
    @Autowired
    private  EntityManager entityManager;
    @Override
    public Result GetByIdPais(int IdPais) {
        Result result = new Result();
        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais =: idPais", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado.class);
            queryEstado.setParameter("idPais", IdPais);
            List<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado> estadosJPA = queryEstado.getResultList();
            List<Estado> estadosML = new ArrayList<>();
            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado estadoJPA : estadosJPA) {
                Estado estadoML = new Estado();
                estadoML.setIdEstado(estadoJPA.getIdEstado());
                estadoML.setNombre(estadoJPA.getNombre());
                estadoML.Pais = new Pais();
                estadoML.Pais.setIdPais(estadoJPA.Pais.getIdPais());
                estadosML.add(estadoML);
            }
            result.correct = true;
            result.object = estadosML;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
    
        return result;
    }
    
}
