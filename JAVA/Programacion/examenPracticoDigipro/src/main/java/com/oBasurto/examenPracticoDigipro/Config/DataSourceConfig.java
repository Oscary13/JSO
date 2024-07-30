/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.Config;
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
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
        driverManagerDataSource.setUsername("OBBControlEscolar");
        driverManagerDataSource.setPassword("password1");
        return driverManagerDataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource){
        return new JdbcTemplate(datasource);
    }
    
}
