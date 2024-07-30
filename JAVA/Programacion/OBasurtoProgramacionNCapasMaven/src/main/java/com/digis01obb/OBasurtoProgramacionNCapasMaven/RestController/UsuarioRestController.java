/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/usuarioapi")
public class UsuarioRestController {
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @PostMapping("/changeEstatus")
    public Result ChangeEstatus(@RequestParam("idUsuario") int idUsuario,@RequestParam("estatus") char estatus){
       Result result = usuarioDAOImplementation.ChangeStatus(idUsuario, estatus);
       return result;
    }
}
