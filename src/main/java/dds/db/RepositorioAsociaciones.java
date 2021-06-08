package dds.db;

import dds.domain.asociacion.Asociacion;

import java.util.List;
import java.util.ArrayList;


public class RepositorioAsociaciones {

    private List<Asociacion> asociaciones = new ArrayList<>();

    private static RepositorioAsociaciones repositorioAsociaciones = new RepositorioAsociaciones() ;

    public static RepositorioAsociaciones getRepositorio() {return repositorioAsociaciones;}


    public void agregarAsociacion(Asociacion asoc) {
        if (asociaciones==null)	{
            asociaciones = new ArrayList<>();
            asociaciones.add(asoc);}
        else {
            asociaciones.add(asoc);
        }
    }
    public void remover(Asociacion aRemover){
        asociaciones.remove(buscarAsociacion(aRemover));

    }
    public List<Asociacion> getAsociaciones() {
        return asociaciones;
    }

    public int buscarAsociacion(Asociacion buscada){   //TODO cuando persistamos buscamos por ID y no por nombre
        for (int i=0;i<asociaciones.size();i++){
            if (buscada.getNombre() == asociaciones.get(i).getNombre()){
                return i;
            }
        }
        return -1;
    }
}
