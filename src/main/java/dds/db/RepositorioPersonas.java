package dds.db;



import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
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
    public void removerPersona(Persona persona){
        personas.remove(persona);
    }
    public List<Persona> getPersonas() {
        return personas;
    }

    public Persona getPersona(String idPersona) {
        Persona persona = personas.stream().filter(p -> p.getIdPersona().equals(idPersona)).findFirst().orElse(null);
        if(persona == null){
            throw new LogicRepoException("Id Persona Inexistente");
        }
        return persona;
    }

    public String getIdPersonaXidMascota(String idMascota){

        Persona persona1 = personas.stream().filter(persona -> persona.getMascotas()
                                                                      .stream().anyMatch(mascota -> mascota.getIdMascota().equals(idMascota)))
                .findFirst().orElse(null);
        if(persona1 == null){
            throw new LogicRepoException("IdMascota inexistente");
        }else {
            return persona1.getIdPersona();
        }

    }




}
