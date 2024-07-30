/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class RolDAOImplementation implements RolDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result<>();
        List<Rol> roles = new ArrayList<>();
        try {
            roles = jdbcTemplate.query("SELECT IdRol, Nombre FROM Rol", new RolRowMapper());
            result.object = roles;
            result.correct= true;
//            return roles;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
