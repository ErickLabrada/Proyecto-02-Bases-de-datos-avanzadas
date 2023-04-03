/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Placa;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface ICarroDAO {
    
    void insertar (EntityManager entityManager, String color, String linea, String marca, String modelo, String serie,List <Placa> placas);
    
}
