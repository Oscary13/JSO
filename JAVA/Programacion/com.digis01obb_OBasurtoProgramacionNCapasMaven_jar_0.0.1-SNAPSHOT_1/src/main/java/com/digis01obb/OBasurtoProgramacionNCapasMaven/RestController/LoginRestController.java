/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginRestController {
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @PostMapping("/validation")
    public ResponseEntity<Result> Login(@RequestBody Usuario usuario){
        Result result = usuarioDAOImplementation.Login(usuario);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
