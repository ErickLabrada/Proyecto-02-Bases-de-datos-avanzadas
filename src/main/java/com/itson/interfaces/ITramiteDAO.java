/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Pago;
import com.itson.dominio.Tramite;
import javax.persistence.EntityManager;


/**
 *
 * @author Erick
 */
public interface ITramiteDAO {
    
    Tramite query (EntityManager entityManager, Long idTramite);
        
    void delete (EntityManager entityManager, Long idTramite);
    
    void addPago(EntityManager entityManager, Pago pago);
}