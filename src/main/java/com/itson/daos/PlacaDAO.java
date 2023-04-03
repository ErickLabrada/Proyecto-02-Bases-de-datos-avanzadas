/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Pago;
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.IPlacasDAO;
import java.time.LocalDate;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class PlacaDAO extends TramiteDAO implements IPlacasDAO{

    @Override
    public void insertar(EntityManager entityManager, String matricula, LocalDate fechaRecepcion, boolean estado, Pago pago, Vehiculo vehiculo) {
        
        Placa placa = new Placa();
        placa.setEstado(estado);
        placa.setFechaRecepcion(fechaRecepcion);
        placa.setMatricula(matricula);
        placa.setPago(pago);
        placa.setVehiculo(vehiculo);
        entityManager.getTransaction().begin();
        entityManager.persist(placa);
        entityManager.getTransaction().commit();

    }    
}
