package dds.db;


import dds.db.repositorioException.LogicRepoException;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMascotas {

    private List<Mascota> mascotas = new ArrayList<>();

    private static RepositorioMascotas repositorioMascotas = new RepositorioMascotas() ;

    public static RepositorioMascotas getRepositorio() {return repositorioMascotas;}

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public Mascota getMascota(String id) {
        if(esIDValido(id)){
            return EntityManagerHelper.getEntityManager().find(Mascota.class, id) ;
        }else{
            throw new LogicRepoException("Id Mascota Inexistente");
        }
    }

    public boolean esIDValido(String ID) {
        return (EntityManagerHelper.getEntityManager().find(Mascota.class, ID) != null) ;
    }



}
