package dds.domain.seguridad.usuario;

import dds.domain.persona.Persona;

import java.security.NoSuchAlgorithmException;

public class Standard extends Usuario {
    private Persona persona;


    public Standard(String userName, String password,Persona persona) throws NoSuchAlgorithmException {
        super(userName, password);
        this.persona = persona;
    }
}
