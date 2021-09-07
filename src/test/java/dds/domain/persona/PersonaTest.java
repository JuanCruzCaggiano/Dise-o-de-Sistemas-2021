package dds.domain.persona;

import dds.db.*;
import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.roles.*;
import dds.domain.persona.transaccion.*;
import dds.domain.seguridad.usuario.Standard;
import dds.domain.seguridad.usuario.Usuario;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.*;
import dds.servicios.publicaciones.PublicacionMascota;
import dds.servicios.publicaciones.TipoPublicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.metamodel.EmbeddableType;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaTest {

    Persona matias,personaRescat,personaVoluntario,personaDuenio, personaAdoptante;
    List<Mascota> mascotas = new ArrayList<>();
    Mascota perro,gato,firulais;
    Asociacion asoc;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        RepositorioHogaresDeTransito.getRepositorio().getHogares().clear();


        //CREO ASOC
        asoc = new Asociacion("asoc",new Ubicacion("DIR",0,0));
        //asoc.setIdAsociacion(1);

        //RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(asoc);
        EntityManagerHelper.commit();


        firulais = new Mascota(TipoMascota.PERRO,"Firulais","Firu",LocalDate.now().minusYears(2),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        firulais.setEstaPerdida(true);

        //CREO DUENIO
        perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",LocalDate.now().minusYears(5),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        //perro.setIdMascota("perro1");
        gato = new Mascota(TipoMascota.GATO,"nombreGato","apodoGato",LocalDate.now().minusYears(8),"Siames",new ArrayList<>(),new HashMap<>(),Sexo.HEMBRA);
        //gato.setIdMascota("gato1");

        mascotas.add(perro);
        mascotas.add(gato);
        mascotas.add(firulais);
        Notificador noti= new Notificador();
        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        noti.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        List<RolPersona> listaRoles = new ArrayList<>();
        listaRoles.add(Duenio.getDuenio());

        matias = new Persona("Matias","Lanneponders",TipoDocumento.DNI,39000401,LocalDate.of(1995,07,07),"Byron 35","1165485425","mail@gmail.com",formasDeNoti);
        matias.setMascotas(mascotas);
        matias.setListaRoles(listaRoles);
        Standard standard = new Standard("matilanne","Password1234+", matias);
        standard.setAsociacion(asoc);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(standard);
        EntityManagerHelper.commit();
        // persistido RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        // persistido RepositorioPersonas.getRepositorio().getPersonas().add(persona);

        //CREO RESCATISTA
        List<RolPersona> listaRoles2 = new ArrayList<>();
        listaRoles2.add(Rescatista.getRescatista());
        Notificador noti2= new Notificador();
        AdapterEmail adEmail2 = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti2 = new ArrayList<>();
        formasDeNoti2.add(adEmail2);
        noti2.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti2);
        noti2.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti2);
        personaRescat = new Persona("nrescat","arescat",new ArrayList<>(),listaRoles2,noti2);
        //personaRescat.setIdPersona("rescat1");
        Standard usuRescatista = new Standard("UsuarioRescatista","Password1234+",personaRescat);
        usuRescatista.setAsociacion(asoc);

        // persistido //RepositorioUsuarios.getRepositorio().agregarUsuario(usuRescatista);
        // persistido //RepositorioPersonas.getRepositorio().getPersonas().add(personaRescat);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(usuRescatista);
        EntityManagerHelper.commit();

        //CREO VOLUNTARIO
        List<RolPersona> listaRoles3 = new ArrayList<>();
        listaRoles3.add(Voluntario.getVoluntario());
        Notificador noti3= new Notificador();
        AdapterEmail adEmail3 = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti3 = new ArrayList<>();
        formasDeNoti3.add(adEmail3);
        noti3.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti3);
        noti3.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti3);
        personaVoluntario = new Persona("nvoluntario","avoluntario",new ArrayList<>(),listaRoles3,noti3);
        //personaVoluntario.setIdPersona("voluntario1");
        Standard usuVoluntario = new Standard("UsuarioVoluntario","Password1234+",personaVoluntario);
        usuVoluntario.setAsociacion(asoc);

        //RepositorioUsuarios.getRepositorio().agregarUsuario(usuVoluntario);
        //RepositorioPersonas.getRepositorio().getPersonas().add(personaVoluntario);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(usuVoluntario);
        EntityManagerHelper.commit();

        //CREO DUENIO Nuevo
        personaDuenio = new Persona("Matias", "Lanneponders",TipoDocumento.DNI,
                                            39000401,LocalDate.of(1995, 7, 7),
                                            "dir","1155892198", "mlyonadi@gmail.com", formasDeNoti);
        //personaDuenio.setIdPersona("personaDuenio");
        Standard usuDuenio = new Standard("UsuarioDuenio","Password1234+",personaDuenio);
        usuDuenio.setAsociacion(asoc);
        personaDuenio.agregarRol(Duenio.getDuenio());


        // persistido //RepositorioUsuarios.getRepositorio().agregarUsuario(usuDuenio);
        // persistido //RepositorioPersonas.getRepositorio().getPersonas().add(personaDuenio);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(usuDuenio);
        EntityManagerHelper.commit();

        // CREO ADOPTANTE
        personaAdoptante = new Persona("Agustin", "Orlando",TipoDocumento.DNI,
                4303123,LocalDate.of(2000, 11, 3),
                "dir","1157383400", "orlandoagustin00@gmail.com", formasDeNoti);
        //personaAdoptante.setIdPersona("personaAdoptante");
        Standard usuAdoptante = new Standard("UsuarioAdoptante","Password1234+",personaDuenio);
        usuAdoptante.setAsociacion(asoc);
        personaAdoptante.agregarRol(Adoptante.getAdoptante());
        // persistido //RepositorioUsuarios.getRepositorio().agregarUsuario(usuAdoptante);
        // persistido //RepositorioPersonas.getRepositorio().getPersonas().add(personaAdoptante);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.entityManager().persist(usuAdoptante);
        EntityManagerHelper.commit();
        }

    @Test
    public void testRegistrarMascota(){
        int size = matias.getMascotas().size();
        matias.ejecutarTransaccion(new RegistrarMascota(matias,TipoMascota.PERRO,"nuevoPerro","nuevoPerro",LocalDate.now().minusYears(8),"Pelo corto",new ArrayList<>(),new HashMap<>(),Sexo.MACHO));
        Assert.assertEquals(size+1, matias.getMascotas().size());
    }

    @Test
    public void testEncontreMascotaPerdidaConChapita(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaConChapita(perro.getIdMascota(),(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",personaRescat.getIdPersona()));

    }
    @Test
    public void testEncontreMascotaPerdidaSinChapita(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",personaRescat.getIdPersona()));
        Assert.assertEquals(1,asoc.getPublicador().getPublicacionesPendientes().size());
    }

    @Test
    public void testEncontreMiMascota(){
        personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",personaRescat.getIdPersona()));
        String jql = "Select p.id from PublicacionMascota p where p.idRescatista = :idRescatista";
        String idPublicacion = (String) EntityManagerHelper.getEntityManager().createQuery(jql).
                setParameter("idRescatista",personaRescat.getIdPersona()).getResultList().get(0);
        personaVoluntario.ejecutarTransaccion(new ValidarPublicacion(idPublicacion));
        personaDuenio.ejecutarTransaccion(new EncontreMiMascota(idPublicacion,asoc.getIdAsociacion(),personaDuenio.getIdPersona()));
    }

    @Test
    public void testSolicitarAdopcion(){
        personaDuenio.ejecutarTransaccion(new DarEnAdopcion("perro1","personaDuenio",new HashMap <String, String> ()));
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
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",personaRescat.getIdPersona(), TipoPublicacion.PENDIENTE);
        asoc.getPublicador().agregarPublicacion(publicacionMascota);
        personaRescat.ejecutarTransaccion(new ValidarPublicacion(publicacionMascota.getIdPublicacion()));
    }

    @Test
    public void testRechazarPublicacion(){
        PublicacionMascota publicacionMascota = new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado","rescat1",TipoPublicacion.PENDIENTE);
        asoc.getPublicador().agregarPublicacion(publicacionMascota);
        personaVoluntario.ejecutarTransaccion(new RechazarPublicacion(publicacionMascota.getIdPublicacion()));
        Assert.assertFalse(asoc.getPublicador().tienePublicacionPendiente(publicacionMascota.getIdPublicacion()));
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
