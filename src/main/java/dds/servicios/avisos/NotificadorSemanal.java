package dds.servicios.avisos;

import dds.db.RepositorioPersonas;
import dds.servicios.publicaciones.PublicacionAdopcion;
import dds.servicios.publicaciones.PublicacionQuieroAdoptar;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class NotificadorSemanal{
    PreferenciasDeAdopcion preferencias;
    RepositorioPersonas repositorioPersonas;
    private List<Contacto> suscriptores = new ArrayList<>();
    private AdapterFormaNotificacion adapter;


    public void notificar(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable enviarRecomendacion = () -> System.out.println("Notificando...");

        //dejamos en 5 segundos para realizar pruebas
        ScheduledFuture<?> result = ses.scheduleAtFixedRate(enviarRecomendacion,0,5,TimeUnit.SECONDS);
        //ScheduledFuture<?> result = ses.scheduleAtFixedRate(task2,0,7,TimeUnit.DAYS);
        //ses.shutdown(); --tener en cuenta que se utiliza para finalizar el periodo...

    }

    public void notificarPublicacionesConCoincidenciaSegun(int coincidenciasMinima, String idAsociacion) {
        preferencias = new PreferenciasDeAdopcion();
        List<PublicacionQuieroAdoptar> publicacionQuieroAdoptar= new ArrayList<>();
        List<PublicacionAdopcion> publicacionAdopcion = new ArrayList<>();
        List<PublicacionAdopcion> listaAEnviaraPosibleAdoptante = new ArrayList<>();

        publicacionQuieroAdoptar = preferencias.obtenerPublicacionesAdoptantesSegunAsociacion(idAsociacion);
        publicacionAdopcion = preferencias.obtenerPublicacionesEnAdopcionSegunAsociacion(idAsociacion);

        for (int i=0;i<publicacionQuieroAdoptar.size();i++){
            listaAEnviaraPosibleAdoptante = preferencias.obtenerPublicacionesConCoincidenciaSegunAdoptante(coincidenciasMinima,publicacionQuieroAdoptar.get(i),publicacionAdopcion);
        }

    }


}

