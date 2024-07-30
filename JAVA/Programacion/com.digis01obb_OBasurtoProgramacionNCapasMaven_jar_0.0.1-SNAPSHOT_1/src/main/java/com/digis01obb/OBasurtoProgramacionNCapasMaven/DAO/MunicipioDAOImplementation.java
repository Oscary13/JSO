/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio;
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
public class MunicipioDAOImplementation implements MunicipioDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByIdEstado(int idEstado) {
        Result result = new Result();
        List<Municipio> municipiosJPA = new ArrayList<>();
        try {
            TypedQuery<Municipio> queryMunicipio = entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado =:IdEstado", com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio.class);
            queryMunicipio.setParameter("IdEstado", idEstado);
            municipiosJPA = queryMunicipio.getResultList();
//            return municipiosJPA;
//            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio municipioJPA : municipiosJPA) {
//                Municipio municipioML = new Municipio();
//                municipioML.setIdMunicipio(municipioJPA.getIdMunicipio());
//                municipioML.setNombre(municipioJPA.getNombre());
//                municipioML.Estado = new Estado();
//                municipioML.Estado.setIdEstado(municipioJPA.Estado.getIdEstado());
//                municipiosML.add(municipioML);
//            }
            result.correct = true;
            result.object = municipiosJPA;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            return null;
        }
        return result;
    }

}
