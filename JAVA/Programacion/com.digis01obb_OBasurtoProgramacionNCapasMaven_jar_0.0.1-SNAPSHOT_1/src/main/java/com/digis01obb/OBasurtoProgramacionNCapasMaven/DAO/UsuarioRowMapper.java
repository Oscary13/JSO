/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA.UsuarioDireccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Alien 2
 */
public class UsuarioRowMapper implements RowMapper<UsuarioDireccion>{

    @Override
    public UsuarioDireccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.usuario = new Usuario();
        usuarioDireccion.usuario.setIdUsuario(rs.getInt("IdUsuario"));
        usuarioDireccion.usuario.setUserName(rs.getString("UserName"));
        usuarioDireccion.usuario.setNombre(rs.getString("NombreUsuario"));
        usuarioDireccion.usuario.setApellidoPaterno(rs.getString("ApellidoPaterno"));
        usuarioDireccion.usuario.setApellidoMaterno(rs.getString("ApellidoMaterno"));
        usuarioDireccion.usuario.setEmail(rs.getString("Email"));
        usuarioDireccion.usuario.setPassword(rs.getString("Password"));
        usuarioDireccion.usuario.setFechaNacimiento(rs.getDate("FechaNacimiento"));
        usuarioDireccion.usuario.setSexo(rs.getString("Sexo"));
        usuarioDireccion.usuario.setTelefono(rs.getString("Telefono"));
        usuarioDireccion.usuario.setCelular(rs.getString("Celular"));
        usuarioDireccion.usuario.setCURP(rs.getString("CURP"));
        usuarioDireccion.usuario.setImagen(rs.getString("Imagen"));
        
        usuarioDireccion.usuario.Rol = new Rol();
        usuarioDireccion.usuario.Rol.setIdRol(rs.getInt("IdRol"));
        usuarioDireccion.usuario.Rol.setNombre(rs.getString("NombreRol"));
        usuarioDireccion.direccion = new Direccion();
        usuarioDireccion.direccion.setIdDireccion(rs.getInt("IdDireccion"));
        usuarioDireccion.direccion.setCalle(rs.getString("Calle"));
        usuarioDireccion.direccion.setNumeroInterior(rs.getString("NumeroInterior"));
        usuarioDireccion.direccion.setNumeroExterior(rs.getString("NumeroExterior"));
        
        usuarioDireccion.direccion.Colonia = new Colonia();
        usuarioDireccion.direccion.Colonia.setIdColonia(rs.getInt("IdColonia"));
        usuarioDireccion.direccion.Colonia.setNombre(rs.getString("NombreColonia"));
        usuarioDireccion.direccion.Colonia.setCodigoPostal(rs.getString("CodigoPostal"));
        usuarioDireccion.direccion.Colonia.Municipio = new Municipio();
        usuarioDireccion.direccion.Colonia.Municipio.setIdMunicipio(rs.getInt("IdMunicipio"));
        usuarioDireccion.direccion.Colonia.Municipio.setNombre(rs.getString("NombreMunicipio"));
        usuarioDireccion.direccion.Colonia.Municipio.Estado = new Estado();
        usuarioDireccion.direccion.Colonia.Municipio.Estado.setIdEstado(rs.getInt("IdEstado"));
        usuarioDireccion.direccion.Colonia.Municipio.Estado.setNombre(rs.getString("NombreEstado"));
        usuarioDireccion.direccion.Colonia.Municipio.Estado.Pais = new Pais();
        usuarioDireccion.direccion.Colonia.Municipio.Estado.Pais.setIdPais(rs.getInt("IdPais"));
        usuarioDireccion.direccion.Colonia.Municipio.Estado.Pais.setNombre(rs.getString("NombrePais"));
        return usuarioDireccion;
    }
    
}
