/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.daos.TramiteDAO;
import com.itson.dominio.Pago;
import com.itson.dominio.Tramite;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPagoDAO {
    
    Pago query (EntityManager entityManager, Long idPago);
    
    void insert (EntityManager entityManager, Pago pago, TramiteDAO tramiteDAO);
    
    void delete (EntityManager entityManager, Long idPago);
    
    Pago create (double monto, LocalDate fechaPago, Tramite tramine);
        
    public ArrayList<Pago> getListaPagos(EntityManager entityManager, Long id, Tramite tramite, LocalDate fechaPago);

    
}
