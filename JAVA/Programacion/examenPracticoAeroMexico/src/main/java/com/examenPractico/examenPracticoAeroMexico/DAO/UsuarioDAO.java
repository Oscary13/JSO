/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.DAO;

import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import com.examenPractico.examenPracticoAeroMexico.ML.Usuario;

/**
 *
 * @author Alien 2
 */
public interface UsuarioDAO {
    Result Add(Usuario usuario);
    Result GetAll();
    Result Autentication(Usuario usuario);
}
