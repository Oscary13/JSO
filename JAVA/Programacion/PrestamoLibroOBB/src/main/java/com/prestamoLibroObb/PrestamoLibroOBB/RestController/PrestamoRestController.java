/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.RestController;

import com.prestamoLibroObb.PrestamoLibroOBB.DAO.PrestamoDAO;
import com.prestamoLibroObb.PrestamoLibroOBB.DAO.PrestamoDAOImplementation;
import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Prestamo;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
@RequestMapping("/apiprestamo")
public class PrestamoRestController{
    
    @Autowired
    private PrestamoDAOImplementation prestamoDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody Prestamo prestamo) {
        Result result = prestamoDAOImplementation.Add(prestamo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
        
    }
    
    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll() {
        Result  result = prestamoDAOImplementation.GetAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
