package dds.servicios.helpers;

import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.Contacto;

public class MyRunnable implements Runnable {
    private String mensaje;
    private Contacto contacto;
    public MyRunnable(String mensaje2,Contacto contacto2) {
        this.mensaje = mensaje2;
        this.contacto = contacto2;
    }

    public void run() {
        mensaje = mensaje.replaceAll("\\n","<br/>");
        AdapterEmail.sendAsHtml(contacto.getEmail(),
                "Rescate de patitas",
                "<h2>Rescate de Patitas</h2><p>"+mensaje+"</p>");
    }
}

