/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.dominio;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "Carros")
@DiscriminatorValue("Carro")
public class Carro extends Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    public Carro() {
    }

    @Override
    public String toString() {
        return "com.itson.dominio.Carro[ id=" + id + " ]";
    }   
}