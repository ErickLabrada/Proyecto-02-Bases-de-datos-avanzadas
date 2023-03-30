package com.itson.dominio;

import com.itson.dominio.Vehiculo;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-30T11:46:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Placas.class)
public class Placas_ extends Tramite_ {

    public static volatile SingularAttribute<Placas, Boolean> estado;
    public static volatile SingularAttribute<Placas, String> matricula;
    public static volatile SingularAttribute<Placas, Vehiculo> vehiculo;
    public static volatile SingularAttribute<Placas, LocalDate> fechaRecepcion;

}