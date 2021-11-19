package dds.domain.entities.seguridad.usuario;

import dds.db.EntityManagerHelper;
import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.persona.personaException.AssignPersonaException;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("S")
public class Standard extends Usuario {

    @OneToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "persona_id")
    private Persona persona;
    public Standard(){}
    public Standard(String userName, String password, Persona persona, Asociacion asociacion) throws NoSuchAlgorithmException {
        super(userName, password, asociacion);
        this.persona = persona;
    }


    public void agregarPersona(Persona persona){
        if(this.persona == null) {
            this.persona = persona;
        }else
        {
            throw new AssignPersonaException("El usuario ya tiene una persona asignada");
        }
    }

    @Override
    public Persona getPersona() {
        return persona;
    }
}
