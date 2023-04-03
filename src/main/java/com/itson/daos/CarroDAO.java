/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Carro;
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.ICarroDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class CarroDAO extends VehiculoDAO implements ICarroDAO{

    @Override
    public void insertar(EntityManager entityManager, String color, String linea, String marca, String modelo, String serie, List <Placa> placas) {

        Carro carro = new Carro();
        carro.setColor(color);
        carro.setLinea(linea);
        carro.setMarca(marca);
        carro.setPlacas((List) placas);
        carro.setModelo(modelo);
        carro.setSerie(serie);
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
        
    }


    
}
