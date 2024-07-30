/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.DAO;

import com.examenPractico.examenPracticoAeroMexico.ML.DB;
import com.examenPractico.examenPracticoAeroMexico.ML.Pasajero;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class PasajeroDAOImplementation implements PasajeroDAO{
    private DB db;

    public PasajeroDAOImplementation(DB db) throws ParseException {
        this.db = DB.getInstance();
    }
    
    
    
    @Override
    public Result Add(Pasajero pasajero) {
        Result result = new Result();
        try {
            db.addPasajero(pasajero);
            result.correct = true;
            result.object = pasajero;
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
        try {
            List<Pasajero> pasajeros = db.getAllPasajeros();
            result.correct = true;
            result.object = pasajeros;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
