package dds.db;



import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPersonas {
    private List<Persona> personas = new ArrayList<>();

    public void agregarPersona(Persona pppl) {
            personas.add(pppl);
    }
    public void removerPersona(Persona aRemover){
        personas.remove(buscarPersona(aRemover));

    }
    public List<Persona> getRepositorio() {
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
}
