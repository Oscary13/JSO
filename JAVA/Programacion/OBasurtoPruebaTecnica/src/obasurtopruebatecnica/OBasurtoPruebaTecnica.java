/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obasurtopruebatecnica;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author oscar
 */
public class OBasurtoPruebaTecnica {

    /**
     * @param args the command line arguments
     */
    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("Digita el numero de la opereción a realizar:");
            System.out.println("--------------------------------------------");
            System.out.println("1) Palindromo");
            System.out.println("2) Serie Fibonacci");
            System.out.println("3) Ordenamiento creciente de areglo");
            System.out.println("4) Salir");
            System.out.println("--------------------------------------------");
            opcion = entrada.nextInt();
            entrada.nextLine();
            ClearConsole();
            switch (opcion) {
                case 1:
                    Palindromo();
                    break;
                case 2:
                    Fibonacci();
                    break;
                case 3:
                    ordenamientoBurbuja();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Respuesta fuera del rango");
            }
            if (opcion != 4) {
                System.out.println("\n\nPresiona enter para continuar...");
                entrada.nextLine();
            }
            ClearConsole();
        } while (opcion != 4);

        System.out.println("BYE :)");
    }

    public static void Palindromo() {
        System.out.println("Ingresa una palabra:");
        String palabra = entrada.nextLine().toLowerCase().replaceAll("[^a-z]", "");
        String palindromo = "";
        for (int i = palabra.length() - 1; i >= 0; i--) {
            palindromo = palindromo + "" + palabra.charAt(i);
        }
        if (palindromo.equals(palabra)) {
            System.out.println("\n\n¡La palabra es un palindromo!\n\n");
        } else {
            System.out.println("\n\n¡La palabra no es un palindromo!\n\n");
        }
        System.out.println("Palabra ingresada: " + palabra);
        System.out.println("Palabra escrita al revés: " + palindromo);
    }

    public static void Fibonacci() {
        System.out.print("Ingrese el número límite para la serie Fibonacci: ");
        int limite = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Serie Fibonacci hasta " + limite + ":\n\n");
        int[] arregloFibonacci = new int[limite];
        arregloFibonacci[0] = 0;
        arregloFibonacci[1] = 1;
        System.out.print(arregloFibonacci[0]);
        System.out.print(", ");
        System.out.print(arregloFibonacci[1]);
        for (int i = 2; i < limite; i++) {
            arregloFibonacci[i] = arregloFibonacci[i - 2] + arregloFibonacci[i - 1];
            System.out.print(", " + arregloFibonacci[i]);
        }
        System.out.println();
    }

    public static void ordenamientoBurbuja() {
        System.out.print("Ingresa la cantidad de numeros a ordenar: ");
        int numeroElementos = entrada.nextInt();
        long arreglo[] = new long[numeroElementos];
        long valorTemporalArreglo;
        System.out.println();
        for (int i = 0; i < numeroElementos; i++) {
            System.out.print("ingresa el numero " + (i + 1) + " : ");
            arreglo[i] = entrada.nextInt();
        }
        entrada.nextLine();
        System.out.println("\n");
        System.out.print("Los numeros en desorden son: ");
        for (int i = 0; i < numeroElementos; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println("\n");
        for (int i = 0; i < (numeroElementos - 1); i++) {
            for (int j = 0; j < (numeroElementos - 1); j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    valorTemporalArreglo = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = valorTemporalArreglo;
                }
            }
        }
        System.out.println();
        System.out.print("Los numeros ordenados crecientemente son: ");
        for (int i = 0; i < numeroElementos; i++) {
            System.out.print(arreglo[i] + ", ");
        }
    }

    public static void ClearConsole() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

    }

}
