package dds.domain.persona;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.roles.*;
import dds.domain.persona.transaccion.*;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.*;
import dds.servicios.publicaciones.PublicacionMascota;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaTest {

    Persona persona,personaRescat,personaVoluntario,personaDuenio, personaAdoptante;
    List<Mascota> mascotas = new ArrayList<>();
    Asociacion asoc;
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        RepositorioHogaresDeTransito.getRepositorio().getHogares().clear();


        //CREO ASOC
        asoc = new Asociacion("asoc",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        //CREO DUENIO


        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",LocalDate.now().minusYears(5),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        perro.setIdMascota("perro1");
        Mascota gato = new Mascota(TipoMascota.GATO,"nombreGato","apodoGato",LocalDate.now().minusYears(8),"Siames",new ArrayList<>(),new HashMap<>(),Sexo.HEMBRA);
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
        listaRoles.add(Duenio.getDuenio());

        persona = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        persona.setIdPersona("persona1");
        Standard standard = new Standard("UsuarioTest","Password1234+",persona);
        standard.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        RepositorioPersonas.getRepositorio().getPersonas().add(persona);

        //CREO RESCATISTA
        List<RolPersona> listaRoles2 = new ArrayList<>();
        listaRoles2.add(Rescatista.getRescatista());
        personaRescat = new Persona("nrescat","arescat",new ArrayList<>(),listaRoles2,noti);
        personaRescat.setIdPersona("rescat1");
        Standard usuRescatista = new Standard("UsuarioRescatista","Password1234+",personaRescat);
        usuRescatista.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(usuRescatista);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaRescat);


        //CREO VOLUNTARIO
        List<RolPersona> listaRoles3 = new ArrayList<>();
        listaRoles3.add(Voluntario.getVoluntario());
        personaVoluntario = new Persona("nvoluntario","avoluntario",new ArrayList<>(),listaRoles3,noti);
        personaVoluntario.setIdPersona("voluntario1");
        Standard usuVoluntario = new Standard("UsuarioVoluntario","Password1234+",personaVoluntario);
        usuVoluntario.setAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(usuVoluntario);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaVoluntario);

        //CREO DUENIO Nuevo
        personaDuenio = new Persona("Matias", "Lanneponders",TipoDocumento.DNI,
                                            39000401,LocalDate.of(1995, 7, 7),
                                            "dir","1155892198", "mlyonadi@gmail.com", formasDeNoti);
        personaDuenio.setIdPersona("personaDuenio");
        Standard usuDuenio = new Standard("UsuarioDuenio","Password1234+",personaDuenio);
        usuDuenio.setAsociacion(asoc);
        personaDuenio.agregarRol(Duenio.getDuenio());
        RepositorioUsuarios.getRepositorio().agregarUsuario(usuDuenio);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaDuenio);

        // CREO ADOPTANTE
        personaAdoptante = new Persona("Agustin", "Orlando",TipoDocumento.DNI,
                4303123,LocalDate.of(2000, 11, 3),
                "dir","1157383400", "orlandoagustin00@gmail.com", formasDeNoti);
        personaAdoptante.setIdPersona("personaAdoptante");
        Standard usuAdoptante = new Standard("UsuarioAdoptante","Password1234+",personaDuenio);
        usuAdoptante.setAsociacion(asoc);
        personaAdoptante.agregarRol(Adoptante.getAdoptante());
        RepositorioUsuarios.getRepositorio().agregarUsuario(usuAdoptante);
        RepositorioPersonas.getRepositorio().getPersonas().add(personaAdoptante);
        }

    @Test
    public void testRegistrarMascota(){
        int size = persona.getMascotas().size();
        persona.ejecutarTransaccion(new RegistrarMascota(persona,TipoMascota.PERRO,"nuevoPerro","nuevoPerro",LocalDate.now().minusYears(8),"Pelo corto",new ArrayList<>(),new HashMap<>(),Sexo.MACHO));
        Assert.assertEquals(size+1,persona.getMascotas().size());
    }

    @Test
    public void testEncontreMascotaPerdidaConChapita(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaConChapita("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","recat1"));

    }
    @Test
    public void testEncontreMascotaPerdidaSinChapita(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1"));
        Assert.assertEquals(1,asoc.getPublicador().getPublicacionesPendientes().size());
    }

    @Test
    public void testEncontreMiMascota(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1"));
        RepositorioAsociaciones.getRepositorio().getAsociacion(1).getPublicador().getPublicacionesPendientes().get(0).setIdPublicacion("Publi1");
        personaVoluntario.ejecutarTransaccion(new ValidarPublicacion("Publi1"));
        personaDuenio.ejecutarTransaccion(new EncontreMiMascota("Publi1",1,"personaDuenio"));
    }

    @Test
    public void testSolicitarAdopcion(){
        personaDuenio.ejecutarTransaccion(new DarEnAdopcion("perro1","personaDuenio",new HashMap <String, Object> ()));
        RepositorioAsociaciones.getRepositorio().getAsociacion(1).getPublicador().getEnAdopcion().get(0).setIdPublicacion("Publi1");
        personaAdoptante.ejecutarTransaccion(new SolicitarAdopcion("Publi1",1,"personaAdoptante"));
    }

    @Test
    public void testValidarPublicacion(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1"));
        RepositorioAsociaciones.getRepositorio().getAsociacion(1).getPublicador().getPublicacionesPendientes().get(0).setIdPublicacion("Publi1");
        Assert.assertEquals(1,asoc.getPublicador().getPublicacionesPendientes().size());
        personaVoluntario.ejecutarTransaccion(new ValidarPublicacion("Publi1"));
        Assert.assertTrue(asoc.getPublicador().tienePublicacionAprobada("Publi1"));
        Assert.assertEquals(1,asoc.getPublicador().getPublicacionesAprobadas().size());
    }


    //Intento validar publicacion con rescatista
    @Test (expected = TransactionException.class)
    public void testValidarPublicacionErrorPermisos(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1");
        publicacionMascota.setIdPublicacion("Publi1");
        asoc.getPublicador().agregarPublicacionPendiente(publicacionMascota);
        personaRescat.ejecutarTransaccion(new ValidarPublicacion("Publi1"));
    }

    @Test
    public void testRechazarPublicacion(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1");
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
