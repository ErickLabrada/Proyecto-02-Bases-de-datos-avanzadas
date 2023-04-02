/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.interfaces.IPersonaDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public class PersonaDAO implements IPersonaDAO {

    @Override
    public Persona consultar(EntityManager entityManager, Long idPersona) {
        return entityManager.find(Persona.class, idPersona);
    }

    @Override
    public void insertar(EntityManager entityManager, Boolean discapacidad, LocalDate fechaNacimiento, String nombre, String rfc, String telefono, List <Tramite> tramites) {

        Persona persona = new Persona();
        persona.setDiscapacidad(discapacidad);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setNombre(nombre);
        persona.setRfc(rfc);
        persona.setTelefono(telefono);
        persona.setTramite(tramites);
        entityManager.getTransaction().begin();
        entityManager.persist(persona);
        entityManager.getTransaction().commit();

    }

    @Override
    public void eliminar(EntityManager entityManager, Long idPersona) {
        
        Persona persona = entityManager.find(Persona.class, idPersona);
        if (persona != null) {
            entityManager.remove(this);
        }
        
    }

    @Override
    public void addTramites(EntityManager entityManager, Persona persona,Tramite tramite) {

        entityManager.getTransaction().begin();
        persona.getTramite().add(tramite);
        entityManager.merge(persona);
        entityManager.getTransaction().commit();
        
    }
}