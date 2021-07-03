package dds.domain.persona;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.roles.Duenio;
import dds.domain.persona.roles.Rescatista;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.roles.Voluntario;
import dds.domain.persona.transaccion.*;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.*;
import dds.servicios.publicaciones.PublicacionMascota;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaTest {

    Persona persona,personaRescat,personaVoluntario;
    List<Mascota> mascotas = new ArrayList<>();
    Asociacion asoc;
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        RepositorioHogaresDeTransito.getRepositorio().getHogares().clear();


        //CREO ASOC
        asoc = new Asociacion("Asco",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion("ASOC1");
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        //CREO DUENIO

        Duenio duenio = new Duenio();
        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",5,"Pelo largo",new ArrayList<>(),new HashMap<>());
        perro.setIdMascota("perro1");
        Mascota gato = new Mascota(TipoMascota.GATO,"nombreGato","apodoGato",8,"Siames",new ArrayList<>(),new HashMap<>());
        gato.setIdMascota("gato1");
        perro.setEstaPerdida(true);
        mascotas.add(perro);
        mascotas.add(gato);
        Notificador noti= new Notificador();
        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        noti.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        List<RolPersona> listaRoles = new ArrayList<>();
        listaRoles.add(duenio);

        persona = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        persona.setIdPersona("persona1");
        Standard standard = new Standard("UsuarioTest","Password1234+",persona);
        standard.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        RepositorioPersonas.getRepositorio().getPersonas().add(persona);

        //CREO RESCATISTA
        List<RolPersona> listaRoles2 = new ArrayList<>();
        Rescatista rescatista = new Rescatista();
        listaRoles2.add(rescatista);
        personaRescat = new Persona("nrescat","arescat",new ArrayList<>(),listaRoles2,new Notificador());
        personaRescat.setIdPersona("rescat1");
        Standard usuRescatista = new Standard("UsuarioRescatista","Password1234+",personaRescat);
        usuRescatista.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(usuRescatista);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaRescat);


        //CREO VOLUNTARIO
        List<RolPersona> listaRoles3 = new ArrayList<>();
        Voluntario voluntario = new Voluntario();
        listaRoles3.add(voluntario);
        personaVoluntario = new Persona("nvoluntario","avoluntario",new ArrayList<>(),listaRoles3,new Notificador());
        personaVoluntario.setIdPersona("voluntario1");
        Standard usuVoluntario = new Standard("UsuarioVoluntario","Password1234+",personaVoluntario);
        usuVoluntario.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(usuVoluntario);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaVoluntario);

    }

    @Test
    public void testRegistrarMascota(){
        int size = persona.getMascotas().size();
        persona.ejecutarTransaccion(new RegistrarMascota(persona,TipoMascota.PERRO,"nuevoPerro","nuevoPerro",8,"Pelo corto",new ArrayList<>(),new HashMap<>()));
        Assert.assertEquals(size+1,persona.getMascotas().size());
    }

    @Test
    public void testEncontreMascotaPerdidaConChapita(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaConChapita("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado"));

    }

    @Test
    public void testValidarPublicacion(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado");
        publicacionMascota.setIdPublicacion("Publi1");
        asoc.getPublicador().agregarPublicacionPendiente(publicacionMascota);
        personaVoluntario.ejecutarTransaccion(new ValidarPublicacion("Publi1"));
        Assert.assertTrue(asoc.getPublicador().tienePublicacionAprobada("Publi1"));
    }


    //Intento validar publicacion con rescatista
    @Test (expected = TransactionException.class)
    public void testValidarPublicacionErrorPermisos(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado");
        publicacionMascota.setIdPublicacion("Publi1");
        asoc.getPublicador().agregarPublicacionPendiente(publicacionMascota);
        personaRescat.ejecutarTransaccion(new ValidarPublicacion("Publi1"));
    }

    @Test
    public void testRechazarPublicacion(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado");
        publicacionMascota.setIdPublicacion("Publi1");
        asoc.getPublicador().agregarPublicacionPendiente(publicacionMascota);
        personaVoluntario.ejecutarTransaccion(new RechazarPublicacion("Publi1"));
        Assert.assertFalse(asoc.getPublicador().tienePublicacionPendiente("Publi1"));
    }

    //RECHAZAR PUBLICACION QUE NO ESTA PENDIENTE
    @Test (expected = LogicRepoException.class)
    public void testRechazarPublicacionError(){
        personaVoluntario.ejecutarTransaccion(new RechazarPublicacion("Publi1"));
    }

    @Test
    public void testBuscarHogarDeTransito(){
        BuscarHogarDeTransito transaccion = new BuscarHogarDeTransito(-51.622855315759274,-69.21685055962318,500);
        personaRescat.ejecutarTransaccion(transaccion);
        Assert.assertEquals(1,transaccion.getPosiblesHogares().size());
    }
}
