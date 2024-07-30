/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado;
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
@RequestMapping("/estadoapi")
@CrossOrigin(origins = "*")
public class EstadoRestController {

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @GetMapping("/getbyidpais")
    public ResponseEntity<Result> GetAllByIdPais(@RequestParam("idPais") int idPais) {
        Result result = estadoDAOImplementation.GetByIdPais(idPais);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
