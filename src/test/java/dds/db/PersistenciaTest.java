package dds.db;

import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.TipoDocumento;
import dds.domain.persona.roles.*;
import dds.domain.persona.transaccion.DarEnAdopcion;
import dds.domain.seguridad.usuario.Administrador;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;
import dds.servicios.publicaciones.PublicacionMascota;
import dds.servicios.publicaciones.TipoPublicacion;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.management.DynamicMBean;
import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @After
    public void rollBack() {
        rollbackTransaction();
    }


    @Test
    public void PullInicialDeDatos() throws NoSuchAlgorithmException {

        Asociacion asoc = new Asociacion("Rescate de Patitas CABA",new Ubicacion("Rivadavia 9450",-34.63722034233585, -58.49715981178081));
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Color de Pelo");
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Tamaño");

        Administrador usuarioTest = new Administrador("usuarioTest","Password123+");



        usuarioTest.setAsociacion(asoc);
        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",LocalDate.now().minusYears(5),"Pelo largo",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
        Mascota gato = new Mascota(TipoMascota.GATO,"nombreGato","apodoGato",LocalDate.now().minusYears(8),"Siames",new ArrayList<>(),new HashMap<>(),Sexo.MACHO);
        perro.agregarCaracteristica("Color De Pelo","Negro y Marron");
        perro.agregarCaracteristica("Tamaño","Grande");

        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);

        Persona persona = new Persona("npersona","apersona",TipoDocumento.DNI,39000401,LocalDate.of(1995,07,07),"dire","1165485425","mail@gmail.com",formasDeNoti);
        persona.agregarRol(Duenio.getDuenio());
        Persona persona2 = new Persona("agus","orlando",TipoDocumento.DNI,43031203,LocalDate.of(2000,11,03),"dire","1165485425","mail@gmail.com",formasDeNoti);
        persona2.agregarRol(Duenio.getDuenio());
        persona2.agregarRol(Rescatista.getRescatista());
        persona.getNotificador().agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        persona.getNotificador().agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        persona.getMascotas().add(perro);
        persona.getMascotas().add(gato);
        persona2.getNotificador().agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        asoc.getPublicador().agregarPublicacion(new PublicacionMascota("perro1",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",persona2.getIdPersona(), TipoPublicacion.PENDIENTE));
        asoc.getPublicador().agregarPublicacion(new PublicacionMascota("perro2",(float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",persona2.getIdPersona(), TipoPublicacion.APROBADA));

        HashMap<String, String> preguntas = new HashMap<String, String>();
        preguntas.put(asoc.getConfiguraciones().getPreguntas().get(0),"Negro");
        preguntas.put(asoc.getConfiguraciones().getPreguntas().get(1),"Grande");

        Standard usuarioStandard = new Standard("usuarioStandard","Password123+2",persona);
        usuarioStandard.setAsociacion(asoc);



        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(usuarioStandard);

        EntityManagerHelper.getEntityManager().persist(usuarioTest);

        EntityManagerHelper.getEntityManager().persist(persona2);

        //Prueba de ejecucion de tansaccion Dar en Adopcion por un usuario standard dueño.
        persona.ejecutarTransaccion(new DarEnAdopcion(perro.getIdMascota(),persona.getIdPersona(),preguntas));


        EntityManagerHelper.commit();


    }
}