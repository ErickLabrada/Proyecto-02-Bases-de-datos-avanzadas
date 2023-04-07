/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Carro;
import com.itson.dominio.Placa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface ICarroDAO {
    
    void insert (EntityManager entityManager, Carro carro);
 
    Carro createCarro(String color, String linea, String marca, String modelo, String serie,List <Placa> placas);
    
    ArrayList <Carro> getListaCarros(EntityManager entityManager, Long id, String modelo, String linea, String Color, String Serie, String marca);
}
