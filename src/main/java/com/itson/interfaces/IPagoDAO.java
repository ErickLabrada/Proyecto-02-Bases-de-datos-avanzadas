/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPagoDAO {
    
    Pago consultar (EntityManager entityManager, Long idPago);
    
    void insertar (EntityManager entityManager, double monto, LocalDate fechaPago, Tramite tramine);
    
    void eliminar (EntityManager entityManager, Long idPago);
        
}
