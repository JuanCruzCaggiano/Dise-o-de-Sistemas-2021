package dds.servicios.publicaciones;

import dds.servicios.publicaciones.publicacionesException.ErrorPubliException;

import java.util.ArrayList;
import java.util.List;

public class Publicador {

    private List<PublicacionMascota> publicacionesAprobadas= new ArrayList<>();
    private List<PublicacionMascota> publicacionesPendientes= new ArrayList<>();
    private List<PublicacionMascota> publicacionesPrivadas= new ArrayList<>();
    private List<PublicacionQuieroAdoptar> publicacionesQuieroAdoptar= new ArrayList<>();


    public Publicador(){}

    public void aprobarPublicacion (PublicacionMascota publi) {  //aprueba publi pendiente y la pasa a aprobada
        //publicacionesPendientes.contains(publi);
        if (publicacionesPendientes.stream().anyMatch(p -> p.idPublicacion.equals(publi.idPublicacion))){
            eliminarPublicacionPendiente(publi);
            agregarPublicacionAprobada(publi);
        }else{
            throw new ErrorPubliException("Dicha publicacion ya fue aprobada");
        }

    }
    public void rechazarPublicacion (PublicacionMascota publi) {  //aprueba publi pendiente y la pasa a aprobada
        //publicacionesPendientes.contains(publi);
        if (publicacionesPendientes.stream().anyMatch(p -> p.idPublicacion.equals(publi.idPublicacion))){
            eliminarPublicacionPendiente(publi);
        }else{
            throw new ErrorPubliException("Dicha publicacion no se encuentra en la lista de pendientes");
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
    public void agregarPublicacionAprobada (PublicacionMascota publi){ publicacionesAprobadas.add(publi);}

    public boolean tienePublicacionPendiente(String idPublicacion) {
        return this.publicacionesPendientes.stream().anyMatch(p -> p.idPublicacion.equals(idPublicacion));
    }
    public boolean tienePublicacionAprobada(String idPublicacion) {
        return this.publicacionesAprobadas.stream().anyMatch(p -> p.idPublicacion.equals(idPublicacion));
    }
    public boolean tienePublicacionPrivada(String idPublicacion) {
        return this.publicacionesPrivadas.stream().anyMatch(p -> p.idPublicacion.equals(idPublicacion));
    }

    public List<PublicacionMascota> getPublicacionesAprobadas() {
        return publicacionesAprobadas;
    }

    public List<PublicacionMascota> getPublicacionesPendientes() {
        return publicacionesPendientes;
    }

    public List<PublicacionMascota> getPublicacionesPrivadas() {
        return publicacionesPrivadas;
    }

    public PublicacionMascota getPendienteXId(String id){
        return this.publicacionesPendientes.stream().filter(p-> p.getIdPublicacion().equals(id)).findFirst().orElse(null);
    }
    public PublicacionMascota getAprobadaXId(String id){
        return this.publicacionesAprobadas.stream().filter(p-> p.getIdPublicacion().equals(id)).findFirst().orElse(null);
    }
}
