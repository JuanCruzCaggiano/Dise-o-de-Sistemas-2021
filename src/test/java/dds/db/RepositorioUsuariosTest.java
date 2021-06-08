package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.RolPersona;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.avisos.Notificador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class RepositorioUsuariosTest  {
    Persona persona = new Persona("pedro","dorr",new ArrayList<>(),new ArrayList<>(),new Notificador());


    @Before
    public void setUp() throws Exception {
        //Creo persona para probar en tests
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        persona = new Persona("pedro","dorr",new ArrayList<>(),new ArrayList<>(),new Notificador());
        persona.setIdPersona("1");
        Mascota perro = new Mascota("perro");
        perro.setIdMascota("12345perro");
        persona.getMascotas().add(perro);
        RepositorioPersonas.getRepositorio().getPersonas().add(persona);
        Standard standard = new Standard("UsuarioTest","Password1234+",persona);
        Asociacion asoc = new Asociacion("Asco","AsocDir","AsocLoc","AscoProv","AscoPais","AsocCod");
        asoc.setIdAsociacion("ASOC1");
        standard.setAsociacion(asoc);


        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
    }

    @Test
    public void testGetIdUsuarioXPersona(){
        Assert.assertEquals("UsuarioTest",RepositorioUsuarios.getRepositorio().getUserNameXIdPersona("1"));

    }

    @Test (expected = LogicRepoException.class)
    public void testGetIdUsuarioXPersonaError() {

        String id = RepositorioUsuarios.getRepositorio().getUserNameXIdPersona("10");

    }

    @Test
    public void testGetIDAsocXIdPersona() {
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdPersona("1"));

    }
    @Test (expected = LogicRepoException.class)
    public void testGetIDAsocXIdPersonaError(){
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdPersona("10"));

    }

    @Test
    public void testGetIDAsocXIdMascota() {
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota("12345perro"));

    }
    @Test (expected = LogicRepoException.class)
    public void testGetIDAsocXIdMascotaError() {
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota("12349ds"));

    }

}