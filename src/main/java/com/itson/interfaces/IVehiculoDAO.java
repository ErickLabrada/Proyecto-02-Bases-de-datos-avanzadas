/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Persona;
import com.itson.dominio.Placa;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IVehiculoDAO {
    
    Vehiculo query (EntityManager entityManager, Long idVehiculo);
        
    void delete (EntityManager entityManager, Long idVehiculo);
        
    void addPlacas(EntityManager entityManager, Vehiculo vehiculo, Placa placa);

    void updatePlacas(EntityManager entityManager, Vehiculo vehiculo);

    ArrayList <Vehiculo> getListaVehiculo(EntityManager entityManager, Long id, String modelo, String linea, String Color, String Serie, String marca);

    
}
