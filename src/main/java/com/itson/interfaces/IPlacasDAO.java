/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Pago;
import com.itson.dominio.Vehiculo;
import java.time.LocalDate;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPlacasDAO {
    
    void insertar (EntityManager entityManager, String matricula, LocalDate fechaRecepcion, boolean estado, Pago pago,Vehiculo vehiculo);
       
}