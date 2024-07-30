/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Alien 2
 */
public class RolRowMapper implements RowMapper<Rol> {

    @Override
    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rol rol = new Rol();
        rol.setIdRol(rs.getInt("IdRol"));
        rol.setNombre(rs.getString("Nombre"));
        
        return rol;

    }
    
}
