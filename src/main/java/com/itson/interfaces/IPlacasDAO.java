/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.daos.LicenciaDAO;
import com.itson.daos.VehiculoDAO;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Erick
 */
public interface IPlacasDAO {

    void insert(EntityManager entityManager, Placa placa, VehiculoDAO vehiculoDAO);

    String generateMatricula(EntityManager entityManager);

    ArrayList<String> getMatriculas(EntityManager entityManager);

    boolean checkMatricula(String matricula, ArrayList<String> matriculas);

    Placa create(EntityManager entityManager, LocalDate fechaRecepcion, Pago pago, Vehiculo vehiculo, LicenciaDAO licenciaDAO, Persona persona, String matricula);

    boolean checkPreviousPayments(EntityManager entityManager, Placa placa);

    ArrayList<Placa> getPlacas(EntityManager entityManager, Long vehiculoID);

    ArrayList<Placa> getListaPlacas(EntityManager entityManager, Long id, Boolean estado, LocalDate fechaRecepcion, Pago pago, Vehiculo vehiculo, Persona persona, LocalDate desde, LocalDate hasta) throws EntityNotFoundException;

}
