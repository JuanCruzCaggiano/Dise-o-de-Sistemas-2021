package dds.domain.seguridad.usuario;

import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.TipoDocumento;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.personaException.AssignPersonaException;
import dds.servicios.avisos.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StandardTest{
    Persona persona;

    @Before
    public void setUp() throws Exception {
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        AdapterEmail adEmail = new AdapterEmail();
        formasDeNoti.add(adEmail);
        Notificador noti= new Notificador();
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);

        //Creo persona para probar en tests
        persona = new Persona("Matias", "Lanneponders", TipoDocumento.DNI,
                39000401, LocalDate.of(1995, 7, 7),
                "dir","1155892198", "mlyonadi@gmail.com", formasDeNoti);
    }

    @Test
    public void agregarPersonaDespuesDeCrearUsuarioTest() throws NoSuchAlgorithmException {

        Standard usuarioTest = new Standard("usuarioTest","Password123+");
        usuarioTest.agregarPersona(persona);
        Assert.assertEquals(usuarioTest.getPersona().getNombre(),"Matias");

    }
    @Test
    public void agregarPersonaEnConstructorTest() throws NoSuchAlgorithmException {
        Standard usuarioTest = new Standard("usuarioTest","Password123+",persona);
        Assert.assertEquals(usuarioTest.getPersona().getNombre(),"Matias");
    }
    @Test (expected = AssignPersonaException.class)
    public void agregarPersonaAUsuarioConPersonaExistenteErrorTest() throws NoSuchAlgorithmException {
        Standard usuarioTest = new Standard("usuarioTest","Password123+",persona);
        usuarioTest.agregarPersona(persona);
    }
}