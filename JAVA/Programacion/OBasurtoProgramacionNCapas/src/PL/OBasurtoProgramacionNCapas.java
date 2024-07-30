/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PL;

import static PL.Usuario.entrada;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Alien 2
 */
public class OBasurtoProgramacionNCapas {

    /**
     * @param args the command line arguments
     */
    public static String query;

    public static void main(String[] args) throws ParseException {
        int dato;
        do {
            try {
                Thread.sleep(1 * 1000);
            } catch (Exception e) {
            }
            ClearCMD();
            try {
                Thread.sleep(1 * 1000);
            } catch (Exception e) {
            }
            menu();
            dato = entrada.nextInt();
            entrada.nextLine();
            ClearCMD();

            try {
                Thread.sleep(1 * 1000);
            } catch (Exception e) {
            }
            switch (dato) {
                case 1:

                    PL.Usuario.Add();
                    break;
                case 2:
                    PL.Usuario.Delete();
                    break;
                case 3:
                    PL.Usuario.Update();
                    break;
                case 4:
                    PL.Usuario.GetAll();
                    break;
                case 5:
                    PL.Usuario.GetByID();
                    break;
                case 6:
                    System.out.println("Bye  :)");
                    break;
                default:
                    System.out.println("Respuesta incorrecta");
            }
            if (dato != 6) {
                System.out.println("\n\n\nPrecione ENTER para continuar...");
                entrada.nextLine();
            }

        } while (dato != 6);

    }

    public static void menu() {
        System.out.println("Seleccione una opción del menu:");
        System.out.println("-------------------------------");
        System.out.println("(1) Agregar usuario");
        System.out.println("(2) Eliminar usuario");
        System.out.println("(3) Auctualizar usuario por ID");
        System.out.println("(4) Counsultar todos los usuarios");
        System.out.println("(5) Counsultar usuario por ID");
        System.out.println("(6) Salir");
        System.out.println("-------------------------------");
    }

    public static void ClearCMD() {
        try {
            java.awt.Robot pressbot = new java.awt.Robot();
            pressbot.keyPress(17); // Holds CTRL key.
            pressbot.keyPress(76); // Holds L key.
            pressbot.keyRelease(17); // Releases CTRL key.
            pressbot.keyRelease(76); // Releases L key.
        } catch (java.awt.AWTException e) {
            System.out.println(e);
        }
    }

}
