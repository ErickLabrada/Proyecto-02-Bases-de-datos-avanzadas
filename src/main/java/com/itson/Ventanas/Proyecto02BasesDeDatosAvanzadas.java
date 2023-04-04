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

        PantallaInicio mainScreen = new PantallaInicio();
        mainScreen.setVisible(true);

//        personaDAO.masiveInsert(entityManager);
//        licenciaDAO.insertar(entityManager, personaDAO.consultar(entityManager, 1L), null, Vigencia.ONE_YEAR);        
//        pagoDAO.insertar(entityManager, 1500, LocalDate.now(), tramiteDAO.consultar(entityManager, 1L));
//        tramiteDAO.addPago(entityManager, pagoDAO.consultar(entityManager, 1L));
//
//        carroDAO.insertar(entityManager, "Rojo", "Recta", "Razer", "Bonito", "HIMYM", null);       
//
//        placaDAO.insertar(entityManager, LocalDate.now(), true, null, carroDAO.consultar(entityManager, 1L), vehiculoDAO, personaDAO.consultar(entityManager, 1L));
//
//        
//        placaDAO.insertar(entityManager, LocalDate.now(), true, null, carroDAO.consultar(entityManager, 1L), vehiculoDAO, personaDAO.consultar(entityManager, 1L));

    }
}
