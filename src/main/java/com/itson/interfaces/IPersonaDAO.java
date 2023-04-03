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
    
    Persona consultar (EntityManager entityManager, Long idPersona);
    
    void insertar (EntityManager entityManager, Boolean discapacidad, LocalDate fechaNacimiento, String nombre, String rfc, String telefono);
    
    void eliminar (EntityManager entityManager, Long idPersona);
    
    void addTramites(EntityManager entityManager, Persona persona, Tramite tramite);
    
    void masiveInsert(EntityManager entityManager);
    
    boolean verificaPersona(EntityManager entityManager, Persona persona);
    
    ArrayList<Licencia> getLicencias(EntityManager entityManager, Long personaID);

    
}