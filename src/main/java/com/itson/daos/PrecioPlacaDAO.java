/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.PrecioPlaca;
import com.itson.interfaces.IPrecioPlacaDAO;
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
public class PrecioPlacaDAO implements IPrecioPlacaDAO {

    /**
     * Método que inserta a la base de datos un objeto de tipo PrecioPlaca.
     * 
     * @param entityManager
     * @param precioPlaca 
     */
    @Override
    public void insert(EntityManager entityManager, PrecioPlaca precioPlaca) {
        if (getPrecioPlaca(entityManager)!=null){
            updatePrecioPlaca(entityManager,precioPlaca.getPrecioVehiculoNuevo(), precioPlaca.getPrecioVehiculoViejo());
        } else{
            entityManager.getTransaction().begin();
            entityManager.persist(precioPlaca);
            entityManager.getTransaction().commit();
        }
    }
/**
 * Método que crea un objeto de tipo PrecioPlaca.
 * 
 * @param entityManager
 * @param precioVehiculoNuevo
 * @param precioVehiculoViejo
 * @return precioPlaca
 */
    @Override
    public PrecioPlaca createPrecioPlaca(EntityManager entityManager, double precioVehiculoNuevo, double precioVehiculoViejo) {
        PrecioPlaca precioPlaca = new PrecioPlaca();
        precioPlaca.setPrecioVehiculoNuevo(precioVehiculoNuevo);
        precioPlaca.setPrecioVehiculoViejo(precioVehiculoViejo);
        return precioPlaca;
    }
/**
 * Método que actualiza el objeto de tipo PrecioPlaca.
 * 
 * @param entityManager
 * @param precioVehiculoNuevo
 * @param precioVehiculoViejo 
 */
    @Override
    public void updatePrecioPlaca(EntityManager entityManager, double precioVehiculoNuevo, double precioVehiculoViejo) {
        PrecioPlaca precioPlaca = getPrecioPlaca(entityManager);
        precioPlaca.setPrecioVehiculoNuevo(precioVehiculoNuevo);
        precioPlaca.setPrecioVehiculoViejo(precioVehiculoViejo);
        entityManager.getTransaction().begin();
        entityManager.merge(precioPlaca);
        entityManager.getTransaction().commit();
    }

    
    /**
     * Método que obtiene el precio de las placas

     * @param entityManager
     * @return PrecioPlaca
     */
    @Override
    public PrecioPlaca getPrecioPlaca(EntityManager entityManager) {

        TypedQuery<PrecioPlaca> query = entityManager.createQuery("SELECT p FROM PrecioPlaca", PrecioPlaca.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /**
     * Método que mediante una consulta dinamica regresa una lista con todos los
     * precios de placas registrados en la base de datos que cumplan con los parámetros de
     * busqueda.Arroja una excepción "EntityNotFoundException" en caso de no
     * encontrar nada.
     *
     * @param entityManager
     * @param id
     * @param precioVehiculoNuevo
     * @param precioVehiculoViejo
     * @return ArrayList <PrecioPlaca> listaPreciosPlaca
     */
    @Override
    public ArrayList<PrecioPlaca> getListaPrecios(EntityManager entityManager, Long id, Double precioVehiculoNuevo,Double precioVehiculoViejo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrecioPlaca> criteriaQuery = criteriaBuilder.createQuery(PrecioPlaca.class);
        Root<PrecioPlaca> precioPlaca = criteriaQuery.from(PrecioPlaca.class);
        criteriaQuery.select(precioPlaca);
        criteriaQuery.distinct(true);

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {

            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(precioPlaca.get("id"), parametro));

        }

        if (precioVehiculoNuevo != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioVehiculoNuevo");
            criteria.add(criteriaBuilder.equal(precioPlaca.get("precioVehiculoNuevo"), parametro));

        }
        if (precioVehiculoViejo != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioVehiculoViejo");
            criteria.add(criteriaBuilder.equal(precioPlaca.get("precioVehiculoViejo"), parametro));

        }

        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<PrecioPlaca> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (precioVehiculoNuevo != null) {
            query.setParameter("precioVehiculoNuevo", precioVehiculoNuevo);
        }
        if (precioVehiculoViejo != null) {
            query.setParameter("precioVehiculoViejo", precioVehiculoViejo);
        }

        ArrayList<PrecioPlaca> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron tramites con los datos proporcionados");
        }
        return resultados;
    }

}
