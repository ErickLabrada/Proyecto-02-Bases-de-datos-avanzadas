/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.AlreadyPaidException;
import com.itson.dominio.Pago;
import com.itson.dominio.Tramite;
import com.itson.interfaces.ITramiteDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class TramiteDAO implements ITramiteDAO {

    @Override
    public Tramite query(EntityManager entityManager, Long idTramite) {
        return entityManager.find(Tramite.class, idTramite);
    }

    @Override
    public void delete(EntityManager entityManager, Long idTramite) {
        Tramite tramite = entityManager.find(Tramite.class, idTramite);
        if (tramite != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(tramite);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void addPago(EntityManager entityManager, Pago pago) throws AlreadyPaidException {

        Tramite tramite = pago.getTramite();

        if (tramite.getPago() != null) {
            throw new AlreadyPaidException("El tramite ya fue pagado");
        } else {
            entityManager.getTransaction().begin();
            tramite.setPago(pago);
            entityManager.merge(tramite);
            entityManager.getTransaction().commit();
        }
    }
}