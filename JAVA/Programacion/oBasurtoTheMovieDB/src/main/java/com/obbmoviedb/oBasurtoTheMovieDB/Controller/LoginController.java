/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obbmoviedb.oBasurtoTheMovieDB.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.obbmoviedb.oBasurtoTheMovieDB.ML.Login;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Alien 2
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String apiKey = "83de6a151443e44770d5c82687acd4b5";
    private String requestToken;
    private String requestTokenValidate;
    private String sessionID;

    @GetMapping("/iniciarSesion")
    public String Login(Model model) throws JsonProcessingException {
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @PostMapping("/sendLogin")
    public String Login(@ModelAttribute("Login") Login login, HttpSession session, Model model, @ModelAttribute("labelError") String labelError) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/authentication/token/new";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("api_key", apiKey);
        ResponseEntity responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                String.class);
        String response = (String) responseEntity.getBody();
        JsonNode jsonNode = objectMapper.readTree(response);
        requestToken = jsonNode.get("request_token").asText();
        login.setRequest_token(requestToken);

        String endPoint;

        try {
            endPoint = "https://api.themoviedb.org/3/authentication/token/validate_with_login";
            uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
            HttpEntity<Login> httpEntityLogin = new HttpEntity<Login>(login);
            ResponseEntity<String> responseEntityLogin = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.POST,
                    httpEntityLogin,
                    String.class
            );

            response = (String) responseEntityLogin.getBody();
            jsonNode = objectMapper.readTree(response);
            requestTokenValidate = jsonNode.get("request_token").asText();

            endPoint = "https://api.themoviedb.org/3/authentication/session/new";
            uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
            HttpEntity<Login> httpEntityLoginSession = new HttpEntity<>(new Login(requestTokenValidate));

            ResponseEntity<String> responseEntitySession = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.POST,
                    httpEntityLoginSession,
                    String.class);

            response = (String) responseEntitySession.getBody();
            jsonNode = objectMapper.readTree(response);
            sessionID = jsonNode.get("session_id").asText();
            session.setAttribute("session_id", sessionID);

            endPoint = "https://api.themoviedb.org/3/movie/popular";
            uriBuilder = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);

            ResponseEntity<Map<String, Object>> responseEntityPeliculasPopulares = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Object>>() {
            });

            Map<String, Object> populares = responseEntityPeliculasPopulares.getBody();
            List<Map<String, Object>> listaPopulares = (List<Map<String, Object>>) populares.get("results");
            endPoint = "https://api.themoviedb.org/3/account/21335145/favorite/movies";
            UriComponentsBuilder uriBuilder2 = UriComponentsBuilder.fromHttpUrl(endPoint).queryParam("api_key", apiKey);
            String sessionS = session.getAttribute("session_id").toString();
            uriBuilder2 = UriComponentsBuilder.fromHttpUrl(uriBuilder2.toUriString()).queryParam("session_id", sessionID);
            String uri = uriBuilder.toUriString();
            ResponseEntity<Map<String, Object>> responseEntityPeliculasFavoritas = restTemplate.exchange(
                    uriBuilder2.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Object>>() {
            });

            Map<String, Object> favoritas = responseEntityPeliculasFavoritas.getBody();
            List<Map<String, Object>> listaFavoritas = (List<Map<String, Object>>) favoritas.get("results");
            List<Integer> idsFavoritas = listaFavoritas.stream()
                    .map(fav -> (Integer) fav.get("id"))
                    .collect(Collectors.toList());

            // Agrega la propiedad "favorita" a las películas populares
            listaPopulares.forEach(pelicula -> {
                if (idsFavoritas.contains((Integer) pelicula.get("id"))) {
                    pelicula.put("favorita", true);
                } else {
                    pelicula.put("favorita", false);
                }
            });
//        Map<String,Object> peliculas = (Map<String,Object>) listaPopulares;
            model.addAttribute("peliculasPopulares", listaPopulares);
            return "Peliculas";
        } catch (Exception ex) {
            model.addAttribute("labelError", "¡Los datos ingresados son icorrectos!");
            return "Login";
        }
    }

    @GetMapping("/usuario")
    public String Usuario() {
        return "Usuario";
    }

}
