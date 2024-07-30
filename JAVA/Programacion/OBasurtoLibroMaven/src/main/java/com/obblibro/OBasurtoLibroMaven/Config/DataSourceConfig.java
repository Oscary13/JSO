/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obblibro.OBasurtoLibroMaven.Config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alien 2
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataManagerDataSource = new DriverManagerDataSource();
        dataManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
        dataManagerDataSource.setUsername("OBasurtoLibro");
        dataManagerDataSource.setPassword("password1");
        
        
        
        return dataManagerDataSource;
    }
    
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
}
