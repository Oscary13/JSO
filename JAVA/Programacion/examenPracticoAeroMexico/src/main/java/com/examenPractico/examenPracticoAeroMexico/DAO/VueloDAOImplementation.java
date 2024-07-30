/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.DAO;

import com.examenPractico.examenPracticoAeroMexico.ML.DB;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import com.examenPractico.examenPracticoAeroMexico.ML.Vuelo;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class VueloDAOImplementation implements VueloDAO{
    
    private DB db;

    public VueloDAOImplementation(DB db) throws ParseException {
        this.db = DB.getInstance();
    }
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            List<Vuelo> vuelos = db.getAllVuelos();
            result.correct = true;
            result.object = vuelos;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
        
    }
    
}
