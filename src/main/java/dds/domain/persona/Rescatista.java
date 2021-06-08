package dds.domain.persona;

import dds.db.RepositorioAsociaciones;
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

    public void encontreMascotaPerdida(float latitud, float longitud, ArrayList<String> listaFotos,String descripcion){

    }


    //CASO CON CHAPITA
    public void encontreMascotaPerdida(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos,String descripcion){
        new PublicacionMascota(idMascota,latitud,longitud,listaFotos,descripcion);
        //RepositorioAsociaciones.getRepositorio().getAsociacionXMascota(idMascota);

        //RepositorioAsociacion repoAsoc = getRepoAsociacion(); //consigue la lista de todas las asociaciones
        //Asociacion asoc = repoAsoc.getAsociacionMasCercana(lat,long);  //agarra la asociación más cercana 	PublicacionMascotaEncontrada publi1 = New PublicacionMascotaEncontrada(lat,long,listaFotos,descripcion);
        //asoc.publicador1.agregarAListaDePublicacionesPendientes(publi1);

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
