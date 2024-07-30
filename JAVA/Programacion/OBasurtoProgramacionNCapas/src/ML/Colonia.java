/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class Colonia {
   private int IdColonia;
   private String Nombre;
   private String CodigoPostal;
   public Municipio Municipio;
   
   public Colonia() {
    }

    public Colonia(int IdColonia, String Nombre, String CodigoPostal) {
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }
    public Colonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }
    public Colonia( String Nombre, String CodigoPostal) {
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }
   
   
}
