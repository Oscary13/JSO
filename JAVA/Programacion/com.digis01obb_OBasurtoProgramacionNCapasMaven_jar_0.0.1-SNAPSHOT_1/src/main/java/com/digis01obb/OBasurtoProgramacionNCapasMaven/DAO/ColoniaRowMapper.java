/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Alien 2
 */
public class ColoniaRowMapper implements RowMapper<Colonia>{

    @Override
    public Colonia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Colonia colonia = new Colonia();
        colonia.setIdColonia(rs.getInt("IdColonia"));
        colonia.setNombre(rs.getString("Nombre"));
        colonia.setCodigoPostal(rs.getString("CodigoPostal"));

        return colonia;
    }
    
    
}
