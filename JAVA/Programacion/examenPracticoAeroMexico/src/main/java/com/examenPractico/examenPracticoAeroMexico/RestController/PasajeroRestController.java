/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.RestController;

import com.examenPractico.examenPracticoAeroMexico.DAO.PasajeroDAOImplementation;
import com.examenPractico.examenPracticoAeroMexico.ML.Pasajero;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/apipasajero")
public class PasajeroRestController {
    @Autowired
    private PasajeroDAOImplementation pasajeroDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody Pasajero pasajero){
        
        Result result = pasajeroDAOImplementation.Add(pasajero);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        
        return new ResponseEntity<>(result, httpStatus);
                
    } 
    
    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll(){
        Result result = pasajeroDAOImplementation.GetAll();
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
        
    }
}
