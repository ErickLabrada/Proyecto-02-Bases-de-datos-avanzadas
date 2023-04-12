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
import com.itson.dominio.Tramite;
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
    static PersonaDAO personaDAO = new PersonaDAO();
    static CarroDAO carroDAO = new CarroDAO();
    static PagoDAO pagoDAO = new PagoDAO();
    static PlacaDAO placaDAO = new PlacaDAO();
    static LicenciaDAO licenciaDAO = new LicenciaDAO();
    static TramiteDAO tramiteDAO = new TramiteDAO();
    static VehiculoDAO vehiculoDAO = new VehiculoDAO();

    public static void main(String[] args) {

//        PantallaInicio mainScreen = new PantallaInicio();
//        mainScreen.setVisible(true);
        personaDAO.masiveInsert(entityManager);
        registraLicencias();
        pagarTramite(tramiteDAO.query(entityManager, 1L));
        pagarTramite(tramiteDAO.query(entityManager, 2L));
        pagarTramite(tramiteDAO.query(entityManager, 3L));
        pagarTramite(tramiteDAO.query(entityManager, 4L));
        pagarTramite(tramiteDAO.query(entityManager, 5L));
        pagarTramite(tramiteDAO.query(entityManager, 6L));
        registraCarros();
        registraPlacas();
        pagoDAO.delete(entityManager,1L);
        //placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), null, carroDAO.query(entityManager, 1L),licenciaDAO, personaDAO.query(entityManager,1l)), vehiculoDAO);

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

    public static void pagarTramite(Tramite tramite) {
        pagoDAO.insert(entityManager, pagoDAO.create(1500, LocalDate.now(), tramite), tramiteDAO);
    }
    public static void registraLicencias() {
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 1L), null, Vigencia.ONE_YEAR));
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 2L), null, Vigencia.ONE_YEAR));
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 2L), null, Vigencia.TWO_YEARS));
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 2L), null, Vigencia.THREE_YEARS));
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 3L), null, Vigencia.ONE_YEAR));
        licenciaDAO.insert(entityManager, licenciaDAO.createLicencia(personaDAO.query(entityManager, 3L), null, Vigencia.THREE_YEARS));
    }

    public static void registraCarros() {
        carroDAO.insert(entityManager, carroDAO.createCarro("Rojo", "Recta", "Razer", "Bonito", "HIMYM", null));
        carroDAO.insert(entityManager, carroDAO.createCarro("Verde", "Curva", "SteelSeries", "feo", "The Office", null));
        carroDAO.insert(entityManager, carroDAO.createCarro("Azul", "Oblicua", "Hyperx", "Bonito", "HIMYF", null));
        carroDAO.insert(entityManager, carroDAO.createCarro("Morado", "Recta", "Logitech", "meh", "TLOU", null));
        carroDAO.insert(entityManager, carroDAO.createCarro("Rojo", "Curva", "Razer", "feo", "Brooklyn99", null));
    }

    public static void registraPlacas(){
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), null, carroDAO.query(entityManager, 1L),licenciaDAO, personaDAO.query(entityManager,1l)), vehiculoDAO);
        pagarTramite(tramiteDAO.query(entityManager, 7L));
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), null, carroDAO.query(entityManager, 1L),licenciaDAO, personaDAO.query(entityManager,1l)), vehiculoDAO);
        pagarTramite(tramiteDAO.query(entityManager, 8L));
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), null, carroDAO.query(entityManager, 2L),licenciaDAO, personaDAO.query(entityManager,2l)), vehiculoDAO);
        pagarTramite(tramiteDAO.query(entityManager, 9L));
        placaDAO.insert(entityManager, placaDAO.create(entityManager, LocalDate.now(), null, carroDAO.query(entityManager, 3L),licenciaDAO, personaDAO.query(entityManager,3l)), vehiculoDAO);
        pagarTramite(tramiteDAO.query(entityManager, 10L));
    }
}