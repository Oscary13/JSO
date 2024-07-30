/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

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
public class PaisDAOImplementation implements PaisDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAllJPA() {
        Result result = new Result();

        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais> queryPais = entityManager.createQuery("FROM Pais", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais.class);
            List<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais> paisesJPA = queryPais.getResultList();
            List<Pais> paisesML = new ArrayList<>();

            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais paisJPA : paisesJPA) {
                Pais paisML = new Pais();
                paisML.setIdPais(paisJPA.getIdPais());;
                paisML.setNombre(paisJPA.getNombre());
                paisesML.add(paisML);
            }
            result.correct = true;
            result.object = paisesML;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

}
