package dds.domain.persona;

import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.PublicacionMascota;

import javax.mail.MessagingException;
import java.util.ArrayList;

public class Rescatista implements RolPersona {

    @Override
    public void realizarTransaccion(String algo) {

    }

    public void puencontreMascotaPerdida(float latitud, float longitud, ArrayList<String> listaFotos,String descripcion){

    }
    public void encontreMascotaPerdida(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos,String descripcion){
        new PublicacionMascota(idMascota,latitud,longitud,listaFotos,descripcion);

    }
 /*
    public void encontreMascotaPerdida(Mascota mascota, RepositorioPersonas repo) throws MessagingException { //esto busca al dueño y dentro del repo se encarga de notificar
        Integer identificador = repo.tieneMascota(mascota);
        new
        RepositorioPersonas.getRepositorio().getPersonas().get(identificador).getNotificador().notificar("encontramos a "+mascota.getNombre());
    }
    */

    private void buscarHogarDeTransito(){ //TODO falta implementar

    }
}
