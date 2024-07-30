/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obasurtodia1;

import java.util.Scanner;

/**
 *
 * @author Alien 2
 */
public class OBasurtoDia1 {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        
        operaciones();
    }
    
    public static void operaciones(){
        Scanner entrada = new Scanner (System.in);
        
        System.out.println("Que operación deseas realiuzar");
        System.out.println("------------------------------");
        System.out.println("(1) SUMA");
        System.out.println("(2) RESTA");
        System.out.println("(3) MULTIPLICACIÓN");
        System.out.println("(4) DIVICIÓN");
        System.out.println("(5) FACTORIAL RECUERSIVO");
        System.out.println("------------------------------");
        int respuesta = entrada.nextInt();
        
        System.out.flush();
        if (respuesta > 0 && respuesta < 5 ) {
            System.out.println("Ingresa el numero 1");
            double numero1 = entrada.nextDouble();
            System.out.println("Ingresa el numero 2");
            double numero2 = entrada.nextDouble();//Invocamos un método sobre un objeto Scanner
            double resultado;
            switch (respuesta) {
                case 1:
                resultado  = numero1 + numero2;
                break;
                case 2:
                resultado  = numero1 - numero2;
                break;
                case 3:
                resultado  = numero1 * numero2;
                break;
                case 4:
                resultado  = numero1 / numero2;
                break;
                default:
                    resultado = 0;
                    System.out.println("El dato ingresado es incorrecto");
                    break;
            }
            
            System.out.println("El resultado es:" + resultado);
        } 
        else if (respuesta == 5) {
            System.out.println("Ingrese un numero");
            int numero1 = entrada.nextInt();
            
            System.out.println("El Factorial recursivo es:"+ factorialRecursivo(numero1));
        }
        else{
            System.out.println("El dato ingresado es incorrecto");
        }
        
        
         //Creación de un objeto Scanner
        
    }
    
    public static long factorialRecursivo(int num1){
        
        if (num1 ==0 || num1 == 1) {
            return 1;
        }else{
            return num1 * factorialRecursivo(num1-1);
        }
        
        
    }
    
    
}
