/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vigencia;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPlacasDAO {
    
    void insertar (EntityManager entityManager, String matricula, LocalDate fechaRecepcion, boolean estado, Pago pago);
       
}