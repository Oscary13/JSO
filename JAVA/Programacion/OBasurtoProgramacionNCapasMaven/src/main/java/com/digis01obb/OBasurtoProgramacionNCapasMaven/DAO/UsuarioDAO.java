/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;

/**
 *
 * @author Alien 2
 */
public interface UsuarioDAO {
    Result GetAllSP();
    Result AddSP(UsuarioDireccion usuarioDireccion);
    Result DeleteSP(int idUsuario);
    Result GetByIdSP(int idUsuario);
    Result UpdateSP(UsuarioDireccion usuarioDireccion);

    Result GetAllJPA(Usuario usuario);
    Result AddJPA(UsuarioDireccion usuarioDireccion);
    Result UpdateJPA(UsuarioDireccion usuarioDireccion);
    Result DeleteJPA(int idUsuario);
    Result GetByIdJPA(int idUsuario);
    
    Result AddTransaccion (UsuarioDireccion usuarioDireccion);
    
    Result ChangeStatus(int idUsuario, char estatus);
    
    Result GetAllView (Usuario usuario);

}
