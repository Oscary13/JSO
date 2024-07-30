/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.ColoniaDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/coloniaapi")
@CrossOrigin(origins = "*")
public class ColoniaRestController {

    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @GetMapping("/getbyidmunicipio")
    public ResponseEntity<Result> GetByIdMunicipio(@RequestParam("idMunicipio") int idMunicipio) {
        Result result =  coloniaDAOImplementation.GetByIdMunicipio(idMunicipio);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
            
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

}
