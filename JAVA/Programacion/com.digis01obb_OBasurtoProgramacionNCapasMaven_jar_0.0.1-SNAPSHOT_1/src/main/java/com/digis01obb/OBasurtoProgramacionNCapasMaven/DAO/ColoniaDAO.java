/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import java.util.List;


/**
 *
 * @author Alien 2
 */
public interface ColoniaDAO {
//    Result GetAll();
    Result GetByIdMunicipio(int idMunicipio);
    
}
