package dds.servicios.publicaciones;

import dds.servicios.publicaciones.publicacionesException.ErrorPubliException;

import java.util.ArrayList;
import java.util.List;

public class Publicador {

    private List<PublicacionMascota> publicacionesAprobadas= new ArrayList<>();
    private List<PublicacionMascota> publicacionesPendientes= new ArrayList<>();
    private List<PublicacionMascota> publicacionesPrivadas= new ArrayList<>();



    public void aprobarPublicacion (PublicacionMascota publi) {  //aprueba publi pendiente y la pasa a aprobada
        //publicacionesPendientes.contains(publi);
        if (publicacionesPendientes.stream().anyMatch(p -> p.idPubli == publi.idPubli )){
            eliminarPublicacionPendiente(publi);
            agregarPublicacionAprobada(publi);
        }else{
            throw new ErrorPubliException("Dicha publicacion ya fue aprobada");
        }

    }
    public void agregarPublicacionPrivada (PublicacionMascota publi) {   // metodo para mascota con chapita
        publicacionesPrivadas.add(publi);
    }
    public void agregarPublicacionPendiente (PublicacionMascota publi) { // metodo que usa el voluntario
        publicacionesPendientes.add(publi);
    }
    private void eliminarPublicacionPendiente (PublicacionMascota publi){
        publicacionesPendientes.remove(publi);
    }
    private void agregarPublicacionAprobada (PublicacionMascota publi){
        publicacionesAprobadas.add(publi);
    }
}
