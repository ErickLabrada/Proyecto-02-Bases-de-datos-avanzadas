/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.ProcedureNotFoundException;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.interfaces.IPersonaDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase DAO para la entidad Persona, implementa la interfaz IPersonaDAO

 *
 * @author Erick
 */
public class PersonaDAO implements IPersonaDAO {

    /**
     * Método que busca y regresa de la base de datos un objeto de tipo persona con el ID especificado.
     * 
     * 
     * @param entityManager
     * @param idPersona
     * @return Persona con el ID especificado o Null en caso de no encontrar.
     */
    
    @Override
    public Persona query(EntityManager entityManager, Long idPersona) {
        return entityManager.find(Persona.class, idPersona);
    }

    
    /**
     * Método que inserta a la base de datos un objeto de tipo persona.
     * 
     * @param entityManager
     * @param persona 
     */
    @Override
    public void insert(EntityManager entityManager, Persona persona) {

        
        List<Tramite> tramites = new ArrayList();
        persona.setTramite(tramites);
        if (checkPersona(entityManager, persona)) {
            entityManager.getTransaction().begin();
            entityManager.persist(persona);
            entityManager.getTransaction().commit();

        }
    }

    
    /**
     * Método que elimina de la base de datos un objeto de tipo persona.
     * 
     * @param entityManager
     * @param idPersona 
     */
    @Override
    public void delete(EntityManager entityManager, Long idPersona) {

        Persona persona = entityManager.find(Persona.class, idPersona);
        if (persona != null) {
            entityManager.remove(this);
        }

    }

    /**
     * Método que le agrega un objeto de tipo tramite a una persona.
     * Arroja una excepción del tipo "ProcedureNotFoundException" si el procedimiento
     * no existe.
     * 
     * @param entityManager
     * @param persona
     * @param tramite 
     * @throws ProcedureNotFoundException
     */
    
    @Override
    public void addTramite(EntityManager entityManager, Persona persona, Tramite tramite)throws ProcedureNotFoundException{

        if (tramite!=null){
        entityManager.getTransaction().begin();
        persona.getTramite().add(tramite);
        entityManager.merge(persona);
        entityManager.getTransaction().commit();
        } else {throw new ProcedureNotFoundException("El tramite que desea agregar no existe");}
    }
    
    
/**
 * Método que crea un objeto de tipo Persona.
 * 
 * @param discapacidad
 * @param fechaNacimiento
 * @param nombre
 * @param rfc
 * @param telefono
 * @return Persona
 */
    public Persona createPersona(boolean discapacidad, LocalDate fechaNacimiento, String nombre, String rfc, String telefono){
        Persona persona = new Persona();
        persona.setDiscapacidad(discapacidad);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setNombre(nombre);
        persona.setRfc(rfc);
        persona.setTelefono(telefono);
        
        return persona;
    }
    
    
    /**
     * Método que inserta 20 personas a la base de datos (hardcodeado).
     * 
     * @param entityManager 
     */
    @Override
    public void masiveInsert(EntityManager entityManager) {

            insert(entityManager, createPersona(false, LocalDate.of(1964, Month.MAY, 20), "John Constantine", "JCNSTNTN", "555-1234-5678"));
            insert(entityManager, createPersona(false, LocalDate.of(1939, Month.JUNE, 6), "Bruce Wayne", "BRWN", "555-2345-6789"));
            insert(entityManager, createPersona(true, LocalDate.of(1963, Month.AUGUST, 2), "Barbara Gordon", "BRBGRDN", "555-3456-7890"));
            insert(entityManager, createPersona(true, LocalDate.of(1961, Month.SEPTEMBER, 10), "Matt Murdock", "MTMRDCK", "555-4567-8901"));
            insert(entityManager, createPersona(true, LocalDate.of(1961, Month.AUGUST, 8), "Charles Xavier", "CHRXVR", "555-5678-9012"));
            insert(entityManager, createPersona(false, LocalDate.of(1941, Month.OCTOBER, 1), "Steve Rogers", "STVRRGRS", "555-6789-0123"));
            insert(entityManager, createPersona(false, LocalDate.of(1963, Month.JUNE, 10), "Jean Grey", "JNGRY", "555-7890-1234"));
            insert(entityManager, createPersona(false, LocalDate.of(1962, Month.AUGUST, 31), "Peter Parker", "PTRPRKR", "555-8901-2345"));
            insert(entityManager, createPersona(false, LocalDate.of(1940, Month.JUNE, 2), "Kent Nelson", "KNTNLSON", "555-9012-3456"));
            insert(entityManager, createPersona(false, LocalDate.of(1938, Month.APRIL, 18), "Clark Kent", "CLRKKNT", "555-0123-4567"));
            insert(entityManager, createPersona(false, LocalDate.of(1961, Month.NOVEMBER, 10), "Scott Summers", "SCTTSMMRS", "555-1234-5678"));
            insert(entityManager, createPersona(false, LocalDate.of(1939, Month.MAY, 1), "Dick Grayson", "DKGRYSN", "555-2345-6789"));
            insert(entityManager, createPersona(false, LocalDate.of(1962, Month.JULY, 10), "Ororo Munroe", "ORRMNR", "555-3456-7890"));
            insert(entityManager, createPersona(false, LocalDate.of(1963, Month.JANUARY, 10), "Wanda Maximoff", "WNDMXMFF", "555-5678-9012"));
            insert(entityManager, createPersona(false, LocalDate.of(1940, Month.OCTOBER, 1), "Hal Jordan", "HLJRDN", "555-6789-0123"));
            insert(entityManager, createPersona(false, LocalDate.of(1979, Month.JUNE, 5), "Barbara Ann Minerva", "BRBRNNMNRV", "555-7890-1234"));
            insert(entityManager, createPersona(true, LocalDate.of(1991, Month.JULY, 5), "Victor Stone", "CVS123", "555-8765-1792"));
            insert(entityManager, createPersona(true, LocalDate.of(1999, Month.JANUARY, 1), "Maya López", "MYLPZ9", "555-7890-7564"));
            insert(entityManager, createPersona(true, LocalDate.of(1984, Month.NOVEMBER, 1), "Jericho Wilson", "JRCWLSN", "555-3456-6718"));
            insert(entityManager, createPersona(false, LocalDate.of(1984, Month.APRIL, 7), "Scott Pilgrim", "SCTPLGRM", "555-1234-1859"));


    }

    /**
     * Método que verifica que una persona cumpla con los requisitos para realizar un tramite.
     * 
     * @param entityManager
     * @param persona
     * @return true si cumple los requisitos, false si no los cumple.
     */
    
    @Override
    public boolean checkPersona(EntityManager entityManager, Persona persona) {

        return persona.getRfc() != null && persona.getNombre() != null && persona.getFechaNacimiento() != null && persona.getTelefono() != null;
    }

    
    /**
     * Método que mediante una consulta dinamica regresa una lista con todas las personas 
     * registradas en la base de datos que cumplan con los parámetros de busqueda.
     * Arroja una excepción "EntityNotFoundException" en caso de no encontrar nada. 
     * 
     * @param entityManager
     * @param id
     * @param discapacidad
     * @param fechaInicio
     * @param fechaFin
     * @param nombre
     * @param rfc
     * @param telefono
     * @throws EntityNotFoundException
     * @return ArrayList<Persona>;
     */
    @Override
    public ArrayList<Persona> getListaPersonas(EntityManager entityManager, Long id, Boolean discapacidad, LocalDate fechaInicio, LocalDate fechaFin, String nombre, String rfc, String telefono) throws EntityNotFoundException{

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> persona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(persona);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {
            
            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(persona.get("id"), parametro));

        }

        if (discapacidad != null) {

            ParameterExpression<Boolean> parametro = criteriaBuilder.parameter(Boolean.class, "discapacidad");
            criteria.add(criteriaBuilder.equal(persona.get("discapacidad"), parametro));

        }

        if (fechaInicio != null) {

            ParameterExpression<LocalDate> parametro = criteriaBuilder.parameter(LocalDate.class, "fechaInicio");
            criteria.add(criteriaBuilder.greaterThanOrEqualTo(persona.get("fechaNacimiento"), parametro));

        }

        if (fechaFin != null) {

            ParameterExpression<LocalDate> parametro = criteriaBuilder.parameter(LocalDate.class, "fechaFin");
            criteria.add(criteriaBuilder.lessThanOrEqualTo(persona.get("fechaNacimiento"), parametro));

        }

        if (nombre != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "nombre");
            criteria.add(criteriaBuilder.like(persona.get("nombre"), parametro));

        }

        if (rfc != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "rfc");
            criteria.add(criteriaBuilder.like(persona.get("rfc"), parametro));

        }

        if (telefono != null) {

            ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class, "telefono");
            criteria.add(criteriaBuilder.like(persona.get("telefono"), parametro));

        }
        if(criteria.size()==0){
            
        }
        else if (criteria.size()==1){
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Persona> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (discapacidad != null) {
            query.setParameter("discapacidad", discapacidad);
        }
        if (fechaInicio != null) {
            query.setParameter("fechaInicio", fechaInicio);
        }
        if (fechaFin != null) {
            query.setParameter("fechaFin", fechaFin);
        }
        if (nombre != null) {
            query.setParameter("nombre","%"+ nombre+"%");
        }
        if (rfc != null) {
            query.setParameter("rfc","%"+ rfc+"%");
        }
        if (telefono != null) {
            query.setParameter("telefono","%"+ telefono+"%");
        }

        ArrayList<Persona> resultados = new ArrayList();
        resultados.addAll(query.getResultList());
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron personas con los datos proporcionados");
        }
        return resultados;
    }
}