package dds.servicios.avisos;

import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Duenio;
import dds.domain.persona.roles.RolPersona;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class NotificadorTest extends TestCase {
    Notificador noti= new Notificador();
    AdapterSMS testeoSMS = new AdapterSMS();
    AdapterWhatsApp testeoWPP = new AdapterWhatsApp();
    AdapterEmail testeoEmail = new AdapterEmail();
    List<AdapterFormaNotificacion> formasDeNoti = new ArrayList();


    //CREO DUEÑO
    @Before
    public void setUp() {
        Duenio duenio = new Duenio();
        Mascota perro = new Mascota(TipoMascota.PERRO, "nombrePerro", "apodoPerro", 5, "Pelo largo", new ArrayList<>(), new HashMap<>());
        perro.setIdMascota("perro1");
        Mascota gato = new Mascota(TipoMascota.GATO, "nombreGato", "apodoGato", 8, "Siames", new ArrayList<>(), new HashMap <>());
        gato.setIdMascota("gato1");
        perro.setEstaPerdida(true);
        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(perro);
        mascotas.add(gato);

        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList();
        formasDeNoti.add(testeoEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        noti.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        List<RolPersona> listaRoles = new ArrayList<>();
        listaRoles.add(duenio);
        Persona persona = new Persona("npersona","apersona",mascotas,listaRoles,noti);
        persona.setIdPersona("persona1");
        RepositorioPersonas.getRepositorio().getPersonas().add(persona);
    }

//COMENTO PARA NO COMER CREDITO EN TWILO
/*
    @Test
    public void testNotificarSMS() throws MessagingException {
        formasDeNoti.add(testeoSMS);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        noti.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        noti.notificar("probando multiple mensaje");
    }
    @Test
    public void testNotificarWPP() throws MessagingException {
        formasDeNoti.add(testeoWPP);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);

        noti.notificar("probando wpp");
    }
*/


    @Test
    public void testNotificarEmail() throws MessagingException {
        noti.notificar("perro1");
    }
}