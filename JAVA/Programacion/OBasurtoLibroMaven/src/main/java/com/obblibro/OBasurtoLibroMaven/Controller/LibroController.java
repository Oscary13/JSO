/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obblibro.OBasurtoLibroMaven.Controller;

import com.obblibro.OBasurtoLibroMaven.DAO.LibroDAOImplemantation;
import com.obblibro.OBasurtoLibroMaven.ML.Libro;
import com.obblibro.OBasurtoLibroMaven.ML.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alien 2
 */
@Controller
@RequestMapping("/libro")
public class LibroController {
    @Autowired    
    private LibroDAOImplemantation libroDAOImplemantation;
    @GetMapping("/getAll")
    public String GetAll(Model model){
        Result result = libroDAOImplemantation.GetAll();
        model.addAttribute("libros", (List<Libro>)result.object);
        return "Libro/ListadoLibros";
    }
    
    @GetMapping("/formulario")
    public String FormLibro(){
        return "Libro/FormLibro";
    }
    
}
