package com.itson.dominio;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-30T11:46:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Persona, Boolean> discapacidad;
    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> rfc;

}