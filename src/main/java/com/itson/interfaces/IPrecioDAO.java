/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.Licencia;
import com.itson.dominio.Pago;
import com.itson.dominio.Persona;
import com.itson.dominio.Precio;
import com.itson.dominio.Vigencia;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPrecioDAO {

    Precio query(EntityManager entityManager, Long idPrecio);

    void delete(EntityManager entityManager, Long idPrecio);

    ArrayList<Precio> getListaPrecios(EntityManager entityManager, Long id, Vigencia vigencia, Double precioNormal, Double precioDiscapacitado, Double precioVehiculoNuevo, Double precioVehiculoviejo);

}
