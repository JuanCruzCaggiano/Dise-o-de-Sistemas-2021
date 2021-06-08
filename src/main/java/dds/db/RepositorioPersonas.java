package dds.db;



import dds.db.repositorioException.LogicRepoException;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPersonas {

    private List<Persona> personas = new ArrayList<>();

    private static RepositorioPersonas repositorioPersonas = new RepositorioPersonas() ;

    public static RepositorioPersonas getRepositorio() {return repositorioPersonas;}

    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }
    public void removerPersona(Persona aRemover){
        personas.remove(buscarPersona(aRemover));

    }
    public List<Persona> getPersonas() {
        return personas;
    }

    public int buscarPersona(Persona buscada){   //TODO cuando persistamos buscamos por ID y no por nombre
        for (int i = 0; i< personas.size(); i++){
            if (buscada.getNombre() == personas.get(i).getNombre()){
                return i;
            }
        }
        return -1;
    }
    public int tieneMascota(Mascota mascota){  //TODO mismo que arriba, esto se busca por id  /esto devuelve de quien es la mascota
        for (int i = 0; i< personas.size(); i++){
            for (int j = 0; j< personas.get(i).getMascotas().size(); j++) {
                if (mascota.getNombre() == personas.get(i).getMascotas().get(j).getNombre()) {
                    return i;
                }
            }
        }
        return -1;
    }
    public String getIdPersonaXidMascota(String idMascota){  //F
        for (int i = 0; i< personas.size(); i++){
            for (int j = 0; j< personas.get(i).getMascotas().size(); j++) {
                if (personas.get(i).getMascotas().get(j).getIdMascota().equals(idMascota)) {
                    return personas.get(i).getIdPersona();
                }
            }
        }
        return null;
    }



}
