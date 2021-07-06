package dds.servicios.avisos;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class NotificadorSemanal{

    public void notificar(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable enviarRecomendacion = () -> System.out.println("Notificando...");

        //dejamos en 5 segundos para realizar pruebas
        ScheduledFuture<?> result = ses.scheduleAtFixedRate(enviarRecomendacion,0,5,TimeUnit.SECONDS);
        //ScheduledFuture<?> result = ses.scheduleAtFixedRate(task2,0,7,TimeUnit.DAYS);
        //ses.shutdown(); --tener en cuenta que se utiliza para finalizar el periodo...

    }

}
