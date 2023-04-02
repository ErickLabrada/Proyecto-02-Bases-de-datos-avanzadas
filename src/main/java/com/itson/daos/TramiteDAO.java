/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.AlreadyPaidException;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.ITramiteDAO;
import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class TramiteDAO implements ITramiteDAO {

    @Override
    public Tramite consultar(EntityManager entityManager, Long idTramite) {
        return entityManager.find(Tramite.class, idTramite);
    }

    @Override
    public void eliminar(EntityManager entityManager, Long idTramite) {
        Tramite tramite = entityManager.find(Tramite.class, idTramite);
        if (tramite != null) {
            entityManager.remove(tramite);
        }
    }

    @Override
    public void addPago(EntityManager entityManager, Tramite tramite, Pago pago) throws AlreadyPaidException {
        if (tramite.getPago() != null) {
            entityManager.getTransaction().begin();
            tramite.setPago(pago);
            entityManager.merge(tramite);
            entityManager.getTransaction().commit();
        } else {
            throw new AlreadyPaidException("La " + (tramite.getClass().getAnnotation(DiscriminatorValue.class).value()) + " ya fue pagada.");
        }
    }
}