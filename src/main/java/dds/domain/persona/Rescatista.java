package dds.domain.persona;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.mascota.Mascota;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.PublicacionMascota;

import javax.mail.MessagingException;
import java.util.ArrayList;

public class Rescatista implements RolPersona {

    @Override
    public void realizarTransaccion(String algo) {

    }

    public void encontreMascotaPerdida(float latitud, float longitud, ArrayList<String> listaFotos,String descripcion){

    }


    //CASO CON CHAPITA
    public void encontreMascotaPerdida(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos,String descripcion) throws MessagingException {
        PublicacionMascota publi = new PublicacionMascota(idMascota,latitud,longitud,listaFotos,descripcion);
        String idAsoc = RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota(idMascota);
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);
        asoc.getPublicador().agregarPublicacionPrivada(publi);
        Persona dueño = RepositorioPersonas.getRepositorio().getPersona(RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota));
        dueño.getNotificador().notificar(idMascota);

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
