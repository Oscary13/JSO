/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ML;

/**
 *
 * @author Alien 2
 */
public class ProductoElectronico extends Producto{

//    private int IdProductoElectronico;
    private String Marca;
    private String Modelo;

//    public int getIdProductoElectronico() {
//        return this.IdProductoElectronico;
//    }
//
//    public void setIdProductoElectronico(int IdProductoElectronico) {
//        this.IdProductoElectronico = IdProductoElectronico;
//    }
    @Override
    public void  Descripcion(){
        super.Descripcion();
        System.out.println(" Electronico");
    }
    
    public void Descripcion(String marca){
        super.Descripcion();
        System.out.println(" Electronico de la marca " + marca);
    }
    
    public String getMarca() {
        return this.Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
    
    public String getModelo(){
        return this.Modelo;
    }
    
    public void setModelo(String Modelo){
        this.Modelo = Modelo;
    }
}
