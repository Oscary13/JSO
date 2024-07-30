package com.obblibro.OBasurtoLibroMaven.ML;



import com.obblibro.OBasurtoLibroMaven.ML.Editorial;

/**
 *
 * @author Alien 2
 */
public class Libro {
    private int IdLibro;
    private String Nombre;
    private String Descripcion;
    private int NumeroPaginas;
    public Editorial Editorial;

    public int getIdLibro() {
        return this.IdLibro;
    }
    public void setIdLibro(int IdLibro){
        this.IdLibro = IdLibro;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
    public void setDescripcion(String Descripcion){
        this.Descripcion = Descripcion;
    }
    
    public  int getNumeroPaginas(){
        return this.NumeroPaginas;
    }
    
    public void setNumeroPaginas(int NumeroPaginas){
        this.NumeroPaginas = NumeroPaginas;
    }
    
    public Editorial getEditorial(){
        return this.Editorial;
    }
    
    public void setEditorial(Editorial Editorial){
        this.Editorial = Editorial;
    }
    
         
}
