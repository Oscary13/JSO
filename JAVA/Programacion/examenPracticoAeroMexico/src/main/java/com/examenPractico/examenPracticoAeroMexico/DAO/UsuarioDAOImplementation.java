/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.DAO;

import com.examenPractico.examenPracticoAeroMexico.ML.DB;
import com.examenPractico.examenPracticoAeroMexico.ML.Result;
import com.examenPractico.examenPracticoAeroMexico.ML.Usuario;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class UsuarioDAOImplementation implements UsuarioDAO{
    private DB db;

    public UsuarioDAOImplementation(DB db) throws ParseException {
        this.db = DB.getInstance();
    }
    
    
    @Override
    public Result Add(Usuario usuario) {
        Result result = new Result();
        try {
            db.addUsuario(usuario);
            result.correct = true;
            result.object = usuario;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            List<Usuario> usuarios = db.getAllUsuario();
            result.correct = true;
            result.object = usuarios;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result Autentication(Usuario usuario) {
        Result result = new Result();
        try {
            result.correct = db.verificarCredenciales(usuario);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return  result;
    }
    
}
