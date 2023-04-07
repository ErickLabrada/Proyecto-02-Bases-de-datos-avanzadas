/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Vigencia;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface ILicenciasDAO {

    void insert(EntityManager entityManager, Licencia licencia);

    boolean checkVality(EntityManager entityManager, Licencia licencia);

    ArrayList<Licencia> getLicencias(EntityManager entityManager, Long personaID);

    public boolean checkDriversLicense(EntityManager entityManager, Persona persona);

    Licencia createLicencia (Persona persona, Pago pago, Vigencia vigencia);
    
    ArrayList<Licencia> getListaLicencias(EntityManager entityManager, Long id,Persona persona, Pago pago, Vigencia vigencia);

}