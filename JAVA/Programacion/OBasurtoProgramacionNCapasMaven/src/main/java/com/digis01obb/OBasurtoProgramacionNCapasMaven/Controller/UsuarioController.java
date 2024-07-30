/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.Controller;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.ColoniaDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.RolDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;
import jakarta.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementations;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @GetMapping
    public String GetAllSP(Model model) {
        logger.info("Hola desde el controaldor");
//        Result result = usuarioDAOImplementation.GetAllSP();
        model.addAttribute("usuarioBusqueda", new Usuario());
//        Result result = usuarioDAOImplementation.GetAllJPA(new Usuario("", "", ""));
        Result result = usuarioDAOImplementation.GetAllView(new Usuario("", "", "", new Rol("")));
        if (result.correct) {
            model.addAttribute("usuariosDireccion", (List<UsuarioDireccion>) result.object);
        }
        return "ListadoUsuarios";
    }

    @PostMapping
    public String GetAllSP(Model model, @ModelAttribute("usuarioBusqueda") Usuario usuarioBusqueda) {
        logger.info("Hola desde el controaldor");
//        Result result = usuarioDAOImplementation.GetAllSP();
        model.addAttribute("usuarioBusqueda", usuarioBusqueda);
        Result result = usuarioDAOImplementation.GetAllView(usuarioBusqueda);
        if (result.correct) {
            model.addAttribute("usuariosDireccion", (List<UsuarioDireccion>) result.object);
        }
        return "ListadoUsuarios";
    }

    @GetMapping("form/{idUsuario}")
    public String Form(@PathVariable int idUsuario, Model model) {

        model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().object);
//        model.addAttribute("colonias", (List<Colonia>) coloniaDAOImplementation.GetByIdMunicipio(1).object);
        model.addAttribute("paises", (List<Pais>) paisDAOImplementation.GetAllJPA().object);
//        model.addAttribute("estados", (List<Estado>) estadoDAOImplementations.GetByIdPais(1).object);
//        model.addAttribute("municipios", (List<Municipio>) municipioDAOImplementation.GetByIdEstado(1).object);

        if (idUsuario == 0) {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("usuarioDireccion", usuarioDireccion);
        } else {
            Result result = usuarioDAOImplementation.GetByIdJPA(idUsuario);
            if (result.correct) {
                model.addAttribute("usuarioDireccion", (UsuarioDireccion) result.object);
                UsuarioDireccion usuarioDireccion = (UsuarioDireccion) result.object;

                if (usuarioDireccion.Direccion != null) {
                    model.addAttribute("estados", (List<Estado>) estadoDAOImplementations.GetByIdPais(usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).object);
                    model.addAttribute("municipios", (List<Municipio>) municipioDAOImplementation.GetByIdEstado(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).object);
                    model.addAttribute("colonias", (List<Colonia>) coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()).object);
                }

            }
        }

        return "FormularioUsuario";

    }

    @PostMapping("/form")
    public String Form(@Valid @ModelAttribute UsuarioDireccion usuarioDireccion, BindingResult bindingResult, @RequestParam("imagenUsuario") MultipartFile imagenUsuario, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().object);
            model.addAttribute("colonias", (List<Colonia>) coloniaDAOImplementation.GetAll().object);
            model.addAttribute("usuarioDireccion", usuarioDireccion);
            return "FormularioUsuario";
        }

        Result result = null;
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
            result = usuarioDAOImplementation.AddTransaccion(usuarioDireccion);
            logger.info("Inserción correcta");
        } else {
            UsuarioDireccion usuarioDireccionConsulta = (UsuarioDireccion) usuarioDAOImplementation.GetByIdJPA(usuarioDireccion.Usuario.getIdUsuario()).object;

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
            result = usuarioDAOImplementation.UpdateJPA(usuarioDireccion);
            logger.info("Actualización correcta");
        }
        if (result.correct) {
            return "redirect:/usuario";
        } else {
            model.addAttribute("usuarioDireccion", usuarioDireccion);
            model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().object);
            model.addAttribute("paises", (List<Pais>) paisDAOImplementation.GetAllJPA().object);
            model.addAttribute("estados", (List<Estado>) estadoDAOImplementations.GetByIdPais(usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).object);
            model.addAttribute("municipios", (List<Municipio>) municipioDAOImplementation.GetByIdEstado(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).object);
            model.addAttribute("colonias", (List<Colonia>) coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio()).object);
            return "FormularioUsuario";
        }

    }

    @GetMapping("/eliminar/{idUsuario}")
    public String Eliminar(@PathVariable int idUsuario) {
        Result result = usuarioDAOImplementation.DeleteJPA(idUsuario);
        if (result.correct) {
            logger.info("Eliminación correcta correcta");
        }
        return "redirect:/usuario";
    }

    @GetMapping("/actualizar/{idUsuario}")
    public String GetByIdSP(@PathVariable int idUsuario, Model model) {
        logger.info("Hola desde el controaldor");
        Result result = usuarioDAOImplementation.GetByIdJPA(idUsuario);
        if (result.correct) {
            model.addAttribute("usuariosDireccion", (UsuarioDireccion) result.object);
        }
        return "ListadoUsuarios";
    }

    @GetMapping("/estados")
    @ResponseBody
    public List<Estado> EstadosGetByIdPais(@RequestParam int idPais) {
        List<Estado> estados = new ArrayList<>();
        estados = (List<Estado>) estadoDAOImplementations.GetByIdPais(idPais).object;
        return estados;
    }

    @GetMapping("/municipios")
    @ResponseBody
    public List<Municipio> MunicipiosGetByIdEstado(@RequestParam("idEstado") int idEstado) {
        List<Municipio> municipios = new ArrayList<>();
        municipios = (List<Municipio>) municipioDAOImplementation.GetByIdEstado(idEstado).object;
        return municipios;
    }

    @GetMapping("/colonias")
    @ResponseBody
    public List<Colonia> ColoniasGetByIdMunicipio(@RequestParam("idMunicipio") int idMunicipio) {
        List<Colonia> colonias = new ArrayList<>();
        colonias = (List<Colonia>) coloniaDAOImplementation.GetByIdMunicipio(idMunicipio).object;
        return colonias;
    }
}
