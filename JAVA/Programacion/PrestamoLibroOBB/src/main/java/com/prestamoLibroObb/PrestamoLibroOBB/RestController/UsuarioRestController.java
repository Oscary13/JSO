/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.RestController;

import com.prestamoLibroObb.PrestamoLibroOBB.DAO.UsuarioDAOImplementation;
import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Usuario;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;
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
@RequestMapping("apiusuario")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody Usuario usuario){
        Result result = usuarioDAOImplementation.Add(usuario);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }    
    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll(){
        Result result = usuarioDAOImplementation.GetAll();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
