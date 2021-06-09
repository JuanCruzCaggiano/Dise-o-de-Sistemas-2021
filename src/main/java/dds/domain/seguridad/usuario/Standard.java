package dds.domain.seguridad.usuario;

import dds.domain.persona.Persona;
import dds.domain.persona.personaException.AssignPersonaException;

import java.security.NoSuchAlgorithmException;

public class Standard extends Usuario {

    private Persona persona;


    public Standard(String userName, String password,Persona persona) throws NoSuchAlgorithmException {
        super(userName, password);
        this.persona = persona;
    }
    public Standard(String userName, String password) throws NoSuchAlgorithmException {
        super(userName, password);
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
