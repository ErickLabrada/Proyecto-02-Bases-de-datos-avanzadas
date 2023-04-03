/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.dominio.Licencia;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.interfaces.IPersonaDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    public void insertar(EntityManager entityManager, Boolean discapacidad, LocalDate fechaNacimiento, String nombre, String rfc, String telefono) {
       
            Persona persona = new Persona();
            persona.setDiscapacidad(discapacidad);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setNombre(nombre);
            persona.setRfc(rfc);
            persona.setTelefono(telefono);
            List<Tramite> tramites = new ArrayList();
            persona.setTramite(tramites);
             if (verificaPersona(entityManager, persona)) {
            entityManager.getTransaction().begin();
            entityManager.persist(persona);
            entityManager.getTransaction().commit();

        }
    }

    @Override
    public void eliminar(EntityManager entityManager, Long idPersona) {

        Persona persona = entityManager.find(Persona.class, idPersona);
        if (persona != null) {
            entityManager.remove(this);
        }

    }

    @Override
    public void addTramites(EntityManager entityManager, Persona persona, Tramite tramite) {

        entityManager.getTransaction().begin();
        persona.getTramite().add(tramite);
        entityManager.merge(persona);
        entityManager.getTransaction().commit();

    }

    @Override
    public void masiveInsert(EntityManager entityManager) {

        insertar(entityManager, false, LocalDate.of(1964, Month.MAY, 20), "John Constantine", "JCNSTNTN", "555-1234-5678");
        insertar(entityManager, false, LocalDate.of(1939, Month.JUNE, 6), "Bruce Wayne", "BRWN", "555-2345-6789");
        insertar(entityManager, true, LocalDate.of(1963, Month.AUGUST, 2), "Barbara Gordon", "BRBGRDN", "555-3456-7890");
        insertar(entityManager, true, LocalDate.of(1961, Month.SEPTEMBER, 10), "Matt Murdock", "MTMRDCK", "555-4567-8901");
        insertar(entityManager, true, LocalDate.of(1961, Month.AUGUST, 8), "Charles Xavier", "CHRXVR", "555-5678-9012");
        insertar(entityManager, false, LocalDate.of(1941, Month.OCTOBER, 1), "Steve Rogers", "STVRRGRS", "555-6789-0123");
        insertar(entityManager, false, LocalDate.of(1963, Month.JUNE, 10), "Jean Grey", "JNGRY", "555-7890-1234");
        insertar(entityManager, false, LocalDate.of(1962, Month.AUGUST, 31), "Peter Parker", "PTRPRKR", "555-8901-2345");
        insertar(entityManager, false, LocalDate.of(1940, Month.JUNE, 2), "Kent Nelson (Dr. Fate)", "KNTNLSON", "555-9012-3456");
        insertar(entityManager, false, LocalDate.of(1938, Month.APRIL, 18), "Clark Kent", "CLRKKNT", "555-0123-4567");
        insertar(entityManager, false, LocalDate.of(1961, Month.NOVEMBER, 10), "Scott Summers", "SCTTSMMRS", "555-1234-5678");
        insertar(entityManager, false, LocalDate.of(1939, Month.MAY, 1), "Dick Grayson", "DKGRYSN", "555-2345-6789");
        insertar(entityManager, false, LocalDate.of(1962, Month.JULY, 10), "Ororo Munroe", "ORRMNR", "555-3456-7890");
        insertar(entityManager, false, LocalDate.of(1963, Month.JANUARY, 10), "Wanda Maximoff", "WNDMXMFF", "555-5678-9012");
        insertar(entityManager, false, LocalDate.of(1940, Month.OCTOBER, 1), "Hal Jordan", "HLJRDN", "555-6789-0123");
        insertar(entityManager, false, LocalDate.of(1979, Month.JUNE, 5), "Barbara Ann Minerva", "BRBRNNMNRV", "555-7890-1234");
        insertar(entityManager, true, LocalDate.of(1991, Month.JULY, 5), "Victor Stone", "CVS123", "555-8765-1792");
        insertar(entityManager, true, LocalDate.of(1999, Month.JANUARY, 1), "Maya López", "MYLPZ9", "555-7890-7564");
        insertar(entityManager, true, LocalDate.of(1984, Month.NOVEMBER, 1), "Jericho Wilson", "JRCWLSN", "555-3456-6718");
        insertar(entityManager, false, LocalDate.of(1984, Month.APRIL, 7), "Scott Pilgrim", "SCTPLGRM", "555-1234-1859");

    }

    @Override
    public boolean verificaPersona(EntityManager entityManager, Persona persona) {

        if (persona.getRfc()!=null && persona.getNombre()!=null && persona.getFechaNacimiento()!=null && persona.getTelefono()!=null){
            return true;
        } return false;
    }
    
    
 @Override
    public ArrayList<Licencia> getLicencias(EntityManager entityManager, Long personaID) {


        TypedQuery<Licencia> query = entityManager.createQuery("SELECT l FROM Licencia l WHERE l.persona.id = :personaID", Licencia.class);
        query.setParameter("personaID", personaID);

        return new ArrayList<Licencia>(query.getResultList());

    }

}
