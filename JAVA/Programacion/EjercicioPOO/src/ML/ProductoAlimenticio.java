/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alien 2
 */
public class ProductoAlimenticio extends Producto {
//    private int IdProductoAlimenticio;
    private Date FechaCaducidad;
    
//    public int getIdProductoAlimenticio(){
//        return  this.IdProductoAlimenticio;
//    }
//    public void setIdProductoAlimenticio(int IdProductoAlimenticio){
//        this.IdProductoAlimenticio = IdProductoAlimenticio;
//    }
    @Override
    public void Descripcion(){
        super.Descripcion();
        System.out.println(" Alimenticio");
    }
    
    public void Descripcion(Date Fechacaducidad){
        super.Descripcion();
        System.out.println(" Alimenticio y caduco el "+ new SimpleDateFormat("dd-MM-YYYY 'a las' hh:mm:ss").format(Fechacaducidad));
    }
    
    public Date getFechaCaducidad(){
        return this.FechaCaducidad;
    }
    
    public void setFechaCaducidad(Date FechaCaducidad){
        this.FechaCaducidad = FechaCaducidad;
    }
    
}
