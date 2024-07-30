/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.Controller;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alien 2
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
//    @GetMapping("/iniciar")
//    public String Login(Model model){
//        Usuario usuario = new Usuario();
//        model.addAttribute("usuario", usuario);
//        return "Login";
//    }
    
    @GetMapping("/iniciar")
    public String Login(){
        return "Login";
    }
    
//    @PostMapping("/iniciare")
//    public String Login2(@ModelAttribute Usuario usuario){
//        return "redirect:/usuario";
//    }
}
