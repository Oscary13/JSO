/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Estado;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Municipio;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Pais;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;
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
        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setIdUsuario(rs.getInt("IdUsuario"));
        usuarioDireccion.Usuario.setUserName(rs.getString("UserName"));
        usuarioDireccion.Usuario.setNombre(rs.getString("NombreUsuario"));
        usuarioDireccion.Usuario.setApellidoPaterno(rs.getString("ApellidoPaterno"));
        usuarioDireccion.Usuario.setApellidoMaterno(rs.getString("ApellidoMaterno"));
        usuarioDireccion.Usuario.setEmail(rs.getString("Email"));
        usuarioDireccion.Usuario.setPassword(rs.getString("Password"));
        usuarioDireccion.Usuario.setFechaNacimiento(rs.getDate("FechaNacimiento"));
        usuarioDireccion.Usuario.setSexo(rs.getString("Sexo"));
        usuarioDireccion.Usuario.setTelefono(rs.getString("Telefono"));
        usuarioDireccion.Usuario.setCelular(rs.getString("Celular"));
        usuarioDireccion.Usuario.setCURP(rs.getString("CURP"));
        usuarioDireccion.Usuario.setImagen(rs.getString("Imagen"));
        usuarioDireccion.Usuario.setEstaus(rs.getString("Estatus").charAt(0));
        
        usuarioDireccion.Usuario.Rol = new Rol();
        usuarioDireccion.Usuario.Rol.setIdRol(rs.getInt("IdRol"));
        usuarioDireccion.Usuario.Rol.setNombre(rs.getString("NombreRol"));
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setIdDireccion(rs.getInt("IdDireccion"));
        usuarioDireccion.Direccion.setCalle(rs.getString("Calle"));
        usuarioDireccion.Direccion.setNumeroInterior(rs.getString("NumeroInterior"));
        usuarioDireccion.Direccion.setNumeroExterior(rs.getString("NumeroExterior"));
        
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(rs.getInt("IdColonia"));
        usuarioDireccion.Direccion.Colonia.setNombre(rs.getString("NombreColonia"));
        usuarioDireccion.Direccion.Colonia.setCodigoPostal(rs.getString("CodigoPostal"));
        usuarioDireccion.Direccion.Colonia.Municipio = new Municipio();
        usuarioDireccion.Direccion.Colonia.Municipio.setIdMunicipio(rs.getInt("IdMunicipio"));
        usuarioDireccion.Direccion.Colonia.Municipio.setNombre(rs.getString("NombreMunicipio"));
        usuarioDireccion.Direccion.Colonia.Municipio.Estado = new Estado();
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(rs.getInt("IdEstado"));
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.setNombre(rs.getString("NombreEstado"));
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais = new Pais();
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setIdPais(rs.getInt("IdPais"));
        usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.setNombre(rs.getString("NombrePais"));
        return usuarioDireccion;
    }
    
}
