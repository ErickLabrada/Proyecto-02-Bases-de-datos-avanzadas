/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.daos;

import com.itson.Exceptions.InvalidLicenceException;
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
import javax.persistence.TypedQuery;

/**
 *
 * @author Erick
 */
public class PlacaDAO extends TramiteDAO implements IPlacasDAO {

    @Override
    public void insertar(EntityManager entityManager, LocalDate fechaRecepcion, boolean estado, Pago pago, Vehiculo vehiculo, VehiculoDAO vehiculoDAO, Persona persona) throws InvalidLicenceException {

        Placa placa = new Placa();

        if (!verificaLicenciaConductor(entityManager,persona)){
            throw new InvalidLicenceException();
        }
        
        placa.setEstado(estado);
        placa.setFechaRecepcion(fechaRecepcion);

        placa.setMatricula(generaMatricula(entityManager));
        placa.setPago(pago);
        placa.setVehiculo(vehiculo);
        entityManager.getTransaction().begin();
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        vehiculoDAO.addPlacas(entityManager, vehiculo, placa);

    }

    @Override
    public String generaMatricula(EntityManager entityManager) {

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
        } while (!verificaMatricula(matricula, getMatriculas(entityManager)));
        System.out.println("a");
        return matricula;

    }

    @Override
    public ArrayList<String> getMatriculas(EntityManager entityManager) {

        TypedQuery<String> query = entityManager.createQuery("SELECT p.matricula FROM Placa p", String.class);
        return new ArrayList(query.getResultList());

    }

    @Override
    public boolean verificaMatricula(String matricula, ArrayList<String> matriculas) {

        for (String matriculaActual : matriculas) {

            if (matriculaActual.equals(matricula)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean verificaLicenciaConductor(EntityManager entityManager, Persona persona) {

        PersonaDAO personaDAO = new PersonaDAO();
        LicenciaDAO licenciaDAO = new LicenciaDAO();
        ArrayList<Licencia> licencias = personaDAO.getLicencias(entityManager, persona.getId());

        for (Licencia licenciaIndex : licencias) {
            if (licenciaDAO.verificaVigencia(entityManager, licenciaIndex)) {
                return true;
            }
        }
        return false;
    }
}