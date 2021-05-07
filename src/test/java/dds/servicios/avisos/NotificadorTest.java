package dds.servicios.avisos;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class NotificadorTest extends TestCase {
    Notificador noti = new Notificador();
    AdapterSMS testeoSMS = new AdapterSMS();
    AdapterSMS testeoSMS2 = new AdapterSMS();
    AdapterWhatsApp testeoWPP = new AdapterWhatsApp();
    List<AdapterFormaNotificacion> formasDeNoti = new ArrayList();


@Test
    public void testNotificarSMS() {
    formasDeNoti.add(testeoSMS);
    noti.agendarContacto("pedro","dorr","+541155892198","dorrpei@gmail.com",formasDeNoti);
    noti.agendarContacto("pedro","dorr","+541140435092","dorrpei@gmail.com",formasDeNoti);
    noti.notificar("probando multiple mensaje");
    }
@Test
    public void testNotificarWPP() {
        formasDeNoti.add(testeoWPP);
        //noti.agendarContacto("pedro","dorr","+541155892198","dorrpei@gmail.com",formasDeNoti);
        noti.agendarContacto("pedro","dorr","+541140435092","dorrpei@gmail.com",formasDeNoti);
        noti.notificar("probando wpp");
    }
}