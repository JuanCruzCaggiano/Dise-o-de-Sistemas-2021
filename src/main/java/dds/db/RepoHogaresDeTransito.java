package dds.db;

import dds.domain.persona.Persona;
import dds.servicios.apiHogares.HogarDeTransito;

import java.util.ArrayList;
import java.util.List;

public class RepoHogaresDeTransito {
    static List<HogarDeTransito> hogares = new ArrayList<>();

    private static RepoHogaresDeTransito repoHogaresDeTransito = new RepoHogaresDeTransito() ;

    public static RepoHogaresDeTransito getInstance(){
        return repoHogaresDeTransito;
    }
    public List<HogarDeTransito> getRepositorio() {
         return hogares;
    }

    public void setRepositorio(List<HogarDeTransito> hogares){
        this.hogares = hogares;
    }
}



