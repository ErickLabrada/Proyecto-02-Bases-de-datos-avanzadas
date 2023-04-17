package com.itson.interfaces;


import com.itson.dominio.Licencia;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author Erick
 */
public interface IPersonaDAO {

    Persona query(EntityManager entityManager, Long idPersona);

    void insert(EntityManager entityManager, Persona persona);

    void delete(EntityManager entityManager, Long idPersona);

    void addTramite(EntityManager entityManager, Persona persona, Tramite tramite);

    void masiveInsert(EntityManager entityManager);

    boolean checkPersona(EntityManager entityManager, Persona persona);
    
    ArrayList<Persona> getListaPersonas(EntityManager entityManager, Long id, Boolean discapacidad, LocalDate fechaInicio, LocalDate fechaFin, String nombre, String rfc, String telefono,Integer birthYear);

}
