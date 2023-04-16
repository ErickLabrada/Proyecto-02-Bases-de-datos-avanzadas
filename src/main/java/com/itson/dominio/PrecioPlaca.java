/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Erick
 */
@Entity
public class PrecioPlaca extends Precio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "PrecioVehiculoNuevo")
    private double precioVehiculoNuevo;

    @Column(name = "PrecioVehiculoViejo")
    private double precioVehiculoViejo;

    public PrecioPlaca() {
    }

    public double getPrecioVehiculoNuevo() {
        return precioVehiculoNuevo;
    }

    public void setPrecioVehiculoNuevo(double precioVehiculoNuevo) {
        this.precioVehiculoNuevo = precioVehiculoNuevo;
    }

    public double getPrecioVehiculoViejo() {
        return precioVehiculoViejo;
    }

    public void setPrecioVehiculoViejo(double precioVehiculoViejo) {
        this.precioVehiculoViejo = precioVehiculoViejo;
    }

    @Override
    public String toString() {
        return "com.itson.dominio.PrecioPlaca[ id=" + id + " ]";
    }

}
