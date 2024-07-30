/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebascerti;

/**
 *
 * @author Alien 2
 */
class Overloading {
    int x(double d){
        System.out.println("one");
        return 0;
    }
//    String x(double  d){
//         System.out.println("two");
//         return null;
//    }
//    
//    double x(double d){
//        System.out.println("thee");
//        return 0.0;
//    }
    
    public static void main(String[] args) {
        new Overloading().x(4.0);
    }
}
