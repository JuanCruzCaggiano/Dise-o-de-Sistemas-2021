package dds.domain.seguridad.usuario;

import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.RolPersona;
import dds.domain.persona.personaException.AssignPersonaException;
import dds.domain.seguridad.usuario.usuarioException.WrongLoginException;
import dds.servicios.avisos.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class StandardTest{
    Persona persona;

    @Before
    public void setUp() throws Exception {
        //Creo persona para probar en tests
        persona = new Persona("pedro","dorr",new ArrayList<Mascota>(),new ArrayList<RolPersona>(),new Notificador());
    }

    @Test
    public void agregarPersonaDespuesDeCrearUsuarioTest() throws NoSuchAlgorithmException {

        Standard usuarioTest = new Standard("usuarioTest","Password123+");
        usuarioTest.agregarPersona(persona);
        Assert.assertEquals(usuarioTest.getPersona().getNombre(),"pedro");

    }
    @Test
    public void agregarPersonaEnConstructorTest() throws NoSuchAlgorithmException {
        Standard usuarioTest = new Standard("usuarioTest","Password123+",persona);
        Assert.assertEquals(usuarioTest.getPersona().getNombre(),"pedro");
    }
    @Test (expected = AssignPersonaException.class)
    public void agregarPersonaAUsuarioConPersonaExistenteErrorTest() throws NoSuchAlgorithmException {
        Standard usuarioTest = new Standard("usuarioTest","Password123+",persona);
        usuarioTest.agregarPersona(persona);
    }
}