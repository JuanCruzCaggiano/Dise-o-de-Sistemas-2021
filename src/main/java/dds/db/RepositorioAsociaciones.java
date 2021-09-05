package dds.db;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.domain.seguridad.usuario.Usuario;
import dds.servicios.helpers.CalcDistanciaHelper;
import dds.servicios.publicaciones.Publicador;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


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


    public Asociacion getAsociacion(int idAsoc) {
        Asociacion asoc = asociaciones.stream().filter( a -> a.getIdAsociacion()==(idAsoc)).findFirst().orElse(null);
        if(asoc == null){
            throw new LogicRepoException("Id asociacion Inexistente");
        }
        return asoc;
    }

    public int getIDAsocXIdPublicacion(String idPublicacion) {
        Asociacion asociacion = this.asociaciones.stream().filter(asoc -> asoc.getPublicador().tienePublicacionPendiente(idPublicacion)).findFirst().orElse(null);
        if (asociacion == null) {
            throw new LogicRepoException("Id Publicacion Incorrecta");
        }

        return asociacion.getIdAsociacion();

    }
    public Asociacion getAsociacionMasCercana(double latitud,double longitud){
        Asociacion asoc ;
        asoc = RepositorioAsociaciones.getRepositorio().getAsociaciones().stream()
                .min(Comparator.comparingDouble(a->CalcDistanciaHelper.getHelper().distanciaCoord(a.getUbicacion().getLat(), a.getUbicacion().getLongitud(), latitud, longitud))).orElse(null);
        return asoc;
    }

    public void agregarAsociacion(Asociacion asoc){
        this.asociaciones.add(asoc);
    }
    public void eliminarAsociacion(Asociacion asoc){
        this.asociaciones.remove(asoc);
    }

}
