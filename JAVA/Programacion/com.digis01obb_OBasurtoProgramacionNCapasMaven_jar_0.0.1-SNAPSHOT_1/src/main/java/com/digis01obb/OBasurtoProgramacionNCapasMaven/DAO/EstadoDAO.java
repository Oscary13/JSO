/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;


/**
 *
 * @author Alien 2
 */
public interface EstadoDAO {
    Result<Object> GetByIdPais(int IdPais);
}
