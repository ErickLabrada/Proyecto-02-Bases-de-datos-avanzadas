/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.ProcedureNotFoundException;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
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
 *
 * @author Erick
 */
public class PagoDAO implements IPagoDAO {

    @Override
    public Pago query(EntityManager entityManager, Long idPago) {

        return entityManager.find(Pago.class, idPago);

    }

    @Override
    public void insert(EntityManager entityManager, Pago pago, TramiteDAO tramiteDAO) {
        
        entityManager.getTransaction().begin();
        entityManager.persist(pago);
        entityManager.getTransaction().commit();
        tramiteDAO.addPago(entityManager, pago);
        
    }

    @Override
    public void delete(EntityManager entityManager, Long idPago) {

        Pago pago = new Pago();

        if (pago != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pago);
            entityManager.getTransaction().commit();
        }

    }

    @Override
    public Pago create(double monto, LocalDate fechaPago, Tramite tramite) {
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
    
     @Override
    public ArrayList<Pago> getListaPagos(EntityManager entityManager, Long id, Tramite tramite, LocalDate fechaPago) {

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

        if(criteria.size()==0){
            
        }
        else if (criteria.size()==1){
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
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron pagos con los datos proporcionados");
        }
        return resultados;
    }
    
}