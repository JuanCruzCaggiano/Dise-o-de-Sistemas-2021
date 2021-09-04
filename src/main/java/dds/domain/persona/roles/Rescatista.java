package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.BuscarHogarDeTransito;
import dds.domain.persona.transaccion.EncontreMascotaPerdidaConChapita;
import dds.domain.persona.transaccion.EncontreMascotaPerdidaSinChapita;
import dds.domain.persona.transaccion.Transaccion;

import javax.mail.MessagingException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("R")
public class Rescatista extends RolPersona {
    public Rescatista() {
        this.nombre = "Rescatista";
        this.permisos.add(new EncontreMascotaPerdidaConChapita());
        this.permisos.add(new EncontreMascotaPerdidaSinChapita());
        this.permisos.add(new BuscarHogarDeTransito());
    }
}
