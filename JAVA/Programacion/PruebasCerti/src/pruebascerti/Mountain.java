/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebascerti;

/**
 *
 * @author Alien 2
 */
public class Mountain extends Rock{

    /**
     * @param args the command line arguments
     */
    Mountain(){
        super("granite ");
        new Rock("granite ");
    }
    
    public static void main(String[] args) {
        new Mountain();
    }
    
    
    
}

class Atom{
Atom(){
    System.out.print("atom ");
}
}

class Rock extends Atom{
    Rock(String type){
        System.out.print(type);
    }
}

