package dds.servicios.publicaciones;
import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Adoptante;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.transaccion.QuieroAdoptar;
import dds.domain.seguridad.usuario.Administrador;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublicacionQuieroAdoptarTest extends TestCase {
    Asociacion asoc;
    PublicacionQuieroAdoptar publi;
    HashMap<String, Object> preguntas;
    Persona adoptador;
    Administrador admin;
    List<Mascota> mascotas = new ArrayList<>();

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        RepositorioPersonas.getRepositorio().getPersonas().clear();
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        Notificador noti= new Notificador();
        List<RolPersona> listaRoles = new ArrayList<>();
        listaRoles.add(Adoptante.getAdoptante());
        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);
        admin = new Administrador("asdasd","Passwrod1234+");

        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        adoptador = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        adoptador.setIdPersona("persona1");

        asoc = new Asociacion("asoc1",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);
        Standard standard = new Standard("UsuarioTest","Password1234+",adoptador);
        standard.setAsociacion(asoc);
        admin.setAsociacion(asoc);



        preguntas = new HashMap<String, Object>();
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

        RepositorioUsuarios.getRepositorio().agregarUsuario(standard);
        RepositorioPersonas.getRepositorio().getPersonas().add(adoptador);


    }

    @Test
    public void testeoLasPreguntasBase(){
        Assert.assertEquals(3, asoc.getConfiguraciones().getPreguntas().size());
    }
    @Test
    public void testeoAgregadoDePreguntas(){
        admin.agregarPregunta("Tiene genitales?");
        //asoc.getConfiguraciones().agregarPreguntaNueva("Tiene genitales?");
        Assert.assertEquals(4, asoc.getConfiguraciones().getPreguntas().size());
    }
    @Test
    public void testeoEliminadoDePreguntas(){
        admin.agregarPregunta("Tiene genitales?");
        admin.eliminarPregunta("Tiene genitales?");
        //asoc.getConfiguraciones().agregarPreguntaNueva("Tiene genitales?");
        Assert.assertEquals(3, asoc.getConfiguraciones().getPreguntas().size());
    }
    @Test
    public void testeoPublicacion(){
        List <String> keys = asoc.getConfiguraciones().getPreguntas();
        for (int i=0;i<keys.size();i++) {
            preguntas.put(keys.get(i),"Respuesta x");
        }
        publi= new PublicacionQuieroAdoptar("4",preguntas);
        assertEquals(3,publi.getPreguntas().size());
    }
    @Test
    public void testeoDeseoAdoptar(){
        List <String> keys = asoc.getConfiguraciones().getPreguntas();
        for (int i=0;i<keys.size();i++) {
            preguntas.put(keys.get(i),"Respuesta x");
        }


        adoptador.ejecutarTransaccion(new QuieroAdoptar("persona1",preguntas));
        assertEquals(1,asoc.getPublicador().getPublicacionesQuieroAdoptar().size());


    }
}