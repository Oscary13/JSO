/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import java.util.List;
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
@RequestMapping("/paisapi")
@CrossOrigin(origins = "*")
public class PaisRestController {

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;

    @GetMapping("/getall")
    public ResponseEntity<Result> GetAllJPAPais() {
        Result result =  paisDAOImplementation.GetAllJPA();
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
