/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto02.basesdedatosavanzadas;

import com.itson.daos.CarroDAO;
import com.itson.daos.LicenciaDAO;
import com.itson.daos.PagoDAO;
import com.itson.daos.PersonaDAO;
import com.itson.daos.PlacaDAO;
import com.itson.daos.TramiteDAO;
import com.itson.dominio.Vigencia;
import java.time.LocalDate;
import java.util.ArrayList;
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

                
      
        PersonaDAO personaDAO = new PersonaDAO();
        CarroDAO carroDAO = new CarroDAO();
        PagoDAO pagoDAO = new PagoDAO();
        PlacaDAO placaDAO = new PlacaDAO();
        LicenciaDAO licenciaDAO = new LicenciaDAO();
        TramiteDAO tramiteDAO= new TramiteDAO();

        personaDAO.insertar(entityManager, true, LocalDate.now(), "Persona genérica", "RFCGenerico", "NumGenerico", new ArrayList());
        licenciaDAO.insertar(entityManager, personaDAO.consultar(entityManager, 1L), null, Vigencia.ONE_YEAR);
        carroDAO.insertar(entityManager, "Rojo", "Recta", "Razer", "Bonito", "HIMYM", null);
        placaDAO.insertar(entityManager, "ABC-123", LocalDate.now(), true, null, carroDAO.consultar(entityManager, 1L));

        pagoDAO.insertar(entityManager, 1500, LocalDate.now(), tramiteDAO.consultar(entityManager, 1L));
        tramiteDAO.addPago(entityManager, pagoDAO.consultar(entityManager, 2L));



//        Licencia licencia = createLicencias(Vigencia.ONE_YEAR, (createPagos(1500, LocalDate.now())), personaDAO.consultar(entityManager, 1L));
//        insertLicencias(licencia);
//        personaDAO.addTramites(entityManager, personaDAO.consultar(entityManager, 1L), licencia);
        
        

        //insertPersonas(false, LocalDate.now(), "Erick Antonio Labrada Rodríguez", "LARE157938RFC48", "6681163510",new ArrayList());

        //Pago pagoPlacas = createPagos(1600, LocalDate.now());

        //insertPagos(pagoPlacas);
       //Carro carro = createCarro("Rojo", "recta", "Toyota", "Bonito", "How I Meet Your Mother");
        //List<Placas> listaPlacas = new ArrayList();
        //listaPlacas.add( createPlacas("ABC-123", LocalDate.now(), true, pagoPlacas));
        //listaPlacas.get(0).setVehiculo(carro);
        //insertCarros(carro, listaPlacas);


        //Pago pagoLicencia = createPagos(1500, LocalDate.now());
        //insertPagos(pagoLicencia);

    }       
        
}