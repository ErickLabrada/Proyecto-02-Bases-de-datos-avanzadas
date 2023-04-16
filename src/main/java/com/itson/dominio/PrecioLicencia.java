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
public class PrecioLicencia extends Precio implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Column(name = "Vigencia")
    private Vigencia vigencia;

    @Column(name = "PrecioNormal")
    private double PrecioNormal;

    @Column(name = "PrecioDiscapacidad")
    private double precioDiscapacidad;

    public PrecioLicencia() {
    }

    public Vigencia getVigencia() {
        return vigencia;
    }

    public void setVigencia(Vigencia vigencia) {
        this.vigencia = vigencia;
    }

    public double getPrecioNormal() {
        return PrecioNormal;
    }

    public void setPrecioNormal(double PrecioNormal) {
        this.PrecioNormal = PrecioNormal;
    }

    public double getPrecioDiscapacidad() {
        return precioDiscapacidad;
    }

    public void setPrecioDiscapacidad(double precioDiscapacidad) {
        this.precioDiscapacidad = precioDiscapacidad;
    }
    
    @Override
    public String toString() {
        return "com.itson.dominio.PrecioLicencia[ id=" + id + " ]";
    }
    
}
