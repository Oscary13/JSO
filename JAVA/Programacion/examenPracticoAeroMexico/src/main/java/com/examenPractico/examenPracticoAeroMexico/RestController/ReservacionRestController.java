/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.RestController;

import com.examenPractico.examenPracticoAeroMexico.ML.Reservacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/apireservacion")
public class ReservacionRestController {
    
    @PostMapping("/add")
    public ResponseEntity<String> Add(@RequestBody @Valid Reservacion reservacion){
        HttpStatus httpStatus;
        try {
            return ResponseEntity.ok("Reservacion exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en la reservacion");
        }
        
    }
}
