/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Precio;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.IPrecioDAO;
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
public class PrecioDAO implements IPrecioDAO {

    /**
     * Método que busca y regresa de la base de datos un objeto de tipo Precio
     * con el ID especificado.
     *
     * @param entityManager
     * @param idPrecio
     * @return
     */
    @Override
    public Precio query(EntityManager entityManager, Long idPrecio) {
        return entityManager.find(Precio.class, idPrecio);

    }

    /**
     * Método que elimina de la base de datos un objeto de tipo tramite.
     *
     * @param entityManager
     * @param idPrecio
     */
    @Override
    public void delete(EntityManager entityManager, Long idPrecio) {
        Precio precio = entityManager.find(Precio.class, idPrecio);
        if (precio != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(precio);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Método que mediante una consulta dinamica regresa una lista con todos los
     * precios registrados en la base de datos que cumplan con los parámetros
     * de busqueda.Arroja una excepción "EntityNotFoundException" en caso de no
     * encontrar nada.
     * 
     * @param entityManager
     * @param id
     * @param vigencia
     * @param precioNormal
     * @param precioDiscapacitado
     * @param precioVehiculoNuevo
     * @param precioVehiculoviejo
     * @return 
     */
    
    @Override
    public ArrayList<Precio> getListaPrecios(EntityManager entityManager, Long id, Vigencia vigencia, Double precioNormal, Double precioDiscapacitado, Double precioVehiculoNuevo, Double precioVehiculoViejo) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Precio> criteriaQuery = criteriaBuilder.createQuery(Precio.class);
        Root<Precio> precio = criteriaQuery.from(Precio.class);
        criteriaQuery.select(precio);
        criteriaQuery.distinct(true);

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {

            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(precio.get("id"), parametro));

        }

        if (vigencia != null) {

            ParameterExpression<Vigencia> parametro = criteriaBuilder.parameter(Vigencia.class, "vigencia");
            criteria.add(criteriaBuilder.equal(precio.get("vigencia"), parametro));

        }
        if (precioNormal != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioNormal");
            criteria.add(criteriaBuilder.equal(precio.get("precioNormal"), parametro));

        }

        if (precioDiscapacitado != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioDiscapacitado");
            criteria.add(criteriaBuilder.equal(precio.get("precioDiscapacitado"), parametro));

        }

        if (precioVehiculoNuevo != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioVehiculoNuevo");
            criteria.add(criteriaBuilder.equal(precio.get("precioVehiculoNuevo"), parametro));

        }
        if (precioVehiculoViejo != null) {

            ParameterExpression<Double> parametro = criteriaBuilder.parameter(Double.class, "precioVehiculoViejo");
            criteria.add(criteriaBuilder.equal(precio.get("precioVehiculoViejo"), parametro));

        }

        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Precio> query = entityManager.createQuery(criteriaQuery);

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
        if (precioVehiculoNuevo != null) {
            query.setParameter("precioVehiculoNuevo", precioVehiculoNuevo);
        }
        if (precioVehiculoViejo != null) {
            query.setParameter("precioVehiculoViejo", precioVehiculoViejo);
        }

        ArrayList<Precio> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron tramites con los datos proporcionados");
        }
        return resultados;
    }

}
