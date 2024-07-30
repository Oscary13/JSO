/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.RestController;

import com.oBasurto.examenPracticoDigipro.DAO.AlumnoDAOImplementation;
import com.oBasurto.examenPracticoDigipro.JPA.Alumno;
import com.oBasurto.examenPracticoDigipro.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/apialumno")
public class AlumnoRestController {
    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody Alumno Alumno){
        Result result = alumnoDAOImplementation.Add(Alumno);
        HttpStatus httpStatus =(result.correct) ? HttpStatus.OK : HttpStatus.BAD_REQUEST ;
        return new ResponseEntity<>(result, httpStatus);
    }
    
    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll(){
        Result result = alumnoDAOImplementation.GetAll();
        HttpStatus httpStatus = (result.correct) ? HttpStatus.OK :HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(result,httpStatus);
        
    }
    @DeleteMapping("delete")
    public ResponseEntity<Result> Delete (@RequestParam int idAlumno){
        Result result = alumnoDAOImplementation.Delete(idAlumno);
        HttpStatus httpStatus = (result.correct) ? HttpStatus.OK :HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result,httpStatus);
    }
    
    
}
