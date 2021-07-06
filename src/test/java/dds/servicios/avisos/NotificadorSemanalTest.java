package dds.servicios.avisos;

import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Adoptante;
import dds.domain.persona.roles.RolPersona;
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



    @Test
    public void pruebaTest() throws InterruptedException {
        notificadorSemanal = new NotificadorSemanal();
        notificadorSemanal.notificar();
        sleep(20000);
        System.out.println("fin notificacion....");

    }






}
