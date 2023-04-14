package com.itson.dominio;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-13T23:27:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Placa.class)
public class Placa_ extends Tramite_ {

    public static volatile SingularAttribute<Placa, Boolean> estado;
    public static volatile SingularAttribute<Placa, String> matricula;
    public static volatile SingularAttribute<Placa, LocalDate> fechaRecepcion;

}