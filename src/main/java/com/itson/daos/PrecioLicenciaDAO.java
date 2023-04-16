/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.PrecioLicencia;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.IPrecioLicenciaDAO;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
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
public class PrecioLicenciaDAO implements IPrecioLicenciaDAO {

    /**
     * Método que inserta a la base de datos un objeto de tipo PrecioLicencia.
     *
     * @param entityManager
     * @param precioLicencia
     */
    @Override
    public void insert(EntityManager entityManager, PrecioLicencia precioLicencia) {
        if (getPrecioLicencia(entityManager, precioLicencia.getVigencia()) != null) {
            updatePrecioLicencia(entityManager, precioLicencia.getVigencia(), precioLicencia.getPrecioNormal(), precioLicencia.getPrecioDiscapacidad());
        } else {
            entityManager.getTransaction().begin();
            entityManager.persist(precioLicencia);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Método que crea un objeto de tipo PrecioLicencia.
     *
     * @param entityManager
     * @param vigencia
     * @param precioNormal
     * @param precioDiscapacidad
     * @return precioLicencia
     */
    @Override
    public PrecioLicencia createPrecioLicencia(EntityManager entityManager, Vigencia vigencia, double precioNormal, double precioDiscapacidad) {
        PrecioLicencia precioLicencia = new PrecioLicencia();
        precioLicencia.setVigencia(vigencia);
        precioLicencia.setPrecioDiscapacidad(precioDiscapacidad);
        precioLicencia.setPrecioNormal(precioNormal);
        return precioLicencia;
    }

    
    /**
     *Método que actualiza un objeto de tipo PrecioLicencia segun el tipo de vigencia
     * 
     * @param entityManager
     * @param vigencia
     * @param precioNormal
     * @param precioDiscapacitado 
     */
    @Override
    public void updatePrecioLicencia(EntityManager entityManager, Vigencia vigencia, double precioNormal, double precioDiscapacitado) {
        PrecioLicencia precioLicencia = getPrecioLicencia(entityManager, vigencia);
        precioLicencia.setPrecioDiscapacidad(precioDiscapacitado);
        precioLicencia.setPrecioNormal(precioNormal);
        entityManager.getTransaction().begin();
        entityManager.merge(precioLicencia);
        entityManager.getTransaction().commit();
    }

    /**
     * Método que obtiene el precio de una licencia en base al tipo de vigencia
     *
     * @param entityManager
     * @param vigencia
     * @return precioLicencia
     */
    public PrecioLicencia getPrecioLicencia(EntityManager entityManager, Vigencia vigencia) {
        try {
            TypedQuery<PrecioLicencia> query = entityManager.createQuery("SELECT p FROM PrecioLicencia p WHERE p.vigencia = :vigencia", PrecioLicencia.class);
            query.setParameter("vigencia", vigencia);
            PrecioLicencia licencia = query.getSingleResult();
            return licencia;
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
     * Método que inserta los precios por default de las licencias
     *
     * @param entityManager
     */
    public void insertDefaulPrices(EntityManager entityManager) {
        insert(entityManager, createPrecioLicencia(entityManager, Vigencia.ONE_YEAR, 600.00, 200.00));
        insert(entityManager, createPrecioLicencia(entityManager, Vigencia.TWO_YEARS, 900.00, 500.00));
        insert(entityManager, createPrecioLicencia(entityManager, Vigencia.THREE_YEARS, 1100.00, 700.00));

    }

    /**
     * Método que mediante una consulta dinamica regresa una lista con todos los
     * precios de licencias registrados en la base de datos que cumplan con los parámetros de
     * busqueda.Arroja una excepción "EntityNotFoundException" en caso de no
     * encontrar nada.
     *
     * @param entityManager
     * @param id
     * @param vigencia
     * @param precioNormal
     * @param precioDiscapacitado
     * @return ArrayList<PrecioLicencia> listaPreciosLicencia
     */
    @Override
    public ArrayList<PrecioLicencia> getListaPrecios(EntityManager entityManager, Long id, Vigencia vigencia, Double precioNormal, Double precioDiscapacitado) {
 CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrecioLicencia> criteriaQuery = criteriaBuilder.createQuery(PrecioLicencia.class);
        Root<PrecioLicencia> precioLicencia = criteriaQuery.from(PrecioLicencia.class);
        criteriaQuery.select(precioLicencia);
        criteriaQuery.distinct(true);

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {

            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(precioLicencia.get("id"), parametro));

        }

        if (vigencia != null) {

            ParameterExpression<Vigencia> parametro = criteriaBuilder.parameter(Vigencia.class, "vigencia");
            criteria.add(criteriaBuilder.equal(precioLicencia.get("vigencia"), parametro));

        }
        if (precioNormal != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioNormal");
            criteria.add(criteriaBuilder.equal(precioLicencia.get("precioNormal"), parametro));

        }

        if (precioDiscapacitado != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioDiscapacitado");
            criteria.add(criteriaBuilder.equal(precioLicencia.get("precioDiscapacitado"), parametro));

        }

     
        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<PrecioLicencia> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (vigencia != null) {
            query.setParameter("vigencia", vigencia);
        }
        if (precioNormal != null) {
            query.setParameter("precioNormal", precioNormal);
        }
        if (precioDiscapacitado != null) {
            query.setParameter("precioDiscapacitado", precioDiscapacitado);
        }

        ArrayList<PrecioLicencia> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron tramites con los datos proporcionados");
        }
        return resultados;
    }

    }