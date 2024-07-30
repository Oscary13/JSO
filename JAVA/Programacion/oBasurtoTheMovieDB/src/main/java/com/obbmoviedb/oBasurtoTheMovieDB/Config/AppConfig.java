/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obbmoviedb.oBasurtoTheMovieDB.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Alien 2
 */
@Configuration
public class AppConfig {
    
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate (){
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        return new org.springframework.web.client.RestTemplate();
    } 
}
