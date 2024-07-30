/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/rolapi")
@CrossOrigin(origins = "*")
public class RolRestController {

    @Autowired
    private RolDAOImplementation rolDAOImplementation;

    @GetMapping("/getall")
    public ResponseEntity<Result> GetAllJPARol() {
        Result result = rolDAOImplementation.GetAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
