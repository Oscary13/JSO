/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.RestController;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Operacion;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 2
 */
@RestController //Definir clase Para exponer logica 
@RequestMapping("/demoapi")
public class DemoRestController {

    @GetMapping
    public String HelloWorld() {
        return "<h1>Hola Mundo</h1>";
    }

    @GetMapping("/salu2")
    public Map<String, Object> HelloWorld(@RequestParam("nombre") String nombre) {
        Map<String,Object> response =  new HashMap<>();
        response.put("Menssage", "Hola " + nombre + " ¿Como estas?");
        return response;
        
    }
    
    @GetMapping("/suma")
    public Map<String, Object> Suma(@RequestParam("numero1") double numero1,@RequestParam("numero2") double numero2){
        Map<String, Object> response = new HashMap<>();
        response.put("numero1", numero1);
        response.put("numero2", numero2);
        response.put("resultado", numero1 + numero2);
        return response;
        
    }
    @GetMapping("/resta")
    public Operacion Resta(@RequestBody Operacion operacion){
        operacion.setResultado(operacion.getNumero1() - operacion.getNumero2());
        return operacion;
        
    }
    @GetMapping("/multiplicacion")
    public Operacion Multiplicacion(@RequestBody Operacion operacion){
        double resultado = operacion.getNumero1() * operacion.getNumero2();
        operacion.setResultado(resultado);
        return operacion;
        
    }
    @GetMapping("/divicion")
    public Operacion Divicion(@RequestBody Operacion operacion){
        double resultado = operacion.getNumero1() / operacion.getNumero2();
        operacion.setResultado(resultado);
        return operacion;
        
    }

}
