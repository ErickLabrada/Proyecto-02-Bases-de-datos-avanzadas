/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.AlreadyPaidException;
import com.itson.Exceptions.ProcedureNotFoundException;
import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Tramite;
import com.itson.interfaces.IPagoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase DAO para la entidad Pago, implementa la interfaz IPagoDAO
 *
 * @author Erick
 */
public class PagoDAO implements IPagoDAO {

    /**
     * Método que busca y regresa de la base de datos un objeto de tipo Pago con
     * el ID especificado.
     *
     *
     * @param entityManager
     * @param idPago
     * @return Pago con el ID especificado o Null en caso de no encontrar.
     */
    @Override
    public Pago query(EntityManager entityManager, Long idPago) {

        return entityManager.find(Pago.class, idPago);

    }

    /**
     * Método que inserta a la base de datos un objeto de tipo Pago.
     *
     * @param entityManager
     * @param pago
     */
    @Override
    public void insert(EntityManager entityManager, Pago pago, TramiteDAO tramiteDAO) {
        
         if (pago.getTramite().getPago() != null) {
            throw new AlreadyPaidException("El tramite ya fue pagado");
        } else {
        entityManager.getTransaction().begin();
        entityManager.persist(pago);
        entityManager.getTransaction().commit();
        tramiteDAO.addPago(entityManager, pago);
       
         }
    }

    /**
     * Método que elimina de la base de datos un objeto de tipo pago.
     *
     * @param entityManager
     * @param idPago
     */
    @Override
    public void delete(EntityManager entityManager, Long idPago) {

        Pago pago = entityManager.find(Pago.class, idPago);

        if (pago != null) {
            Class clase = pago.getTramite().getClass();
            if (clase == Licencia.class) {
                LicenciaDAO licenciaDAO = new LicenciaDAO();
                licenciaDAO.deleteRelatedProcedures(entityManager, pago);
            }
            entityManager.getTransaction().begin();
            entityManager.remove(pago);
            entityManager.getTransaction().commit();

        }
    }

    /**
     * Método que crea un objeto de tipo Pago. Arroja una excepción del tipo
     * "ProcedureNotFoundException" si el tramite a pagar no existe
     *
     * @param monto
     * @param fechaPago
     * @param tramite
     * @throws ProcedureNotFoundException
     * @return Pago
     */
    @Override
    public Pago create(double monto, LocalDate fechaPago, Tramite tramite) throws ProcedureNotFoundException {
        if (tramite != null) {
            Pago pago = new Pago();
            pago.setFechaPago(fechaPago);
            pago.setMonto(monto);
            pago.setTramite(tramite);

            return pago;
        } else {
            throw new ProcedureNotFoundException("El tramite que desea pagar no existe");
        }
    }

    /**
     * *
     * Método que mediante una consulta dinamica regresa una lista con todos los
     * pagos registrados en la base de datos que cumplan con los parámetros de
     * busqueda. Arroja una excepción "EntityNotFoundException" en caso de no
     * encontrar nada.
     *
     * @param entityManager
     * @param id
     * @param tramite
     * @param fechaPago
     * @throws EntityNotFoundException
     * @return ArrayList<Pago>;
     */
    @Override
    public ArrayList<Pago> getListaPagos(EntityManager entityManager, Long id, Tramite tramite, LocalDate fechaPago) throws EntityNotFoundException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pago> criteriaQuery = criteriaBuilder.createQuery(Pago.class);
        Root<Pago> pago = criteriaQuery.from(Pago.class);
        criteriaQuery.select(pago);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {

            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(pago.get("id"), parametro));

        }

        if (tramite != null) {

            ParameterExpression<Tramite> parametro = criteriaBuilder.parameter(Tramite.class, "tramite");
            criteria.add(criteriaBuilder.equal(pago.get("tramite"), parametro));

        }

        if (fechaPago != null) {

            ParameterExpression<LocalDate> parametro = criteriaBuilder.parameter(LocalDate.class, "fechaPago");
            criteria.add(criteriaBuilder.equal(pago.get("fechaPago"), parametro));

        }

        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Pago> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (tramite != null) {
            query.setParameter("tramite", tramite);
        }
        if (fechaPago != null) {
            query.setParameter("fechaPago", fechaPago);
        }

        ArrayList<Pago> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron pagos con los datos proporcionados");
        }
        return resultados;
    }

}
