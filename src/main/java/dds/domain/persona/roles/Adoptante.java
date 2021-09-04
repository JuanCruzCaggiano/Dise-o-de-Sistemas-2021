package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.QuieroAdoptar;
import dds.domain.persona.transaccion.SolicitarAdopcion;
import dds.domain.persona.transaccion.Transaccion;
import dds.domain.persona.transaccion.DarEnAdopcion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("A")
public class Adoptante extends RolPersona {

    private static Adoptante rolAdoptante = new Adoptante() ;

    public static Adoptante getAdoptante() {return rolAdoptante;}

    public Adoptante() {
        this.id = 4;
        this.nombre = "Adoptante";
        this.permisos.add(new DarEnAdopcion());
        this.permisos.add(new QuieroAdoptar());
        this.permisos.add(new SolicitarAdopcion());
    }
}