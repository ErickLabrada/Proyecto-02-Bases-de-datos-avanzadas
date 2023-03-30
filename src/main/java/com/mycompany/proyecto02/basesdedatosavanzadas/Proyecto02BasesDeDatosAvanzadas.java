/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto02.basesdedatosavanzadas;

import com.itson.dominio.Carro;
import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Placas;
import com.itson.dominio.Vehiculo;
import com.itson.dominio.Vigencia;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Erick
 */
public class Proyecto02BasesDeDatosAvanzadas {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.mycompany_Proyecto02.BasesDeDatosAvanzadas_jar_1.0-SNAPSHOTPU");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        insertPersonas(false, LocalDate.now(), "Erick Antonio Labrada Rodríguez", "LARE157938RFC48", "6681163510");
        insertCarros("Rojo", "recta", "Toyota", "Bonito", "How I Meet Your Mother");
        insertLicencias(Vigencia.TWO_YEARS);
        insertPlacas("ABC-123", LocalDate.now(), true);
        insertPagos(1500, LocalDate.now());


    }

    public static void insertPersonas(Boolean discapacidad, LocalDate fechaNacimiento, String nombre, String rfc, String telefono) {
        Persona persona = new Persona();
        persona.setDiscapacidad(discapacidad);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setNombre(nombre);
        persona.setRfc(rfc);
        persona.setTelefono(telefono);
        entityManager.getTransaction().begin();
        entityManager.persist(persona);
        entityManager.getTransaction().commit();
    }
    
       public static void insertCarros(String color, String linea, String marca, String modelo, String serie) {
    
        Carro carro = new Carro();
        carro.setColor(color);
        carro.setLinea(linea);
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setSerie(serie);
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
    }
       
       public static void insertPagos(double monto, LocalDate fechaPago){
           Pago pago = new Pago();
           pago.setFechaPago(fechaPago);
           pago.setMonto(1500);
           entityManager.getTransaction().begin();
           entityManager.persist(pago);
           entityManager.getTransaction().commit();
       }
       
       public static void insertLicencias(Vigencia vigencia){
           Licencia licencia = new Licencia();
           licencia.setVigencia(vigencia);
           entityManager.getTransaction().begin();
           entityManager.persist(licencia);
           entityManager.getTransaction().commit();
       }
    
       public static void insertPlacas(String matricula,LocalDate fechaRecepcion, boolean estado){
           
           Placas placas = new Placas();
           placas.setEstado(estado);
           placas.setFechaRecepcion(fechaRecepcion);
           placas.setMatricula(matricula);
           entityManager.getTransaction().begin();
           entityManager.persist(placas);
           entityManager.getTransaction().commit();
           
           
       }

}
