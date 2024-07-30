/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.RestController;

import com.oBasurto.examenPracticoDigipro.DAO.MateriaDAOImplementation;
import com.oBasurto.examenPracticoDigipro.JPA.Materia;
import com.oBasurto.examenPracticoDigipro.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/apimateria")
public class MateriaRestController {
    
    @Autowired
    private MateriaDAOImplementation materiaDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody Materia materia){
        Result result = materiaDAOImplementation.Add(materia);
        HttpStatus httpStatus = (result.correct) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(result, httpStatus);
    }
    
    @GetMapping("/getall")
    private ResponseEntity<Result> GetAll(){
        Result result = materiaDAOImplementation.GetAll();
        HttpStatus httpStatus = (result.correct) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(result, httpStatus);
        
    }
    
    @DeleteMapping("/delete")
    private ResponseEntity<Result> Delete(int idMateria){
        Result result = materiaDAOImplementation.Delete(idMateria);
        HttpStatus httpStatus = (result.correct) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(result, httpStatus);
    }
    
}
