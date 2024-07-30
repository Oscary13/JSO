/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class Producto {
    private int IdProducto;
    private String Nombre;
    private int Stock;
    private double Precio;
    
    
    
    public void Descripcion(){
        System.out.print("Soy un producto");
    }
    
    public int getIdProducto(){
        return this.IdProducto;
    }
    
    public void setIdProducto(int IdProducto){
        this.IdProducto = IdProducto;
    }
    
    public String getNombre(){
       return this.Nombre;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public int getStock(){
        return this.Stock;
    }
    
    public void setStock(int Stock){
        this.Stock = Stock;
    }
    
    public double getPrecio (){
        return this.Precio;
    }
    
    public void setPrecio(double Precio){
        this.Precio = Precio;
    }
}
