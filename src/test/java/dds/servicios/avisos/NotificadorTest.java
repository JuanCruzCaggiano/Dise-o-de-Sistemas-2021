package dds.servicios.avisos;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.List;
import java.util.ArrayList;

public class NotificadorTest extends TestCase {
    Notificador noti= new Notificador();;
    AdapterSMS testeoSMS = new AdapterSMS();
    AdapterWhatsApp testeoWPP = new AdapterWhatsApp();
    AdapterEmail testeoEmail = new AdapterEmail();
    List<AdapterFormaNotificacion> formasDeNoti = new ArrayList();

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
        noti.agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        noti.notificar("probando wpp");
    }
*/

    @Test
    public void testNotificarEmail() throws MessagingException {
        formasDeNoti.add(testeoEmail);
        noti.agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        noti.agendarContacto("pedro", "dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        noti.notificar("probando email");
    }
}