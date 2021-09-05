package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.TipoDocumento;
import dds.domain.persona.roles.Duenio;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioUsuariosTest  {
    Persona personaDuenio;
    Standard usuDuenio;
    Asociacion asoc;
    @Before
    public void setUp() throws Exception {
        //CREO ASOC
        asoc = new Asociacion("asoc",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        //Creo persona para probar en tests

        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        AdapterEmail adEmail = new AdapterEmail();
        formasDeNoti.add(adEmail);
        Notificador noti= new Notificador();
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);



        //CREO DUENIO Nuevo
        personaDuenio = new Persona("Matias", "Lanneponders", TipoDocumento.DNI,
                39000401, LocalDate.of(1995, 7, 7),
                "dir","1155892198", "mlyonadi@gmail.com", formasDeNoti);
        personaDuenio.setIdPersona("1");
        Standard usuDuenio = new Standard("UsuarioDuenio","Password1234+",personaDuenio);
        usuDuenio.setAsociacion(asoc);

        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",LocalDate.now().minusYears(5),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        perro.setIdMascota("perro1");
        personaDuenio.getMascotas().add(perro);



        personaDuenio.agregarRol(Duenio.getDuenio());
        RepositorioUsuarios.getRepositorio().agregarUsuario(usuDuenio);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaDuenio);

        Standard standard = new Standard("UsuarioTest","Password1234+",personaDuenio);
        standard.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
    }

    @Test
    public void testGetIdUsuarioXPersona(){
        Assert.assertEquals("UsuarioDuenio",RepositorioUsuarios.getRepositorio().getUserNameXIdPersona("1"));
    }

    @Test (expected = LogicRepoException.class)
    public void testGetIdUsuarioXPersonaError() {
        String userName = RepositorioUsuarios.getRepositorio().getUserNameXIdPersona("10");

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
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota("perro1"));

    }
    @Test (expected = LogicRepoException.class)
    public void testGetIDAsocXIdMascotaError() {
        Assert.assertEquals("ASOC1",RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota("12349ds"));

    }

}