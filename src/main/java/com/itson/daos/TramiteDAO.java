/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.AlreadyPaidException;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vehiculo;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.ITramiteDAO;
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
 * Clase DAO para la entidad Tramite, implementa la interfaz ITramiteDAO
 *
 * @author Erick
 */
public class TramiteDAO implements ITramiteDAO {

    /**
     * Método que busca y regresa de la base de datos un objeto de tipo Tramite
     * con el ID especificado.
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
    
    public ArrayList<Tramite> getListaTramites(EntityManager entityManager, Long id, Persona persona, Pago pago, Vigencia vigencia, Vehiculo vehiculo) throws EntityNotFoundException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteriaQuery = criteriaBuilder.createQuery(Tramite.class);
        Root<Tramite> tramite = criteriaQuery.from(Tramite.class);
        criteriaQuery.select(tramite);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {

            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(tramite.get("id"), parametro));

        }

        if (persona != null) {

            ParameterExpression<Persona> parametro = criteriaBuilder.parameter(Persona.class, "persona");
            criteria.add(criteriaBuilder.equal(tramite.get("persona"), parametro));

        }
        if (vehiculo != null) {

            ParameterExpression<Vehiculo> parametro = criteriaBuilder.parameter(Vehiculo.class, "vehiculo");
            criteria.add(criteriaBuilder.equal(tramite.get("vehiculo"), parametro));

        }
        

        if (pago != null) {

            ParameterExpression<Pago> parametro = criteriaBuilder.parameter(Pago.class, "pago");
            criteria.add(criteriaBuilder.equal(tramite.get("pago"), parametro));

        }

        if (vigencia != null) {

            ParameterExpression<Vigencia> parametro = criteriaBuilder.parameter(Vigencia.class, "rfc");
            criteria.add(criteriaBuilder.equal(tramite.get("vigencia"), parametro));

        }

        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Tramite> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (persona != null) {
            query.setParameter("persona", persona);
        }
        if (persona != null) {
            query.setParameter("vehiculo", vehiculo);
        }
        if (pago != null) {
            query.setParameter("pago", pago);
        }
        if (vigencia != null) {
            query.setParameter("vigencia", vigencia);
        }

        ArrayList<Tramite> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron tramites con los datos proporcionados");
        }
        return resultados;
    }
    
}
