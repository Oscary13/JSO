/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class ColoniaDAOImplementation implements ColoniaDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EntityManager entityManager;
    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            List<Colonia> colonias = jdbcTemplate.query("SELECT IdColonia, Nombre, CodigoPostal FROM Colonia", new ColoniaRowMapper());
            result.object= colonias;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
            
        
        return result;
    }

    @Override
    public Result GetByIdMunicipio(int idMunicipio) {
        Result result = new Result();
        try {
            TypedQuery<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia> queryColonia = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio =:IdMunicipio",com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia.class);
            queryColonia.setParameter("IdMunicipio", idMunicipio);
            List<com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia> coloniasJPA = queryColonia.getResultList();
            List<Colonia> coloniasML = new ArrayList<>();
            for (com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia coloniaJPA : coloniasJPA) {
                Colonia coloniaML = new Colonia();
                coloniaML.setIdColonia(coloniaJPA.getIdColonia());
                coloniaML.setNombre(coloniaJPA.getNombre());
                coloniaML.setCodigoPostal(coloniaJPA.getCodigoPostal());
                coloniaML.Municipio = new Municipio();
                coloniaML.Municipio.setIdMunicipio(coloniaJPA.Municipio.getIdMunicipio());
                coloniasML.add(coloniaML);
            }
            result.correct = true;
            result.object = coloniasML;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
