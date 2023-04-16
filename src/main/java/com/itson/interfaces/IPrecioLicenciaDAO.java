/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.PrecioLicencia;
import com.itson.dominio.Vigencia;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPrecioLicenciaDAO {

    void insert(EntityManager entityManager, PrecioLicencia precioLicencia);

    PrecioLicencia createPrecioLicencia(EntityManager entityManager, Vigencia vigencia, double precioNormal, double precioDiscapacitado);

    void updatePrecioLicencia(EntityManager entityManager, Vigencia vigencia, double precioNormal, double precioDiscapacitado);
    
    PrecioLicencia getPrecioLicencia(EntityManager entityManager, Vigencia vigencia);

    ArrayList<PrecioLicencia> getListaPrecios(EntityManager entityManager, Long id, Vigencia vigencia, Double precioNormal, Double precioDiscapacitado);

}
