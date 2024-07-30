/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Alien 2
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String GetAllSP(Model model) {
        logger.info("Hola desde el controaldor");
        model.addAttribute("usuarioBusqueda", new Usuario());
        String url = "http://localhost:8081/usuarioapi";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Usuario> httpEntityUsuarioDireccion = new HttpEntity<>(new Usuario("", "", ""));
        ResponseEntity<Result<List<UsuarioDireccion>>> responseEntity
                = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        httpEntityUsuarioDireccion,
                        new ParameterizedTypeReference<Result<List<UsuarioDireccion>>>() {
                });

        List<UsuarioDireccion> respuesta = (List<UsuarioDireccion>) responseEntity.getBody().object;
        model.addAttribute("usuariosDireccion", (List<UsuarioDireccion>) responseEntity.getBody().object);
        return "ListadoUsuarios";
    }

    @PostMapping
    public String GetAllSP(Model model, @ModelAttribute("usuarioBusqueda") Usuario usuarioBusqueda) {
        logger.info("Hola desde el controaldor");
//        Result result = usuarioDAOImplementation.GetAllSP();
        model.addAttribute("usuarioBusqueda", usuarioBusqueda);
        String url = "http://localhost:8081/usuarioapi";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> httpEntityUsuarioDireccion = new HttpEntity<>(usuarioBusqueda, headers);
        ResponseEntity<Result<List<UsuarioDireccion>>> responseEntity
                = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        httpEntityUsuarioDireccion,
                        new ParameterizedTypeReference<Result<List<UsuarioDireccion>>>() {
                });

        List<UsuarioDireccion> respuesta = (List<UsuarioDireccion>) responseEntity.getBody().object;
        model.addAttribute("usuariosDireccion", (List<UsuarioDireccion>) responseEntity.getBody().object);
        return "ListadoUsuarios";
    }

    @GetMapping("form/{idUsuario}")
    public String Form(@PathVariable int idUsuario, Model model) {

        String url = "http://localhost:8081/paisapi/getall";
        ResponseEntity<Result<List<Pais>>> responseEntityPais
                = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Result<List<Pais>>>() {
                });
        model.addAttribute("paises", (List<Pais>) responseEntityPais.getBody().object);
        url = "http://localhost:8081/rolapi/getall";
        ResponseEntity<Result<List<Rol>>> responseEntityRol
                = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Result<List<Rol>>>() {
                });
//        Result<Rol> result = responseEntityRol.getBody();
        model.addAttribute("roles", (List<Rol>) responseEntityRol.getBody().object);
        if (idUsuario == 0) {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("usuarioDireccion", usuarioDireccion);
        } else {
//            Result result = usuarioDAOImplementation.GetByIdJPA(idUsuario);
            url = "http://localhost:8081/usuarioapi/usuariobyid";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idUsuario", idUsuario);
            ResponseEntity<Result<UsuarioDireccion>> responseEntityUsuario
                    = restTemplate.exchange(
                            uriBuilder.toUriString(),
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<Result<UsuarioDireccion>>() {
                    });
//            if (result.correct) {
            model.addAttribute("usuarioDireccion", (UsuarioDireccion) responseEntityUsuario.getBody().object);
            UsuarioDireccion usuarioDireccion = (UsuarioDireccion) responseEntityUsuario.getBody().object;

            if (usuarioDireccion.Direccion != null) {
                url = "http://localhost:8081/estadoapi/getbyidpais";
                uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idPais", usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais());
                ResponseEntity<Result<List<Estado>>> responseEntityEstado
                        = restTemplate.exchange(
                                uriBuilder.toUriString(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<Result<List<Estado>>>() {
                        });
                model.addAttribute("estados", (List<Estado>) responseEntityEstado.getBody().object);
                url = "http://localhost:8081/municipioapi/getbyidestado";
                uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idEstado", usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado());
                ResponseEntity<Result<List<Municipio>>> responseEntityMunicipio
                        = restTemplate.exchange(
                                uriBuilder.toUriString(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<Result<List<Municipio>>>() {
                        });
                model.addAttribute("municipios", (List<Municipio>) responseEntityMunicipio.getBody().object);
                url = "http://localhost:8081/coloniaapi/getbyidmunicipio";
                uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idMunicipio", usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio());
                ResponseEntity<Result<List<Colonia>>> responseEntityColonia
                        = restTemplate.exchange(
                                uriBuilder.toUriString(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<Result<List<Colonia>>>() {
                        });
                model.addAttribute("colonias", (List<Colonia>) responseEntityColonia.getBody().object);
            }
//
//            }
        }

        return "FormularioUsuario";

    }

    @PostMapping("/form")
    public String Form(@ModelAttribute UsuarioDireccion usuarioDireccion, BindingResult bindingResult, @RequestParam("imagenUsuario") MultipartFile imagenUsuario, Model model) throws IOException {

//        if (bindingResult.hasErrors()) {
//            model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().object);
//            model.addAttribute("colonias", (List<Colonia>) coloniaDAOImplementation.GetAll().object);
//            model.addAttribute("usuarioDireccion", usuarioDireccion);
//            return "FormularioUsuario";
//        }
        Result result = new Result();
        if (usuarioDireccion.Usuario.getIdUsuario() == 0) {
            try {
                if (!imagenUsuario.isEmpty()) {
                    byte[] fileBites = imagenUsuario.getBytes();
                    String base64ImagenUsuario = Base64.encodeBase64String(fileBites);
                    usuarioDireccion.Usuario.setImagen(base64ImagenUsuario);
                } else {
                    usuarioDireccion.Usuario.setImagen(null);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
            try {
                String url = "http://localhost:8081/usuarioapi/add";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<UsuarioDireccion> httpEntityUsuarioDireccion = new HttpEntity<>(usuarioDireccion);
                ResponseEntity<UsuarioDireccion> responseEntity
                        = restTemplate.exchange(
                                url,
                                HttpMethod.POST,
                                httpEntityUsuarioDireccion,
                                new ParameterizedTypeReference<UsuarioDireccion>() {
                        });
//            result = usuarioDAOImplementation.AddJPA(usuarioDireccion);
                result.correct = true;
            } catch (Exception e) {
                result.correct = false;
            }
            logger.info("Inserción correcta");
        } else {
            String url = "http://localhost:8081/usuarioapi/usuariobyid";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idUsuario", usuarioDireccion.Usuario.getIdUsuario());
            ResponseEntity<Result<UsuarioDireccion>> responseEntityUsuario
                    = restTemplate.exchange(
                            uriBuilder.toUriString(),
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<Result<UsuarioDireccion>>() {
                    });

            UsuarioDireccion usuarioDireccionConsulta = (UsuarioDireccion) responseEntityUsuario.getBody().object;

            if (usuarioDireccionConsulta.Usuario.getImagen() != null && imagenUsuario.isEmpty()) {
                usuarioDireccion.Usuario.setImagen(usuarioDireccionConsulta.Usuario.getImagen());
                System.out.println("Entre");
            } else {
                try {
                    if (!imagenUsuario.isEmpty()) {
                        byte[] fileBites = imagenUsuario.getBytes();
                        String base64ImagenUsuario = Base64.encodeBase64String(fileBites);
                        usuarioDireccion.Usuario.setImagen(base64ImagenUsuario);
                    } else {
                        usuarioDireccion.Usuario.setImagen(null);
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            url = "http://localhost:8081/usuarioapi/update";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UsuarioDireccion> httpEntityUsuarioDireccion = new HttpEntity<>(usuarioDireccion);
            ResponseEntity<UsuarioDireccion> responseEntity
                    = restTemplate.exchange(
                            url,
                            HttpMethod.PUT,
                            httpEntityUsuarioDireccion,
                            new ParameterizedTypeReference<UsuarioDireccion>() {
                    });
            logger.info("Actualización correcta");
            result.correct = true;
        }
        if (result.correct) {
            return "redirect:/usuario";
        } else {
            return "FormularioUsuario";
        }

    }

    @GetMapping("/eliminar/{idUsuario}")
    public String Eliminar(@PathVariable int idUsuario) {
        String url = "http://localhost:8081/usuarioapi/delete";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("idUsuario", idUsuario);

        ResponseEntity responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.DELETE,
                null,
                String.class);
        return "redirect:/usuario";
    }
}
