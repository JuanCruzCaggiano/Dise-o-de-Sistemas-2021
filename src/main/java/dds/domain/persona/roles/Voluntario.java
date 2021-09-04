package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.*;

import javax.mail.MessagingException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Voluntario extends RolPersona {

    private static Voluntario rolVoluntario = new Voluntario() ;

    public static Voluntario getVoluntario() {return rolVoluntario;}

    public Voluntario() {
        this.id = 3;
        this.nombre = "Voluntario";
        this.permisos.add(new ValidarPublicacion());
        this.permisos.add(new RechazarPublicacion());
        //this.permisos.add(new GestionarPreguntasAdopcion()); TODO
    }
}