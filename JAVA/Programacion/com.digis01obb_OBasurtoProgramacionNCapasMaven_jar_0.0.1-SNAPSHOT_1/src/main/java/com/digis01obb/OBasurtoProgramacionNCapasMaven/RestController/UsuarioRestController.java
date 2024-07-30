/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.ColoniaDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.UsuarioDireccion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/usuarioapi")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @PostMapping
    public ResponseEntity<Result> GetAll(@RequestBody Usuario usuario) {
        Result result = usuarioDAOImplementation.GetAllJPA(usuario);
        HttpStatus httpStatus;
        HttpHeaders headers = new HttpHeaders();
        if (result.correct) {
            httpStatus = HttpStatus.OK;
            headers.add("Message", "Lista obtenida correctamente :3");

        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, headers, httpStatus);
    }

    @GetMapping("/usuariobyid")
    public ResponseEntity<Result> GetByIdJPA(@RequestParam("idUsuario") int idUsuario) {
        Result result = usuarioDAOImplementation.GetByIdJPA(idUsuario);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> Add(@RequestBody UsuarioDireccion usuarioDirecciomDireccion) {
        Result result = usuarioDAOImplementation.AddJPA(usuarioDirecciomDireccion);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> Update(@RequestBody UsuarioDireccion usuarioDirecciomDireccion) {
        Result result = usuarioDAOImplementation.UpdateJPA(usuarioDirecciomDireccion);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> Delete(@RequestParam("idUsuario") int idUsuario) {
        Result result = usuarioDAOImplementation.DeleteJPA(idUsuario);
        HttpStatus httpStatus;
        if (result.correct) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

}
