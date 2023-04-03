/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.ILicenciasDAO;
import java.time.LocalDate;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class LicenciaDAO extends TramiteDAO implements ILicenciasDAO {

    @Override
    public void insertar(EntityManager entityManager, Persona persona, Pago pago, Vigencia vigencia) {

        Licencia licencia = new Licencia();
        licencia.setPago(pago);
        licencia.setPersona(persona);
        licencia.setVigencia(Vigencia.ONE_YEAR);
        entityManager.getTransaction().begin();
        entityManager.persist(licencia);
        entityManager.getTransaction().commit();

    }

    @Override
    public boolean verificaVigencia(EntityManager entityManager, Licencia licencia) {

        LocalDate fechaVigencia;

        if (licencia.getVigencia().equals(Vigencia.ONE_YEAR)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(1);
        } else if (licencia.getVigencia().equals(Vigencia.TWO_YEARS)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(2);
        } else if (licencia.getVigencia().equals(Vigencia.THREE_YEARS)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(3);
        } else {
            throw new RuntimeException ("Vigencia Inválida");
        }

        return (licencia.getPago().getFechaPago().isBefore(fechaVigencia));
    }

}
