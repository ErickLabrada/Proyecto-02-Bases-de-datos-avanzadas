package com.itson.dominio;

import com.itson.dominio.Tramite;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-16T22:00:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Double> monto;
    public static volatile SingularAttribute<Pago, Tramite> tramite;
    public static volatile SingularAttribute<Pago, Long> id;
    public static volatile SingularAttribute<Pago, LocalDate> fechaPago;

}