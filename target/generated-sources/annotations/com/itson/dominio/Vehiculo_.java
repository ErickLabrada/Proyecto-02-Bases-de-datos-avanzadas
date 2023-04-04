package com.itson.dominio;

import com.itson.dominio.Placa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-03T16:18:44", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Vehiculo.class)
public abstract class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, String> marca;
    public static volatile SingularAttribute<Vehiculo, String> color;
    public static volatile SingularAttribute<Vehiculo, String> serie;
    public static volatile SingularAttribute<Vehiculo, Long> id;
    public static volatile SingularAttribute<Vehiculo, String> modelo;
    public static volatile SingularAttribute<Vehiculo, String> linea;
    public static volatile ListAttribute<Vehiculo, Placa> placa;

}