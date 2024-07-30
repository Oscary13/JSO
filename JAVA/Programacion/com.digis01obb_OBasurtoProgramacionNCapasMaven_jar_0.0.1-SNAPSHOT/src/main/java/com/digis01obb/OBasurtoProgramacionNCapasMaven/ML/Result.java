/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.ML;

import java.util.List;

/**
 *
 * @author Alien 2
 */
public class Result<T> {
    public boolean  correct;
    public String errorMessage;
    public Exception ex;
    public T object;
    public List<T> objects;
}
