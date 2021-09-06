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

        if(esIDValido(idPersona)){
            return EntityManagerHelper.getEntityManager().find(Persona.class, idPersona) ;
        }else{
            throw new LogicRepoException("Id Persona Inexistente");
        }
    }

    public String getIdPersonaXidMascota(String idMascota){
        if(RepositorioMascotas.getRepositorio().esIDValido(idMascota)){
            String jql = "Select p from Persona p, Mascota m where m.idMascota = :idMascota";
            Persona persona = (Persona) EntityManagerHelper.getEntityManager().createQuery(jql).
                    setParameter("idMascota",idMascota).getResultList().get(0);
            return  persona.getIdPersona();

        }else {
            throw new LogicRepoException("IdMascota inexistente");
        }
    }


        public boolean esIDValido(String ID) {
        return (EntityManagerHelper.getEntityManager().find(Persona.class, ID) != null) ;
    }



}
