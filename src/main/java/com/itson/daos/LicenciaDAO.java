/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.itson.daos;

import com.itson.Exceptions.InvalidLicenseException;
import com.itson.Exceptions.UnpaidProcedureException;
import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vigencia;
import com.itson.interfaces.ILicenciasDAO;
import java.time.LocalDate;
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
 *
 * Clase DAO para la entidad Licencia, implementa la interfaz ILicenciaDAO
 *
 * @author Erick
 */
public class LicenciaDAO extends TramiteDAO implements ILicenciasDAO {

    /**
     * Método que inserta a la base de datos un objeto de tipo licencia.
     *
     * @param entityManager
     * @param licencia
     */
    @Override
    public void insert(EntityManager entityManager, Licencia licencia) {

        System.out.println(licencia.getPersona().getNombre());
       if (!getLicencias(entityManager, licencia.getPersona().getId()).isEmpty()){
       // if (!licencia.getPersona().getTramite().isEmpty()){
            updateLicencias(entityManager, licencia.getPersona());
        }
        entityManager.getTransaction().begin();
        entityManager.persist(licencia);
        entityManager.getTransaction().commit();
    }

    /**
     * Método que verifica la vigencia de una licencia
     *
     * @param entityManager
     * @param licencia
     * @throws UnpaidProcedureException
     * @return true si está vigente, false si no
     *
     */
    @Override
    public boolean checkVality(EntityManager entityManager, Licencia licencia) throws UnpaidProcedureException {

        LocalDate fechaVigencia;

        switch (licencia.getVigencia()) {
            case ONE_YEAR:
                fechaVigencia = licencia.getPago().getFechaPago().plusYears(1);
                System.out.println("a");
                break;
            case TWO_YEARS:
                fechaVigencia = licencia.getPago().getFechaPago().plusYears(2);
                System.out.println("b");
                break;
            case THREE_YEARS:
                System.out.println("c");
                fechaVigencia = licencia.getPago().getFechaPago().plusYears(3);
                break;
            default:
                throw new UnpaidProcedureException("La licencia aun no se ha pagado D:<");
        }

        return LocalDate.now().isBefore(fechaVigencia);
    }

    /**
     * Método que obtiene una lista de todas las licencias registradas a nombre de una persona
     *
     * @param entityManager
     * @param personaID
     * @return ArrayList<Licencia>
     */
    @Override
    public ArrayList<Licencia> getLicencias(EntityManager entityManager, Long personaID) {

        TypedQuery<Licencia> query = entityManager.createQuery("SELECT l FROM Licencia l WHERE l.persona.id = :personaID", Licencia.class);
        query.setParameter("personaID", personaID);
        ArrayList<Licencia> licencias = new ArrayList<Licencia>(query.getResultList());
            return licencias;
    }

    /**
     * Método que verifica las licencias de una persona
     *
     * @param entityManager
     * @param persona
     * @throws InvalidLicenseException
     * @return true si tiene una licencia vigente, false si no
     */
    @Override
    public boolean checkDriversLicense(EntityManager entityManager, Persona persona) throws InvalidLicenseException {

        ArrayList<Licencia> licencias = getListaLicencias(entityManager, null, persona, null, null, true);

        for (Licencia licenciaIndex : licencias) {
            if (checkVality(entityManager, licenciaIndex)) {
                return true;
            }
        }
        throw new InvalidLicenseException("La licencia ha caducado");
    }
    
        /**
     * Método que actualiza el estado de las licencias de una persona.
     *
     * @param entityManager
     * @param persona
     */
    @Override
    public void updateLicencias(EntityManager entityManager, Persona persona) {

        List<Licencia> licencias = getListaLicencias(entityManager, null, persona, null, null, Boolean.TRUE);

        for (Licencia licencia : licencias) {
            licencia.setEstado(false);
            entityManager.getTransaction().begin();
            entityManager.merge(licencia);
            entityManager.getTransaction().commit();
        }

    }
    
    public void deleteRelatedProcedures(EntityManager entityManager, Pago pago){
        TramiteDAO tramiteDAO = new TramiteDAO();
        ArrayList <Tramite> tramites = tramiteDAO.getListaTramites(entityManager, null, pago.getTramite().getPersona(), null, null, null);
                for (Tramite tramite: tramites){
                    tramiteDAO.delete(entityManager,tramite.getId());
                }
    }

    /**
     * Método que crea un objeto de tipo Licencia.
     *
     * @param persona
     * @param pago
     * @param vigencia
     * @param estado
     * @return
     */
    @Override
    public Licencia createLicencia(Persona persona, Pago pago, Vigencia vigencia) {

        Licencia licencia = new Licencia();
        licencia.setPago(pago);
        licencia.setPersona(persona);
        licencia.setVigencia(vigencia);
        licencia.setEstado(true);

        return licencia;

    }
    
     /**
     * Método que actualiza el estado de las placas de un vehiculo.
     *
     * @param entityManager
     * @param vehiculo
     */


    /**
     * Método que mediante una consulta dinamica regresa una lista con todas las
     * licencias registradas en la base de datos que cumplan con los parámetros
     * de busqueda.Arroja una excepción "EntityNotFoundException" en caso de no
 encontrar nada.
     *
     * @param entityManager
     * @param id
     * @param persona
     * @param pago
     * @param vigencia
     * @param estado
     * @throws EntityNotFoundException
     * @return ArrayList<Licencia>
     */
    @Override
    public ArrayList<Licencia> getListaLicencias(EntityManager entityManager, Long id, Persona persona, Pago pago, Vigencia vigencia, Boolean estado) throws EntityNotFoundException {

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
        
         if (estado != null) {

            ParameterExpression<Boolean> parametro = criteriaBuilder.parameter(Boolean.class, "estado");
            criteria.add(criteriaBuilder.equal(licencia.get("estado"), parametro));

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

        if (criteria.size() == 0) {

        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Licencia> query = entityManager.createQuery(criteriaQuery);

        if (id != null) {
            query.setParameter("id", id);
        }
        if (estado!= null) {
            query.setParameter("estado", estado);
        }
        if (persona != null) {
            query.setParameter("persona", persona);
        }
        if (pago != null) {
            query.setParameter("pago", pago);
        }
        if (vigencia != null) {
            query.setParameter("vigencia", vigencia);
        }

        ArrayList<Licencia> resultados = new ArrayList();
        resultados.addAll(query.getResultList());

        if (resultados.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron licencias con los datos proporcionados");
        }
        return resultados;
    }

}