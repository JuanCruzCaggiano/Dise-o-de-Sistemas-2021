package dds.domain.asociacion;

import java.util.List;
import java.util.ArrayList;


public class RepoAsociacion {
    private List<Asociacion> asociaciones;

    public void agregarAsociacion(Asociacion asoc) {
        if (asociaciones==null)	{
            asociaciones = new ArrayList<Asociacion>();
            asociaciones.add(asoc);}
        else {
            asociaciones.add(asoc);
        }
    }
    public void remover(Asociacion aRemover){
        asociaciones.remove(buscarAsociacion(aRemover));

    }
    public List<Asociacion> getRepoAsociaciones() {
        return asociaciones;
    }

    public int buscarAsociacion(Asociacion buscada){
        for (int i=0;i<asociaciones.size();i++){
            if (buscada.getNombre() == asociaciones.get(i).getNombre()){
                return i;
            }
        }
        return -1;
    }
}
