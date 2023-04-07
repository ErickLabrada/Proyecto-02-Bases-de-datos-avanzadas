/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.InvalidLicenseException;
import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.ILicenciasDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Erick
 */
public class LicenciaDAO extends TramiteDAO implements ILicenciasDAO {

    @Override
    public void insert(EntityManager entityManager, Licencia licencia) {

        entityManager.getTransaction().begin();
        entityManager.persist(licencia);
        entityManager.getTransaction().commit();

    }

    @Override
    public boolean checkVality(EntityManager entityManager, Licencia licencia) {

        LocalDate fechaVigencia;

        if (licencia.getVigencia().equals(Vigencia.ONE_YEAR)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(1);
        } else if (licencia.getVigencia().equals(Vigencia.TWO_YEARS)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(2);
        } else if (licencia.getVigencia().equals(Vigencia.THREE_YEARS)) {
            fechaVigencia = licencia.getPago().getFechaPago().plusYears(3);
        } else {
            throw new RuntimeException ("Vigencia Inválida");
        }

        return (licencia.getPago().getFechaPago().isBefore(fechaVigencia));
    }
    
        @Override
    public ArrayList<Licencia> getLicencias(EntityManager entityManager, Long personaID) {


        TypedQuery<Licencia> query = entityManager.createQuery("SELECT l FROM Licencia l WHERE l.persona.id = :personaID", Licencia.class);
        query.setParameter("personaID", personaID);

        return new ArrayList<Licencia>(query.getResultList());

    }

    @Override
    public boolean checkDriversLicense(EntityManager entityManager, Persona persona) {

        PersonaDAO personaDAO = new PersonaDAO();
        LicenciaDAO licenciaDAO = new LicenciaDAO();
        ArrayList<Licencia> licencias = licenciaDAO.getLicencias(entityManager, persona.getId());

        for (Licencia licenciaIndex : licencias) {
            if (licenciaDAO.checkVality(entityManager, licenciaIndex)) {
                return true;
            }
        }
        throw new InvalidLicenseException("La licencia ha caducado");
    }

    @Override
    public Licencia createLicencia(Persona persona, Pago pago, Vigencia vigencia) {

        Licencia licencia = new Licencia();
        licencia.setPago(pago);
        licencia.setPersona(persona);
        licencia.setVigencia(vigencia);

        return licencia;
        
    }
    
     @Override
    public ArrayList<Licencia> getListaLicencias(EntityManager entityManager, Long id,Persona persona, Pago pago, Vigencia vigencia) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Licencia> criteriaQuery = criteriaBuilder.createQuery(Licencia.class);
        Root<Licencia> licencia = criteriaQuery.from(Licencia.class);
        criteriaQuery.select(licencia);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;

        ArrayList<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {
            
            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(licencia.get("id"), parametro));

        }

        if (persona != null) {

            ParameterExpression<Persona> parametro = criteriaBuilder.parameter(Persona.class, "persona");
            criteria.add(criteriaBuilder.equal(licencia.get("persona"), parametro));

        }

        if (pago != null) {

            ParameterExpression<Pago> parametro = criteriaBuilder.parameter(Pago.class, "pago");
            criteria.add(criteriaBuilder.equal(licencia.get("pago"), parametro));

        }

        if (vigencia != null) {

            ParameterExpression<Vigencia> parametro = criteriaBuilder.parameter(Vigencia.class, "rfc");
            criteria.add(criteriaBuilder.equal(licencia.get("vigencia"), parametro));

        }

        if(criteria.size()==0){
            
        }
        else if (criteria.size()==1){
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Licencia> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (persona != null) {
            query.setParameter("persona", persona);
        }
        if (pago != null) {
            query.setParameter("pago", pago);
        }
        if (vigencia != null) {
            query.setParameter("vigencia",vigencia);
        }

        ArrayList<Licencia> resultados = new ArrayList();
        resultados.addAll(query.getResultList());
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron licencias con los datos proporcionados");
        }
        return resultados;
    }
    
}