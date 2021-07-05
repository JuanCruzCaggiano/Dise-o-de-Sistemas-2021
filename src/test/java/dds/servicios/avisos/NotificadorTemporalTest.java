package dds.servicios.avisos;

import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class NotificadorTemporalTest {
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    NotificadorTemporal notificador;
    Runnable task2 = () -> System.out.println("Running task66...");
    Timer t = new Timer();

    @Test
    public void pruebaTest() throws InterruptedException {
        notificador = new NotificadorTemporal();
        notificador.task1();


        //run this task after 5 seconds, nonblock for task3
        //ses.schedule(task2, 5, TimeUnit.SECONDS);
        ScheduledFuture<?> result = ses.scheduleAtFixedRate(task2,0,5,TimeUnit.SECONDS);
        Thread.sleep(20000);
        notificador.task3();
        ses.shutdown();
    }






}
