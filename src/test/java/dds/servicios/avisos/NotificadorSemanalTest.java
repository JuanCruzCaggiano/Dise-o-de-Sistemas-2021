package dds.servicios.avisos;

import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class NotificadorSemanalTest {
    NotificadorSemanal notificador;

    @Test
    public void pruebaTest() throws InterruptedException {
        notificador = new NotificadorSemanal();
        notificador.notificar();
        Thread.sleep(20000);
        System.out.println("fin notificacion....");

    }






}
