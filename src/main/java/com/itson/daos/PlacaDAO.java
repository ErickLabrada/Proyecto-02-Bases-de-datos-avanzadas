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
import com.itson.dominio.Placa;
import com.itson.dominio.Vehiculo;
import com.itson.interfaces.IPlacasDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase DAO para la entidad Placa, implementa la interfaz IPlacasDAO
 * 
 * @author Erick
 */
public class PlacaDAO extends TramiteDAO implements IPlacasDAO {

     
    /**
     * Mértodo que inserta a la base de datos un objeto de tipo placa.
     * 
     * @param entityManager
     * @param placa 
     */
    
    @Override
    public void insert(EntityManager entityManager, Placa placa, VehiculoDAO vehiculoDAO) throws InvalidLicenseException {
                
        entityManager.getTransaction().begin();
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        vehiculoDAO.addPlacas(entityManager, placa.getVehiculo(), placa);

    }
/**
 * Método que génera de manera aleatoria una matricula única
 * 
 * @param entityManager
 * @return String matricula
 */
    @Override
    public String generateMatricula(EntityManager entityManager) {

        String matricula;

        do {

            String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numeros = "0123456789";

            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(letras.length());
                char letra = letras.charAt(index);
                stringBuilder.append(letra);
            }

            stringBuilder.append("-");
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(numeros.length());
                char numero = numeros.charAt(index);
                stringBuilder.append(numero);
            }

            matricula = stringBuilder.toString();
        } while (!checkMatricula(matricula, getMatriculas(entityManager)));
        return matricula;

    }

    /**
     * Método que obtiene una lista de todas las matriculas registradas
     * @param entityManager
     * @return ArrayList<String>
     */
    
    @Override
    public ArrayList<String> getMatriculas(EntityManager entityManager) {

        TypedQuery<String> query = entityManager.createQuery("SELECT p.matricula FROM Placa p", String.class);
        return new ArrayList(query.getResultList());

    }
/**
 * Verifica que la mátricula no exista en la base de datos
 * @param matricula
 * @param matriculas
 * @return true si no existe, false si existe
 */
    @Override
    public boolean checkMatricula(String matricula, ArrayList<String> matriculas) {

        for (String matriculaActual : matriculas) {

            if (matriculaActual.equals(matricula)) {
                return false;
            }

        }
        return true;
    }
/**
 * Método que crea un objeto de tipo Placa
 * 
 * @param entityManager
 * @param fechaRecepcion
 * @param estado
 * @param pago
 * @param vehiculo
 * @param licenciaDAO
 * @param persona
 * @throws InvalidLicenseException
 * @return Placa
 */
    @Override
    public Placa create(EntityManager entityManager, LocalDate fechaRecepcion, boolean estado, Pago pago, Vehiculo vehiculo,  LicenciaDAO licenciaDAO, Persona persona) throws InvalidLicenseException {

        Placa placa = new Placa();
        
        if (!licenciaDAO.checkDriversLicense(entityManager,persona)){
            throw new InvalidLicenseException();
        }
        
        placa.setEstado(estado);
        placa.setFechaRecepcion(fechaRecepcion);

        placa.setMatricula(generateMatricula(entityManager));
        placa.setPago(pago);
        placa.setVehiculo(vehiculo);

        return placa;

    }

    /**
     * Método que mediante una consulta dinamica regresa una lista con todas las
     * placas registradas en la base de datos que cumplan con los parámetros
     * de busqueda. Arroja una excepción "EntityNotFoundException" en caso de no
     * encontrar nada.
     *
     * @param entityManager
     * @param id
     * @param estado
     * @param fechaRecepcion
     * @param pago
     * @param vehiculo
     * @param persona
     * @throws EntityNotFoundException
     * @return ArrayList<Placa>;
     */
    @Override
    public ArrayList<Placa> getListaPersonas(EntityManager entityManager, Long id, Boolean estado, LocalDate fechaRecepcion, Pago pago, Vehiculo vehiculo, Persona persona) throws EntityNotFoundException{
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Placa> criteriaQuery = criteriaBuilder.createQuery(Placa.class);
        Root<Placa> placa = criteriaQuery.from(Placa.class);
        criteriaQuery.select(placa);
        criteriaQuery.distinct(true);
        StringBuilder stringBuilder;
        ArrayList<Predicate> criteria = new ArrayList<Predicate>();
        if (id != null) {
            ParameterExpression<Long> parametro = criteriaBuilder.parameter(Long.class, "id");
            criteria.add(criteriaBuilder.equal(placa.get("id"), parametro));
        }
        if (estado != null) {
            ParameterExpression<Boolean> parametro = criteriaBuilder.parameter(Boolean.class, "estado");
            criteria.add(criteriaBuilder.equal(placa.get("estado"), parametro));
        }
        if (fechaRecepcion != null) {
            ParameterExpression<LocalDate> parametro = criteriaBuilder.parameter(LocalDate.class, "fechaRecepcion");
            criteria.add(criteriaBuilder.equal(placa.get("fechaRecepcion"), parametro));
        }
        if (pago != null) {
            ParameterExpression<Pago> parametro = criteriaBuilder.parameter(Pago.class, "pago");
            criteria.add(criteriaBuilder.equal(placa.get("pago"), parametro));
        }
        if (vehiculo != null) {
            ParameterExpression<Vehiculo> parametro = criteriaBuilder.parameter(Vehiculo.class, "vehiculo");
            criteria.add(criteriaBuilder.equal(placa.get("vehiculo"), parametro));
        }
        if (persona != null) {
            ParameterExpression<Persona> parametro = criteriaBuilder.parameter(Persona.class, "persona");
            criteria.add(criteriaBuilder.equal(placa.get("persona"), parametro));
        }
        if(criteria.size()==0){}
        else if (criteria.size()==1){
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
        }
        TypedQuery<Placa> query = entityManager.createQuery(criteriaQuery);
        if (id != null) {
            query.setParameter("id", id);
        }
        if (estado != null) {
            query.setParameter("estado", estado);
        }
        if (fechaRecepcion != null) {
            query.setParameter("fechaRecepcion", fechaRecepcion);
        }
        if (pago != null) {
            query.setParameter("pago", pago);
        }
        if (vehiculo != null) {
            query.setParameter("vehiculo",vehiculo);
        }
        if (persona != null) {
            query.setParameter("persona",persona);
        }

        ArrayList<Placa> resultados = new ArrayList();
        resultados.addAll(query.getResultList());
        
        if(resultados.isEmpty()){
            throw new EntityNotFoundException("No se encontraron placas con los datos proporcionados");
        }
        return resultados;
    }
}