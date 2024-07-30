/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.RestController;

import com.examenPractico.examenPracticoAeroMexico.DAO.UsuarioDAOImplementation;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import com.examenPractico.examenPracticoAeroMexico.ML.Usuario;
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
@RequestMapping("/apiusuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> AddUsuario(@RequestBody Usuario usuario){
        Result result = new Result();
        HttpStatus httpStatus;
        result = usuarioDAOImplementation.Add(usuario);
        if (result.correct) {
            httpStatus = HttpStatus.ACCEPTED;
        }else{
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(result, httpStatus);
        
        
    }
    
    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll(){
        Result result = usuarioDAOImplementation.GetAll();
        HttpStatus httpStatus;
        
        if (result.correct) {
            httpStatus = HttpStatus.ACCEPTED;
        }else{
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(result, httpStatus);
        
    }
    
    @PostMapping("/autenticar")
    public ResponseEntity<Result> Autentication(@RequestBody Usuario usuario){
        Result result = usuarioDAOImplementation.Autentication(usuario);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
