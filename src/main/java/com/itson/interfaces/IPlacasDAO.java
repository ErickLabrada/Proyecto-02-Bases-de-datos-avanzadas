/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.daos.VehiculoDAO;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPlacasDAO {
    
    void insertar (EntityManager entityManager, LocalDate fechaRecepcion, boolean estado, Pago pago,Vehiculo vehiculo,VehiculoDAO vehiculoDAO, Persona persona);
    
    String generaMatricula(EntityManager entityManager);
 
    ArrayList<String> getMatriculas(EntityManager entityManager);
    
    public boolean verificaMatricula(String matricula, ArrayList<String> matriculas);
    
    public boolean verificaLicenciaConductor(EntityManager entityManager, Persona persona);
    
}