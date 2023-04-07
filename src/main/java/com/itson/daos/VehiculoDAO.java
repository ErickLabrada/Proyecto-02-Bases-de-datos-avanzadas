/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Persona;
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.IVehiculoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
public class VehiculoDAO implements IVehiculoDAO {
    
    

    @Override
    public Vehiculo query(EntityManager entityManager, Long idVehiculo) {

        return entityManager.find(Vehiculo.class, idVehiculo);

    }

    @Override
    public void delete(EntityManager entityManager, Long idVehiculo) {

        Vehiculo vehiculo = entityManager.find(Vehiculo.class, idVehiculo);
        if (vehiculo!=null){
            entityManager.getTransaction().begin();
            entityManager.remove(vehiculo);
            entityManager.getTransaction().commit();        }
        
        
    }

    @Override
    public void addPlacas(EntityManager entityManager, Vehiculo vehiculo, Placa placa) {

        updatePlacas(entityManager, vehiculo);
        vehiculo.getPlacas().add(placa);
        entityManager.getTransaction().begin();
        entityManager.merge(vehiculo);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updatePlacas(EntityManager entityManager, Vehiculo vehiculo) {

        List<Placa> placas = vehiculo.getPlacas();

        for (Placa placa : placas) {
            placa.setEstado(false);
        }
        entityManager.getTransaction().begin();
        entityManager.merge(vehiculo);
        entityManager.getTransaction().commit();
        
    }

    @Override
    public ArrayList<Vehiculo> getListaVehiculo(EntityManager entityManager, Long id, String modelo, String linea, String color, String serie, String marca) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehiculo> criteriaQuery = criteriaBuilder.createQuery(Vehiculo.class);
        Root<Vehiculo> vehiculo = criteriaQuery.from(Vehiculo.class);
        criteriaQuery.select(vehiculo);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {
            
            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(vehiculo.get("id"), parametro));

        }

        if (serie != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "serie");
            criteria.add(criteriaBuilder.equal(vehiculo.get("serie"), parametro));

        }

        if (marca != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "marca");
            criteria.add(criteriaBuilder.like(vehiculo.get("marca"), parametro));

        }

        if (linea != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "linea");
            criteria.add(criteriaBuilder.like(vehiculo.get("linea"), parametro));

        }

        if (color != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "color");
            criteria.add(criteriaBuilder.equal(vehiculo.get("color"), parametro));

        }
        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Vehiculo> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (serie != null) {
            query.setParameter("serie", serie);
        }
        if (marca != null) {
            query.setParameter("marca", "%" + marca + "%");
        }
        if (linea != null) {
            query.setParameter("linea", "%" + linea + "%");
        }
        if (color != null) {
            query.setParameter("color", color);
        }

        ArrayList<Vehiculo> resultados = new ArrayList();
        resultados.addAll(query.getResultList());
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron vehiculos con los datos proporcionados");
        }
        return resultados;

    }

}
