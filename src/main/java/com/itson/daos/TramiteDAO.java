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
 * Clase DAO para la entidad Tramite, implementa la interfaz ITramiteDAO
 * 
 * @author Erick
 */
public class TramiteDAO implements ITramiteDAO {

    /**
     * Método que busca y regresa de la base de datos un objeto de tipo Tramite con el ID especificado.
     * 
     * 
     * @param entityManager
     * @param idTramite
     * @return Tramite con el ID especificado o Null en caso de no encontrar.
     */
    
    @Override
    public Tramite query(EntityManager entityManager, Long idTramite) {
        return entityManager.find(Tramite.class, idTramite);
    }
/**
     * Método que elimina de la base de datos un objeto de tipo tramite.
     * 
     * @param entityManager
     * @param idTramite 
     */
    @Override
    public void delete(EntityManager entityManager, Long idTramite) {
        Tramite tramite = entityManager.find(Tramite.class, idTramite);
        if (tramite != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(tramite);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Método que paga un tramite
     * 
     * @param entityManager
     * @param pago
     * @throws AlreadyPaidException 
     */
    
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