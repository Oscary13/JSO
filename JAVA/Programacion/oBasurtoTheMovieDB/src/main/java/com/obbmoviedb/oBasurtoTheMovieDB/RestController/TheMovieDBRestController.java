/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obbmoviedb.oBasurtoTheMovieDB.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Alien 2
 */
@RestController
@RequestMapping("/theMovieDB")
public class TheMovieDBRestController {

    @Autowired
    private RestTemplate restTemplate;
    private String apiKey = "83de6a151443e44770d5c82687acd4b5";

    @GetMapping("/peliculasPopulares")
    public Map peliculaPopulare() {
        String endPoint = "https://api.themoviedb.org/3/movie/popular";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);

        ResponseEntity<Map> responseEntityPeliculasPopulares = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map>() {
        });

        return responseEntityPeliculasPopulares.getBody();
    }

    @GetMapping("/peliculasRecomendadas")
    public Map peliculaRecomendada() {
        String endPoint = "https://api.themoviedb.org/3/movie/top_rated";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);

        ResponseEntity<Map> responseEntityPeliculasPopulares = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map>() {
        });

        return responseEntityPeliculasPopulares.getBody();
    }

    @GetMapping("/detallesPelicula")
    public Map DetallesPelicula(@RequestParam("idPelicula") int idPelicula) {
        String endPoint = "https://api.themoviedb.org/3/movie/"+ idPelicula;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);

        ResponseEntity<Map> detallesPelicula = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map>() {
        });

        return detallesPelicula.getBody();
    }
    
    @GetMapping("/peliculaFavorita")
    public Map peliculaFavorita(HttpSession session) {
        String endPoint = "https://api.themoviedb.org/3/account/21335145/favorite/movies";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
        String sessionS = session.getAttribute("session_id").toString();
        uriBuilder = UriComponentsBuilder.fromHttpUrl(uriBuilder.toUriString()).queryParam("session_id", session.getAttribute("session_id").toString());
        String uri = uriBuilder.toUriString();
        ResponseEntity<Map> detallesPelicula = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map>() {
        });

        return detallesPelicula.getBody();
    }
    
    @PostMapping("/addFavorite")
    public Map AddFavorite(HttpSession session, @RequestBody Map map){
        
        String endPoint = "https://api.themoviedb.org/3/account/21335145/favorite";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
        String sessionS = session.getAttribute("session_id").toString();
        uriBuilder = UriComponentsBuilder.fromHttpUrl(uriBuilder.toUriString()).queryParam("session_id", session.getAttribute("session_id").toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = uriBuilder.toUriString();
        HttpEntity<Map<String,Object>> httpEntityUsuarioDireccion = new HttpEntity<Map<String,Object>>(map,headers);
        ResponseEntity<Map> responseEntity
                = restTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.POST,
                        httpEntityUsuarioDireccion,
                        new ParameterizedTypeReference<Map>() {
                });
        
        return responseEntity.getBody();
    }
    
    @GetMapping("/detailsUser")
    public Map DetailUser(HttpSession session) {
        String endPoint = "https://api.themoviedb.org/3/account/21335145";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
        String sessionS = session.getAttribute("session_id").toString();
        uriBuilder = UriComponentsBuilder.fromHttpUrl(uriBuilder.toUriString()).queryParam("session_id", session.getAttribute("session_id").toString());
        String uri = uriBuilder.toUriString();
        ResponseEntity<Map> detallesPelicula = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map>() {
        });

        return detallesPelicula.getBody();
    }
    
    @GetMapping("/productoras")
    public ResponseEntity<Map> Productoras(@RequestParam("idPelicula") int idPelicula) {
        String endPoint = "https://api.themoviedb.org/3/movie/"+idPelicula;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<Map> detallesPelicula = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Map>() {
        });

        return new ResponseEntity<Map>(detallesPelicula.getBody(), headers, HttpStatus.OK);
    }
}
