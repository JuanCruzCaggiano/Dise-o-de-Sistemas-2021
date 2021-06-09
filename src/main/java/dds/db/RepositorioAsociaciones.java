package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.seguridad.usuario.Usuario;
import dds.servicios.publicaciones.Publicador;

import java.util.List;
import java.util.ArrayList;


public class RepositorioAsociaciones {

    private List<Asociacion> asociaciones = new ArrayList<>();

    private static RepositorioAsociaciones repositorioAsociaciones = new RepositorioAsociaciones() ;

    public static RepositorioAsociaciones getRepositorio() {return repositorioAsociaciones;}

    public void getAsociacionXMascota(String idMascota){
        //TODO la asociacion esta encargada de esto?
    }

    public List<Asociacion> getAsociaciones() {
        return asociaciones;
    }


    public Asociacion getAsociacion(String idAsoc) {
        Asociacion asoc = asociaciones.stream().filter( a -> a.getIdAsociacion().equals(idAsoc)).findFirst().orElse(null);
        if(asoc == null){
            throw new LogicRepoException("Id asociacion Inexistente");
        }
        return asoc;
    }

    public String getIDAsocXIdPublicacion(String idPublicacion) {
        Asociacion asociacion = this.asociaciones.stream().filter(asoc -> asoc.getPublicador().tienePublicacionPendiente(idPublicacion)).findFirst().orElse(null);
        if (asociacion == null) {
            throw new LogicRepoException("Id Publicacion Incorrecta");
        }

        return asociacion.getIdAsociacion();

    }

    public void agregarAsociacion(Asociacion asoc){
        this.asociaciones.add(asoc);
    }
    public void eliminarAsociacion(Asociacion asoc){
        this.asociaciones.remove(asoc);
    }

}
