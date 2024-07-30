/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.DAO;

import com.oBasurto.examenPracticoDigipro.JPA.Materia;
import com.oBasurto.examenPracticoDigipro.ML.Result;

/**
 *
 * @author Alien 2
 */
public interface MateriaDAO {
    Result Add(Materia materia);
    Result GetAll();
    Result Delete(int idMateria);
}
