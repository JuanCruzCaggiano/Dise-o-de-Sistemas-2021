package dds.servicios.avisos;

import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;
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


    public void notificar(int cantMinina,String idAsoc){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        //Runnable enviarRecomendacion = () -> System.out.println("Notificando...");
        Runnable enviarRecomendacion = notificarPublicacionesConCoincidenciaSegun(cantMinina,idAsoc);

        //dejamos en 5 segundos para realizar pruebas
        ScheduledFuture<?> result = ses.scheduleAtFixedRate(enviarRecomendacion,0,5,TimeUnit.SECONDS);
        //ScheduledFuture<?> result = ses.scheduleAtFixedRate(task2,0,7,TimeUnit.DAYS);
        //ses.shutdown(); --tener en cuenta que se utiliza para finalizar el periodo...

    }

    public Runnable notificarPublicacionesConCoincidenciaSegun(int coincidenciasMinima, String idAsociacion) {
        preferencias = new PreferenciasDeAdopcion();
        List<PublicacionQuieroAdoptar> publicacionQuieroAdoptar= new ArrayList<>();
        List<PublicacionAdopcion> publicacionAdopcion = new ArrayList<>();
        List<PublicacionAdopcion> listaAEnviaraPosibleAdoptante = new ArrayList<>();

        publicacionQuieroAdoptar = preferencias.obtenerPublicacionesAdoptantesSegunAsociacion(idAsociacion);
        publicacionAdopcion = preferencias.obtenerPublicacionesEnAdopcionSegunAsociacion(idAsociacion);

        for (int i=0;i<publicacionQuieroAdoptar.size();i++){
            listaAEnviaraPosibleAdoptante = preferencias.obtenerPublicacionesConCoincidenciaSegunAdoptante(coincidenciasMinima,publicacionQuieroAdoptar.get(i),publicacionAdopcion);
            if (!listaAEnviaraPosibleAdoptante.isEmpty()){
                notificar(publicacionQuieroAdoptar.get(i).getIdAdoptante(),listaAEnviaraPosibleAdoptante);
            }
            ;
        }

        return null;
    }

    public void notificar(String idPersona,List<PublicacionAdopcion> listaAEnviaraPosibleAdoptante)  {
        Persona adoptante =  RepositorioPersonas.getRepositorio().getPersona(idPersona);
        Persona duenio;
        Mascota mascota;
        String link = "";//TODO Crear formula en un singleton servicio que genere el link que te lleve a la publicacion de la mascota encontrada.

        String mensaje;
        for(int k=0;k<listaAEnviaraPosibleAdoptante.size();k++){
            //Recupero el duenio
            duenio = RepositorioPersonas.getRepositorio().getPersona(idPersona);
            //Recupero la mascota
            mascota = duenio.getMascota(listaAEnviaraPosibleAdoptante.get(k).getIdMascota());
            mensaje = "Encontramos esta pulicaciones de Mascota: "+ mascota.getNombre() + " para mas informacion ingresa al siguiente link de publicacion!: "+listaAEnviaraPosibleAdoptante.get(k).getIdPublicacion()+link;

            for (int i=0;i<adoptante.getNotificador().getSuscriptores().size();i++){
                List<AdapterFormaNotificacion> formas = adoptante.getNotificador().getSuscriptores().get(i).getFormasNotificacion();
                for (int j=0;j<formas.size();j++) {
                    formas.get(j).notificar(mensaje,adoptante.getNotificador().getSuscriptores().get(i)); //aca paso el suscriptor
                }
            }
        }
    }


}

