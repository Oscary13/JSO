/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.DAO;

import com.oBasurto.examenPracticoDigipro.JPA.AlumnoMateria;
import com.oBasurto.examenPracticoDigipro.ML.Result;

/**
 *
 * @author Alien 2
 */
public interface AlumnoMateriaDAO {
    Result Add(AlumnoMateria alumnoMateria);
    Result GetAll();
    Result Delete(int idAlumnoMateria);
}
