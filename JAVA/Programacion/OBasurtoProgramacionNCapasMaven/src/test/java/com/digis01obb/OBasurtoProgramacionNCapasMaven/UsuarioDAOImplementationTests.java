/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Alien 2
 */
@SpringBootTest
public class UsuarioDAOImplementationTests {
    
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @Mock
    private UsuarioDAOImplementation usuarioDAOImplementationMock;
    
    
    
    @Test
    public void GetAllTest(){
//        UsuarioDAOImplementation usuarioDAOImplementation = new UsuarioDAOImplementation();
        Result result = usuarioDAOImplementation.GetAllView(new Usuario("", "","",new Rol("")));
        assertNotNull(result);
        assertTrue(result.correct);
        assertNotNull(result.object);
        assertFalse(((List<UsuarioDireccion>) result.object).isEmpty());
        
    }
    
    @Test
    public void AddTest(){
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setNombre("Oscarya");
        usuarioDireccion.Usuario.setApellidoPaterno("Basurto");
        usuarioDireccion.Usuario.setUserName("aaaaaaoaabaaa");
        usuarioDireccion.Usuario.setFechaNacimiento(new Date());
        usuarioDireccion.Usuario.setApellidoMaterno("Basurto");
        usuarioDireccion.Usuario.setEmail("oaaaab@gmail.com");
        usuarioDireccion.Usuario.setPassword("password01ss");
        usuarioDireccion.Usuario.setSexo("H");
        usuarioDireccion.Usuario.setTelefono("5566997788");
        usuarioDireccion.Usuario.setCURP("55889977440");
        usuarioDireccion.Usuario.Rol = new Rol();
        usuarioDireccion.Usuario.Rol.setIdRol(2);
        usuarioDireccion.Usuario.setImagen(null);
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setCalle("Cipresses");
        usuarioDireccion.Direccion.setNumeroExterior("26171");
        usuarioDireccion.Direccion.setNumeroInterior("34344");
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(1);
        
        
       Result expectedResult = new Result();
       expectedResult.correct = true;
       when(usuarioDAOImplementationMock.AddJPA(usuarioDireccion)).thenReturn(expectedResult);
       Result result = usuarioDAOImplementationMock.AddJPA(usuarioDireccion);
       verify(usuarioDAOImplementationMock).AddJPA(usuarioDireccion);
       assertTrue(result.correct);
    }

}
