/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Pago;
import com.itson.dominio.Tramite;
import com.itson.interfaces.IPagoDAO;
import java.time.LocalDate;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class PagoDAO implements IPagoDAO {

    @Override
    public Pago consultar(EntityManager entityManager, Long idPago) {

        return entityManager.find(Pago.class, idPago);

    }

    @Override
    public void insertar(EntityManager entityManager, double monto, LocalDate fechaPago, Tramite tramite) {

        TramiteDAO tramiteDAO = new TramiteDAO();
        Pago pago = new Pago();
        pago.setFechaPago(fechaPago);
        pago.setMonto(monto);
        pago.setTramite(tramite);
        entityManager.getTransaction().begin();
        entityManager.persist(pago);
        entityManager.getTransaction().commit();
        
    }

    @Override
    public void eliminar(EntityManager entityManager, Long idPago) {

        Pago pago = new Pago();

        if (pago != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pago);
            entityManager.getTransaction().commit();
        }

    }

}
