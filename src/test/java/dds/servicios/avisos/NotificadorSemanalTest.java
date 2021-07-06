package dds.servicios.avisos;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Adoptante;
import dds.domain.persona.roles.RolPersona;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.PublicacionQuieroAdoptar;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class NotificadorSemanalTest {
    Asociacion asoc;
    PublicacionQuieroAdoptar publi;
    HashMap<String, Object> preguntas;
    Persona adoptador;
    List<Mascota> mascotas = new ArrayList<>();
    NotificadorSemanal notificadorSemanal;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        Notificador noti= new Notificador();
        Adoptante adoptante = new Adoptante();
        List<RolPersona> listaRoles = new ArrayList<>();
        listaRoles.add(adoptante);
        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);

        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",5,"Pelo largo",new ArrayList<>(),new HashMap<>());
        perro.setIdMascota("perro1");
        mascotas.add(perro);
        duenio = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        duenio.setIdPersona("persona1");

        asoc = new Asociacion("asoc1",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion("ASOC1");
        Standard standard = new Standard("UsuarioTest","Password1234+",duenio);
        standard.setAsociacion(asoc);

        preguntas = new HashMap<String, Object>();
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        RepositorioPersonas.getRepositorio().getPersonas().add(duenio);
    }



    @Test
    public void pruebaTest() throws InterruptedException {
        notificadorSemanal = new NotificadorSemanal();
        notificadorSemanal.notificar();
        sleep(20000);
        System.out.println("fin notificacion....");

    }






}
