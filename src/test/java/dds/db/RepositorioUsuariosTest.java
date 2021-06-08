package dds.db;

import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.RolPersona;
import dds.domain.seguridad.usuario.Standard;
import dds.domain.seguridad.usuario.Usuario;
import dds.servicios.avisos.Notificador;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class RepositorioUsuariosTest  {
    Persona persona = new Persona("pedro","dorr",new ArrayList<Mascota>(),new ArrayList<RolPersona>(),new Notificador());


    @Before
    public void setUp() throws Exception {
        //Creo persona para probar en tests
        persona = new Persona("pedro","dorr",new ArrayList<Mascota>(),new ArrayList<RolPersona>(),new Notificador());
    }

    @Test
    public void testGetIdUsuarioXPersona() throws NoSuchAlgorithmException {
        RepositorioUsuarios.getRepositorio();
        persona.setIdPersona("1");
        Standard standard = new Standard("UsuarioTest","Password1234+",persona);
        standard.setIdUsuario("123445");
        RepositorioUsuarios.getRepositorio().agregarUsuario(standard );

        Assert.assertEquals("123445",RepositorioUsuarios.getRepositorio().getIdUsuarioXPersona("1"));


    }
}