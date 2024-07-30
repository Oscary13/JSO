/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class Estado {
    private int IdEstado;
    private String Nombre;
    public Pais Pais;
    
    public Estado(){
        
    }
    public Estado(int IdEstado, String Nombre){
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
    }
    public Estado(String Nombre){
        this.Nombre = Nombre;
    }
    public Estado(int IdEstado){
        this.Nombre = Nombre;
    }
    
    public int getIdEstado (){
        return IdEstado;
    }
    public void setIdEstado(int IdEstado){
        this.IdEstado = IdEstado;
    }
    
    public String getNombre(){
        return Nombre;
    }
    public void setNombre (String Nombre){
        this.Nombre = Nombre;
    }
}
