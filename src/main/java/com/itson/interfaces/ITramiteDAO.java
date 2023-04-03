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
import javax.persistence.EntityManager;


/**
 *
 * @author Erick
 */
public interface ITramiteDAO {
    
    Tramite consultar (EntityManager entityManager, Long idTramite);
        
    void eliminar (EntityManager entityManager, Long idTramite);
    
    void addPago(EntityManager entityManager, Pago pago);
}
