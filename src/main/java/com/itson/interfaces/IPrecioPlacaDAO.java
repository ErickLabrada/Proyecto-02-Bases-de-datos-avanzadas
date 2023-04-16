/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.interfaces;

import com.itson.dominio.PrecioLicencia;
import com.itson.dominio.PrecioPlaca;
import com.itson.dominio.Vigencia;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Erick
 */
public interface IPrecioPlacaDAO {

    void insert(EntityManager entityManager, PrecioPlaca precioPlaca);

    PrecioPlaca createPrecioPlaca(EntityManager entityManager, double precioVehiculoNuevo, double precioVehiculoViejo);

    void updatePrecioPlaca(EntityManager entityManager, double precioVehiculoNuevo, double precioVehiculoViejo);
    
    PrecioPlaca getPrecioPlaca(EntityManager entityManager);

    ArrayList<PrecioPlaca> getListaPrecios(EntityManager entityManager, Long id, Double precioVehiculoNuevo, Double precioVehiculoViejo);
}
