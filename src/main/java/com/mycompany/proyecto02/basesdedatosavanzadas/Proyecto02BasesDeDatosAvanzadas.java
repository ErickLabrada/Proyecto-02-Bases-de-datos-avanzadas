/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto02.basesdedatosavanzadas;

import com.itson.dominio.Carro;
import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Placas;
import com.itson.dominio.Vigencia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        Pago pagoPlacas = createPagos(1600, LocalDate.now());

        insertPagos(pagoPlacas);
        Carro carro = createCarro("Rojo", "recta", "Toyota", "Bonito", "How I Meet Your Mother");
        List<Placas> listaPlacas = new ArrayList();
        listaPlacas.add( createPlacas("ABC-123", LocalDate.now(), true, pagoPlacas));
        listaPlacas.get(0).setVehiculo(carro);
        insertCarros(carro, listaPlacas);


        Pago pagoLicencia = createPagos(1500, LocalDate.now());
        insertPagos(pagoLicencia);
        Licencia licencia = createLicencias(Vigencia.TWO_YEARS, pagoLicencia);
        insertLicencias(licencia);

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
    
       public static void insertCarros(Carro carro, List placas) {
    
        carro.setPlacas(placas);
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
    }
       
       public static Carro createCarro(String color, String linea, String marca, String modelo, String serie){
           
        Carro carro = new Carro();
        carro.setColor(color);
        carro.setLinea(linea);
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setSerie(serie);
        return carro;
        
       }

    public static Pago createPagos(double monto, LocalDate fechaPago) {
        Pago pago = new Pago();
        pago.setFechaPago(fechaPago);
        pago.setMonto(monto);
        return pago;
    }

       public static void insertPagos(Pago pago){
           entityManager.getTransaction().begin();
           entityManager.persist(pago);
           entityManager.getTransaction().commit();
       }
       
       public static void insertLicencias(Licencia licencia){
           entityManager.getTransaction().begin();
           entityManager.persist(licencia);
           entityManager.getTransaction().commit();
       }
    
  
       
       public static Licencia createLicencias(Vigencia vigencia,Pago pago){
           Licencia licencia = new Licencia();
           licencia.setVigencia(vigencia);
           licencia.setPago(pago);
           return licencia;
       }
       
       public static void insertPlacas(Placas placas, Carro carro){
           
           placas.setVehiculo(carro);
           entityManager.getTransaction().begin();
           entityManager.persist(placas);
           entityManager.getTransaction().commit();
           
       }

       public static Placas createPlacas(String matricula,LocalDate fechaRecepcion, boolean estado, Pago pago){
       
           Placas placas = new Placas();
           placas.setEstado(estado);
           placas.setFechaRecepcion(fechaRecepcion);
           placas.setPago(pago);
           placas.setMatricula(matricula);
           return placas;
       
       }
}
