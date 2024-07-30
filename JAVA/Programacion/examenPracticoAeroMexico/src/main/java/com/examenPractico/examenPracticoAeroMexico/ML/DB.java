/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.ML;

import jakarta.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class DB {

    private static DB instance;
    private List<Usuario> Usuarios;
    private List<Vuelo> Vuelos;
    private List<Pasajero> Pasajeros;

    private DB() throws ParseException {
        Usuarios = new ArrayList<>();
        inicializarUsuarios();
        Vuelos = new ArrayList<>();
        iniciarVuelos();
        Pasajeros = new ArrayList<>();
    }

    public static DB getInstance() throws ParseException {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public void inicializarUsuarios() {
        Usuarios.add(new Usuario("admin", "admin123"));
        Usuarios.add(new Usuario("user1", "password1"));
        Usuarios.add(new Usuario("user2", "password2"));
    }

    public void iniciarVuelos() throws ParseException {
        agregarVuelo(new Vuelo("152", "MX", "DR", StringToDate("2002-08-28 00:00:00")));   
        agregarVuelo(new Vuelo("150", "MX", "GT", StringToDate("2002-08-30 00:00:00")));
        agregarVuelo(new Vuelo("122", "MX", "OC", StringToDate("2002-08-11 00:00:00")));
    }
    public static Date StringToDate(String fechaString) throws ParseException {
        Date fechaDate;
        
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fechaDate = formato.parse(fechaString);
            return fechaDate;
       
        
    }
    
    public void agregarVuelo(@Valid Vuelo vuelo){
         Vuelos.add(vuelo);
    }

    public void addUsuario(Usuario usuario) {
        Usuarios.add(usuario);
    }
    public void addPasajero (Pasajero pasajero){
        Pasajeros.add(pasajero);
    }
    
    public List<Usuario> getAllUsuario() {
        return new ArrayList<>(Usuarios);
    }
    
    public List<Vuelo> getAllVuelos(){
        return new ArrayList<>(Vuelos);
    }
    
    public List<Pasajero> getAllPasajeros(){
        return new ArrayList<>(Pasajeros);
    }

    public boolean verificarCredenciales(Usuario usuarioBody) {
        for (Usuario usuario : Usuarios) {
            if (usuario.getNombre().equals(usuarioBody.getNombre()) && usuario.getPassword().equals(usuarioBody.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
