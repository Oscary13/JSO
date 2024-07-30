/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciopoo;

import ML.ProductoAlimenticio;
import ML.ProductoElectronico;
import java.util.Date;

/**
 *
 * @author Alien 2
 */
public class EjercicioPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ProductoAlimenticio productoAlimenticio = new ProductoAlimenticio();
        productoAlimenticio.setNombre("Leche");
        productoAlimenticio.setPrecio(200.90);
        productoAlimenticio.setStock(12);
        productoAlimenticio.setIdProducto(1);
        productoAlimenticio.setFechaCaducidad(new Date());
        
        ProductoElectronico productoElectronico = new ProductoElectronico();
        productoElectronico.setIdProducto(2);
        productoElectronico.setNombre("Refrigerador");
        productoElectronico.setMarca("LG");
        productoElectronico.setModelo("A12");
        productoElectronico.setPrecio(4000.10);
        productoElectronico.setStock(13);
        
        
        System.out.println("JAjaja");
        productoAlimenticio.Descripcion(productoAlimenticio.getFechaCaducidad());
        productoElectronico.Descripcion(productoElectronico.getMarca());

    }


}
