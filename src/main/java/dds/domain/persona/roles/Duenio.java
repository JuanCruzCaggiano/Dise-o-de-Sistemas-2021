package dds.domain.persona.roles;


import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.*;

import javax.mail.MessagingException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("D")
public class Duenio extends RolPersona {

    private static Duenio rolDuenio = new Duenio() ;

    public static Duenio getDuenio() {return rolDuenio;}

    public Duenio() {
        this.id = 2;
        this.nombre = "Duenio";
        this.permisos.add(new RegistrarMascota());
        this.permisos.add(new EncontreMiMascota());
        this.permisos.add(new DarEnAdopcion());
    }
}