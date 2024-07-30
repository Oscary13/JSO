/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PL;

import ML.Direccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Alien 2
 */
public class Usuario {

    public static Scanner entrada = new Scanner(System.in);
    public static ML.Result result;

    public static void Add() throws ParseException {
        System.out.println("-------------------------------------------------");
        System.out.println("                  AGREGAR USUARIO");
        System.out.println("-------------------------------------------------");
        System.out.println("Ingresa el nombre:");
        String nombre = entrada.nextLine();

        System.out.println("Ingresa el apellido paterno:");
        String apellidoPaterno = entrada.nextLine();

        System.out.println("Ingresa el apellido materno:");
        String apellidoMaterno = entrada.nextLine();

        System.out.println("Ingresa la fecha de nacimiento DD-MM-YYYY");
        String fecha = entrada.nextLine();
        Date fechaNacimiento = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
            System.out.println("Ingresa la CURP");
            String cURP = entrada.nextLine();

            System.out.println("Ingresa el nombre de usuario:");
            String userName = entrada.nextLine();

            System.out.println("Ingresa el Email:");
            String email = entrada.nextLine();

            System.out.println("Ingresa la contraseña:");
            String password = entrada.nextLine();

            System.out.println("Ingresa el Sexo:");
            String sexo = entrada.nextLine();

            System.out.println("Ingresa el telefono:");
            String telefono = entrada.nextLine();

            System.out.println("Ingresa el Celular:");
            String celular = entrada.nextLine();

            System.out.println("Ingresa el ID del Rol:");
            int idRol = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Ingresa la calle:");
            String calle = entrada.nextLine();

            System.out.println("Ingresa numero interior:");
            String numeroInterior = entrada.nextLine();

            System.out.println("Ingresa numero exterior:");
            String numeroExterior = entrada.nextLine();

            System.out.println("Ingresa el ID de la Colonia:");
            int idColonia = entrada.nextInt();
            entrada.nextLine();

            ML.UsuarioDireccion usuarioDireccion = new ML.UsuarioDireccion();
            usuarioDireccion.Usuario = new ML.Usuario(userName, nombre, apellidoPaterno, apellidoMaterno, email, password, fechaNacimiento, sexo, telefono, celular, cURP);
            usuarioDireccion.Usuario.Rol = new ML.Rol();
            usuarioDireccion.Usuario.Rol.setIdRol(idRol);
            usuarioDireccion.Direccion = new Direccion(calle, numeroInterior, numeroExterior);
            usuarioDireccion.Direccion.Colonia = new ML.Colonia(idColonia);
            result = BL.Usuario.AddSP(usuarioDireccion);

        } catch (ParseException ex) {
            System.out.println("\n\nError al guardar fecha de Nacimiento :(");
        }
        if (result.correct) {
            System.out.println("\n\n Usuario creado correctamente");
            entrada.nextLine();
        } else {
            System.out.println(result.errorMessage + "\n\n" + result.ex);
            entrada.nextLine();

        }

    }

    public static void Delete() {
        System.out.println("-------------------------------------------------");
        System.out.println("                  ELIMINAR USUARIO");
        System.out.println("-------------------------------------------------");
        System.out.println("Ingresa el ID del usuario que desea eliminar");
        int id = entrada.nextInt();
        entrada.nextLine();
        ML.UsuarioDireccion usuarioDireccion = new ML.UsuarioDireccion();
        usuarioDireccion.Usuario = new ML.Usuario(id);
        ML.Result result = BL.Usuario.DeleteSP(usuarioDireccion);
        if (result.correct) {
            System.out.println("Registro eliminado correctamente");
        } else {
            System.out.println("No fue posible eliminar el registro");
        }

    }

    public static void Update() {
        //            System.out.println("Ingresa el ID del usuario al que deaseas actualizar el nombre");
        //            String idString = entrada.nextLine();
        //            int id = Integer.parseInt(idString);
        System.out.println("-------------------------------------------------");
        System.out.println("                 ACTUALIZAR USUARIO");
        System.out.println("-------------------------------------------------");
        System.out.println("Ingresa el ID del usuario que deaseas actualizar");
        int idUsuario = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Ingresa el nombre:");
        String nombre = entrada.nextLine();

        System.out.println("Ingresa el apellido paterno:");
        String apellidoPaterno = entrada.nextLine();

        System.out.println("Ingresa el apellido materno:");
        String apellidoMaterno = entrada.nextLine();

        System.out.println("Ingresa la fecha de nacimiento DD-MM-YYYY");
        String fecha = entrada.nextLine();
        Date fechaNacimiento = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
            System.out.println("Ingresa la CURP");
            String cURP = entrada.nextLine();

            System.out.println("Ingresa el nombre de usuario:");
            String userName = entrada.nextLine();

            System.out.println("Ingresa el Email:");
            String email = entrada.nextLine();

            System.out.println("Ingresa la contraseña:");
            String password = entrada.nextLine();

            System.out.println("Ingresa el Sexo:");
            String sexo = entrada.nextLine();

            System.out.println("Ingresa el telefono:");
            String telefono = entrada.nextLine();

            System.out.println("Ingresa el Celular:");
            String celular = entrada.nextLine();

            System.out.println("Ingresa el ID del Rol:");
            int idRol = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Ingresa la calle:");
            String calle = entrada.nextLine();

            System.out.println("Ingresa numero interior:");
            String numeroInterior = entrada.nextLine();

            System.out.println("Ingresa numero exterior:");
            String numeroExterior = entrada.nextLine();

            System.out.println("Ingresa el ID de la Colonia:");
            int idColonia = entrada.nextInt();
            entrada.nextLine();

            ML.UsuarioDireccion usuarioDireccion = new ML.UsuarioDireccion();
            usuarioDireccion.Usuario = new ML.Usuario(idUsuario, userName, nombre, apellidoPaterno, apellidoMaterno, email, password, fechaNacimiento, sexo, telefono, celular, cURP);
            usuarioDireccion.Usuario.Rol = new ML.Rol();
            usuarioDireccion.Usuario.Rol.setIdRol(idRol);
            usuarioDireccion.Direccion = new Direccion(calle, numeroInterior, numeroExterior);
            usuarioDireccion.Direccion.Colonia = new ML.Colonia(idColonia);

            result = BL.Usuario.UpdateSP(usuarioDireccion);

        } catch (ParseException ex) {
            System.out.println("Error al guardar fecha de Nacimiento :(");
        }

        if (result.correct) {
            System.out.println("\n\nRegistro actualizado correctamente");
        } else {
            System.out.println("No fue posible actualizar el dato");
            System.out.println(result.errorMessage + "\n\n" + result.ex);

        }

    }

    public static void GetAll() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 MOSTRAR TODOS LOS USUARIOS");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        ML.Result result = BL.Usuario.GetAllSP();

        if (result.correct) {
            for (Object object : result.objects) {
                if (object instanceof ML.UsuarioDireccion) {
                    ML.UsuarioDireccion usuarioDireccion = (ML.UsuarioDireccion) object;
                    System.out.println(
                            "ID Usuario: " + usuarioDireccion.Usuario.getIdUsuario()
                            + "\nNombre: " + usuarioDireccion.Usuario.getNombre()
                            + "\nApellido paterno: " + usuarioDireccion.Usuario.getApellidoPaterno()
                            + "\nApellido materno: " + usuarioDireccion.Usuario.getApellidoMaterno()
                            + "\nFecha nacimiento :" + usuarioDireccion.Usuario.getFechaNacimiento()
                            + "\nCURP: " + usuarioDireccion.Usuario.getCURP()
                            + "\nSexo :" + usuarioDireccion.Usuario.getSexo()
                            + "\nTelefono: " + usuarioDireccion.Usuario.getTelefono()
                            + "\nCelular: " + usuarioDireccion.Usuario.getCelular()
                            + "\nEmail:" + usuarioDireccion.Usuario.getEmail()
                            + "\nPassword: " + usuarioDireccion.Usuario.getPassword()
                            + "\nNombre usuario: " + usuarioDireccion.Usuario.getUserName()
                            + "\nID Rol:" + usuarioDireccion.Usuario.Rol.getIdRol()
                            + "\nRol:" + usuarioDireccion.Usuario.Rol.getNombreRol()
                            + "\nID Direccion: " + usuarioDireccion.Direccion.getIdDireccion()
                            + "\nCalle: " + usuarioDireccion.Direccion.getCalle()
                            + "\nNumero Interior: " + usuarioDireccion.Direccion.getNumeroInterior()
                            + "\nNumero Exterior: " + usuarioDireccion.Direccion.getNumeroExterior()
                            + "\nID Colonia: " + usuarioDireccion.Direccion.Colonia.getIdColonia()
                            + "\nColonia: " + usuarioDireccion.Direccion.Colonia.getNombre()
                            + "\nCP: " + usuarioDireccion.Direccion.Colonia.getCodigoPostal()
                            + "\nID Municipio: " + usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()
                            + "\nMunicipio: " + usuarioDireccion.Direccion.Colonia.Municipio.getNombre()
                            + "\nID Estado: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()
                            + "\nEstado: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.getNombre()
                            + "\nID Pais: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()
                            + "\nPais: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getNombre()
                            + "\n\n\n");
                }
            }
        } else {
            System.out.println("\n\nExiste un error :(");
        }
    }

    public static void GetByID() {
        System.out.println("Ingresa el ID del usuario al que deaseas consultar");
        int id = entrada.nextInt();
        entrada.nextLine();
        PL.OBasurtoProgramacionNCapas.ClearCMD();
        try {
            Thread.sleep(1 * 1000);
        } catch (Exception e) {
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 MOSTRAR USUARIO CON ID: " + id);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        ML.Usuario usuario = new ML.Usuario(id);
        ML.Result result = BL.Usuario.GetByIdSP(usuario);

        if (result.correct) {
            if (result.object instanceof ML.UsuarioDireccion) {
                ML.UsuarioDireccion usuarioDireccion = (ML.UsuarioDireccion) result.object;
                System.out.println(
                        "ID Usuario: " + usuarioDireccion.Usuario.getIdUsuario()
                        + "\nNombre: " + usuarioDireccion.Usuario.getNombre()
                        + "\nApellido paterno: " + usuarioDireccion.Usuario.getApellidoPaterno()
                        + "\nApellido materno: " + usuarioDireccion.Usuario.getApellidoMaterno()
                        + "\nFecha nacimiento :" + usuarioDireccion.Usuario.getFechaNacimiento()
                        + "\nCURP: " + usuarioDireccion.Usuario.getCURP()
                        + "\nSexo :" + usuarioDireccion.Usuario.getSexo()
                        + "\nTelefono: " + usuarioDireccion.Usuario.getTelefono()
                        + "\nCelular: " + usuarioDireccion.Usuario.getCelular()
                        + "\nEmail:" + usuarioDireccion.Usuario.getEmail()
                        + "\nPassword: " + usuarioDireccion.Usuario.getPassword()
                        + "\nNombre usuario: " + usuarioDireccion.Usuario.getUserName()
                        + "\nID Rol:" + usuarioDireccion.Usuario.Rol.getIdRol()
                        + "\nRol:" + usuarioDireccion.Usuario.Rol.getNombreRol()
                        + "\nID Direccion: " + usuarioDireccion.Direccion.getIdDireccion()
                        + "\nCalle: " + usuarioDireccion.Direccion.getCalle()
                        + "\nNumero Interior: " + usuarioDireccion.Direccion.getNumeroInterior()
                        + "\nNumero Exterior: " + usuarioDireccion.Direccion.getNumeroExterior()
                        + "\nID Colonia: " + usuarioDireccion.Direccion.Colonia.getIdColonia()
                        + "\nColonia: " + usuarioDireccion.Direccion.Colonia.getNombre()
                        + "\nCP: " + usuarioDireccion.Direccion.Colonia.getCodigoPostal()
                        + "\nID Municipio: " + usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()
                        + "\nMunicipio: " + usuarioDireccion.Direccion.Colonia.Municipio.getNombre()
                        + "\nID Estado: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()
                        + "\nEstado: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.getNombre()
                        + "\nID Pais: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()
                        + "\nPais: " + usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getNombre()
                        + "\n\n\n");

            }
        } else {
            System.out.println("\n\nExiste un error :(");
        }

    }
}
