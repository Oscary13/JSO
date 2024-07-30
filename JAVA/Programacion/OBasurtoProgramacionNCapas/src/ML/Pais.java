/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class Pais {

    private int IdPais;
    private String Nombre;

    public Pais() {

    }

    public Pais(int IdPais, String Nombre) {
        this.IdPais = IdPais;
        this.Nombre = Nombre;
    }

    public Pais(String Nombre) {
        this.Nombre = Nombre;
    }

    public Pais(int IdPais) {
        this.IdPais = IdPais;
    }

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int IdPais) {
        this.IdPais = IdPais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
