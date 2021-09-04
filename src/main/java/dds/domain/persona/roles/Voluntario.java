package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.*;

import javax.mail.MessagingException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("V")
public class Voluntario extends RolPersona {
    public Voluntario() {
        this.nombre = "Voluntario";
        this.permisos.add(new ValidarPublicacion());
        this.permisos.add(new RechazarPublicacion());
        //this.permisos.add(new GestionarPreguntasAdopcion()); TODO
    }
}