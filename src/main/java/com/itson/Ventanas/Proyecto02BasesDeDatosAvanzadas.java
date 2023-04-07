/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.itson.Ventanas;

import com.itson.daos.CarroDAO;
import com.itson.daos.LicenciaDAO;
import com.itson.daos.PagoDAO;
import com.itson.daos.PersonaDAO;
import com.itson.daos.PlacaDAO;
import com.itson.daos.TramiteDAO;
import com.itson.daos.VehiculoDAO;
import com.itson.dominio.Carro;
import com.itson.dominio.Vehiculo;
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
        TramiteDAO tramiteDAO = new TramiteDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        //PantallaInicio mainScreen = new PantallaInicio();
        //mainScreen.setVisible(true);

        personaDAO.masiveInsert(entityManager);
        
        
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 1L), null, Vigencia.ONE_YEAR));
        pagoDAO.insert(entityManager, pagoDAO.create(1500, LocalDate.now(), tramiteDAO.query(entityManager, 1L)), tramiteDAO);

        
        
        carroDAO.insert(entityManager, carroDAO.createCarro("Rojo", "Recta", "Razer", "Bonito", "HIMYM", null));  
        carroDAO.insert(entityManager, carroDAO.createCarro("Verde", "Curva", "SteelSeries", "feo", "The Office", null));  
        carroDAO.insert(entityManager, carroDAO.createCarro("Azul", "Oblicua", "Hyperx", "Bonito", "HIMYF", null));  
        carroDAO.insert(entityManager, carroDAO.createCarro("Morado", "Recta", "Logitech", "meh", "TLOU", null));  
        carroDAO.insert(entityManager, carroDAO.createCarro("Rojo", "Curva", "Razer", "feo", "Brooklyn99", null));  


        
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), true, null, carroDAO.query(entityManager, 1L),licenciaDAO, personaDAO.query(entityManager,1l)), vehiculoDAO);
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), true, null, carroDAO.query(entityManager, 1L),licenciaDAO, personaDAO.query(entityManager,1l)), vehiculoDAO);

//
//        //Long id, Boolean discapacidad, LocalDate fechaInicio, LocalDate fechaFin, String nombre, String rfc, String telefono
//        ArrayList<Vehiculo> listaVehiculo = vehiculoDAO.getListaVehiculo(entityManager, null, null, null, null, null, null);
//        System.out.println("-------------------------------------");
//        for (Vehiculo vehiculo : listaVehiculo) {
//            
//            System.out.println((Carro) vehiculo);
//            
//            System.out.println(vehiculo.getId());
//            System.out.println(vehiculo.getColor());
//            System.out.println(vehiculo.getLinea());
//            System.out.println(vehiculo.getMarca());
//            System.out.println(vehiculo.getModelo());
//            System.out.println(vehiculo.getSerie());
//            System.out.println("-------------------------------------");
//
//        }
//System.out.println(personaDAO.consultar(entityManager,1l));
//System.out.println(tramiteDAO.consultar(entityManager, 1l));
    }
}