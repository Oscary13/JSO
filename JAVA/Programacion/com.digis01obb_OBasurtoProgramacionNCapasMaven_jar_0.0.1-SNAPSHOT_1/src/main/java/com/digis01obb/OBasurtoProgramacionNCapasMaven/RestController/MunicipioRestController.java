/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio;
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
@RequestMapping("/municipioapi")
@CrossOrigin(origins = "*")
public class MunicipioRestController {

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @GetMapping("/getbyidestado")
    public ResponseEntity<Result> GetByIdEstado(@RequestParam("idEstado") int idEstado) {
        Result result = municipioDAOImplementation.GetByIdEstado(idEstado);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
