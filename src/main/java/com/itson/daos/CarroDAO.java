/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Carro;
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.ICarroDAO;
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
public class CarroDAO extends VehiculoDAO implements ICarroDAO{

    @Override
    public void insert(EntityManager entityManager, Carro carro) {

        
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
        
    }

    @Override
    public Carro createCarro(String color, String linea, String marca, String modelo, String serie, List<Placa> placas) {

        Carro carro = new Carro();
        carro.setColor(color);
        carro.setLinea(linea);
        carro.setMarca(marca);
        carro.setPlacas((List) placas);
        carro.setModelo(modelo);
        carro.setSerie(serie);

        return carro;
        
    }

@Override
    public ArrayList<Carro> getListaCarros(EntityManager entityManager, Long id, String modelo, String linea, String color, String serie, String marca) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carro> criteriaQuery = criteriaBuilder.createQuery(Carro.class);
        Root<Carro> carro = criteriaQuery.from(Carro.class);
        criteriaQuery.select(carro);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {
            
            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(carro.get("id"), parametro));

        }

        if (serie != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "serie");
            criteria.add(criteriaBuilder.equal(carro.get("serie"), parametro));

        }

        if (marca != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "marca");
            criteria.add(criteriaBuilder.like(carro.get("marca"), parametro));

        }

        if (linea != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "linea");
            criteria.add(criteriaBuilder.like(carro.get("linea"), parametro));

        }

        if (color != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "color");
            criteria.add(criteriaBuilder.equal(carro.get("color"), parametro));

        }
        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Carro> query = entityManager.createQuery(criteriaQuery);

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

        ArrayList<Carro> resultados = new ArrayList();
        resultados.addAll(query.getResultList());
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron carros con los datos proporcionados");
        }
        return resultados;

    }
    
}
