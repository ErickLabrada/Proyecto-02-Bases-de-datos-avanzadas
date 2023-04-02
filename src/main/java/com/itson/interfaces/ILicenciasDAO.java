/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Vigencia;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface ILicenciasDAO {

    void insertar(EntityManager entityManager, Persona persona, Pago pago, Vigencia vigencia);

}
