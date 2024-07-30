/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.RestController;

import com.examenPractico.examenPracticoAeroMexico.DAO.VueloDAOImplementation;
import com.examenPractico.examenPracticoAeroMexico.ML.DB;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/apivuelo")
public class VueloRestController {

    @Autowired
    private VueloDAOImplementation vueloDAOImplementation;

    @GetMapping("/getall")
    public ResponseEntity<Result> GetAll(@PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFin") String fechaFin) {
        HttpHeaders headers = new HttpHeaders();
        Result result = new Result();
        HttpStatus httpStatus;
        try {
            DB.StringToDate(fechaInicio);
            DB.StringToDate(fechaFin);

            result = vueloDAOImplementation.GetAll();
            headers.add("fechaInicio", fechaInicio);
            headers.add("fechaFin", fechaFin);

            if (result.correct) {
                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result, headers, httpStatus);
    }
}
