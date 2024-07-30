/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class Rol {
    
    private int IdRol;
    private String NombreRol;

    public Rol() {
    }

    public Rol(int IdRol, String NombreRol) {
        this.IdRol = IdRol;
        this.NombreRol = NombreRol;
    }
    public Rol(int IdRol) {
        this.IdRol = IdRol;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getNombreRol() {
        return NombreRol;
    }

    public void setNombreRol(String NombreRol) {
        this.NombreRol = NombreRol;
    }
    
    
    
    
    
    
}
