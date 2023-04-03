/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.IVehiculoDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class VehiculoDAO implements IVehiculoDAO {

    @Override
    public Vehiculo consultar(EntityManager entityManager, Long idVehiculo) {

        return entityManager.find(Vehiculo.class, idVehiculo);

    }

    @Override
    public void eliminar(EntityManager entityManager, Long idVehiculo) {

        Vehiculo vehiculo = entityManager.find(Vehiculo.class, idVehiculo);
        if (vehiculo!=null){
            entityManager.getTransaction().begin();
            entityManager.remove(vehiculo);
            entityManager.getTransaction().commit();        }
        
        
    }

    @Override
    public void addPlacas(EntityManager entityManager, Vehiculo vehiculo, Placa placa) {

        vehiculo.getPlacas().add(placa);
        entityManager.getTransaction().begin();
        entityManager.merge(vehiculo);
        entityManager.getTransaction().commit();

    }

    
}
