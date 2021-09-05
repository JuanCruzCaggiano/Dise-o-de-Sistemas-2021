package dds.servicios.avisos;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Adoptante;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.transaccion.DarEnAdopcion;
import dds.domain.persona.transaccion.QuieroAdoptar;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.PublicacionQuieroAdoptar;
import org.junit.Before;
import org.junit.Test;
import dds.servicios.avisos.NotificadorSemanal;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;


public class NotificadorSemanalTest {
    Asociacion asoc;
    PublicacionQuieroAdoptar publi;
    HashMap<String, Object> preguntas,preguntasAdoptante;
    Persona adoptador,duenio;
    Standard standard;
    List<Mascota> mascotas = new ArrayList<>();
    NotificadorSemanal notificadorSemanal;

    PublicacionQuieroAdoptar publi2;
    HashMap<String, Object> preguntas2;
    List<Mascota> mascotas2 = new ArrayList<>();

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        Notificador noti= new Notificador();
        Notificador noti2= new Notificador();
        List<RolPersona> listaRoles = new ArrayList<>();
        List<RolPersona> listaRoles2 = new ArrayList<>();
        listaRoles.add(Adoptante.getAdoptante());
        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);


        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro", LocalDate.now().minusYears(5),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        perro.setIdMascota("perro1");
        mascotas.add(perro);
        duenio = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        duenio.setIdPersona("persona1");

        //ALTA ASOCIACION
        asoc = new Asociacion("asoc1",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);

        //ALTA DE USUARIOS
        standard = new Standard("UsuarioTest","Password1234+",duenio);
        standard.setAsociacion(asoc);

        preguntas = new HashMap<String, Object>();
        //RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        RepositorioPersonas.getRepositorio().getPersonas().add(duenio);

        //Doy de alta al adoptante
        listaRoles2.add(Adoptante.getAdoptante());
        AdapterEmail adEmail2 = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti2 = new ArrayList<>();
        formasDeNoti2.add(adEmail2);
        noti2.agendarContacto("Gabriel", "figueroa", "1155892198", "gabriel.n.figueroa@gmail.com", formasDeNoti2);
        noti2.agendarContacto("Gabriel", "figueroa", "1111111111", "gabriel.n.figueroa@hotmail.com", formasDeNoti2);
        adoptador = new Persona("personaAdoptante","2persona",mascotas,listaRoles2,noti2);
        adoptador.setIdPersona("personaAdoptante");

        Standard standardAdoptante = new Standard("UsuarioTest","Password1234+",adoptador);
        standardAdoptante.setAsociacion(asoc);

        preguntasAdoptante = new HashMap<String, Object>();
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standardAdoptante);
        RepositorioPersonas.getRepositorio().getPersonas().add(adoptador);

        List<String> keys = asoc.getConfiguraciones().getPreguntas();
        for (int i = 0; i < keys.size(); i++) {
            preguntas.put(keys.get(i), "Respuesta"+i);
            preguntasAdoptante.put(keys.get(i),"Respuesta"+i);
        }

        duenio.ejecutarTransaccion(new DarEnAdopcion("perro1", "persona1", preguntas));


        adoptador.ejecutarTransaccion(new QuieroAdoptar("personaAdoptante",preguntasAdoptante));

        for(int l = 0;l<asoc.getPublicador().getPublicacionesQuieroAdoptar().size();l++){
            asoc.getPublicador().getPublicacionesQuieroAdoptar().get(l).setIdPublicacion("PubliAdoptanteNro:"+l);
        }

        for(int m = 0;m<asoc.getPublicador().getEnAdopcion().size();m++){
            asoc.getPublicador().getEnAdopcion().get(m).setIdPublicacion("PubliBuscoDuenioNro::"+m);
        }

    }



    @Test
    public void pruebaEnvioNotificacionSemanal() throws InterruptedException {
        NotificadorSemanal.getNotificadorSemanalHelper().notificar();
        //notificadorSemanal = new NotificadorSemanal();
        //notificadorSemanal.notificar();
        //notificadorSemanal.notificarPublicacionesConCoincidenciaSegun(1,asoc.getIdAsociacion());
        sleep(40000);
        System.out.println(preguntasAdoptante);
        System.out.println(preguntas);
        System.out.println("fin notificacion....");
        //notificadorSemanal.notificarPublicacionesConCoincidenciaSegun(2,asoc.getIdAsociacion());

    }






}
