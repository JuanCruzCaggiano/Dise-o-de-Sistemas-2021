package dds.db;

import dds.servicios.apiHogares.HogarDeTransito;

import java.util.ArrayList;
import java.util.List;

public class RepositorioHogaresDeTransito {
    static List<HogarDeTransito> hogares = new ArrayList<>();

    private static RepositorioHogaresDeTransito repositorioHogaresDeTransito = new RepositorioHogaresDeTransito() ;

    public static RepositorioHogaresDeTransito getRepositorio(){
        return repositorioHogaresDeTransito;
    }
    public List<HogarDeTransito> getHogares() {
         return hogares;
    }

    public void setRepositorio(List<HogarDeTransito> hogares){
        this.hogares = hogares;
    }
}



