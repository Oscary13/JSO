/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Editorial;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;

/**
 *
 * @author Alien 2
 */
public interface EditorialDAO {
    Result AddJPA(Editorial editorial);
    Result GetAllJPA();
}
