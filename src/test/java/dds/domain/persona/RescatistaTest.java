package dds.domain.persona;

import dds.domain.mascota.Mascota;
import dds.servicios.avisos.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class RescatistaTest extends TestCase { //TODO no test found???

    RepositorioPersonas repoPersonas = new RepositorioPersonas();
    Notificador noti = new Notificador();
    Notificador noti2 = new Notificador();
    AdapterWhatsApp a = new AdapterWhatsApp();
    AdapterSMS b = new AdapterSMS();
    AdapterEmail c = new AdapterEmail();
    List<AdapterFormaNotificacion> listaAdapters = new ArrayList<>();
    List<Mascota> listaMascotas= new ArrayList<>();
    List<RolPersona> listaRoles = new ArrayList<>();
    List<Mascota> listaMascotas2= new ArrayList<>();
    List<RolPersona> listaRoles2 = new ArrayList<>();
    Mascota lola = new Mascota("lola");
    Persona persona = new Persona("pedro","dorr",listaMascotas,listaRoles,noti);
    Persona rescatista = new Persona("rescatista","resss",listaMascotas2,listaRoles2,noti2);
    Rescatista rescate = new Rescatista();


    @Test
    public void validarMensajeRescatista() throws MessagingException {

        listaAdapters.add(a);
        listaAdapters.add(b);
        listaAdapters.add(c);
        listaRoles2.add(rescate);
        persona.getMascotas().add(lola);
        persona.getNotificador().agendarContacto("pedro","dorr","1140435092","dorrpei@gmail.com",listaAdapters);
        repoPersonas.agregarPersona(persona);
        repoPersonas.agregarPersona(rescatista);
        rescate.encontreMascotaPerdida(lola,repoPersonas); //creo que hay que reveer el tema de los roles y que haria el realizartransacc



    }
}