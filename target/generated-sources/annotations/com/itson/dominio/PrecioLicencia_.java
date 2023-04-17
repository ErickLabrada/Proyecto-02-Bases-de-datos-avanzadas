package com.itson.dominio;

import com.itson.dominio.Vigencia;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-16T19:09:58", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PrecioLicencia.class)
public class PrecioLicencia_ extends Precio_ {

    public static volatile SingularAttribute<PrecioLicencia, Vigencia> vigencia;
    public static volatile SingularAttribute<PrecioLicencia, Double> precioDiscapacidad;
    public static volatile SingularAttribute<PrecioLicencia, Double> PrecioNormal;

}