/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.DAO;

import com.examenPractico.examenPracticoAeroMexico.ML.Pasajero;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;

/**
 *
 * @author Alien 2
 */
public interface PasajeroDAO {
    Result Add(Pasajero pasajero);
    Result GetAll();
    
    
}
