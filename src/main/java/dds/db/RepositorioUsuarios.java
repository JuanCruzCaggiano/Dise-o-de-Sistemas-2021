package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.seguridad.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {


    List<Usuario> usuarios= new ArrayList<>();

    private static RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios() ;

    public static RepositorioUsuarios getRepositorio() {return repositorioUsuarios;}

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {return usuarios;}


    public Usuario getUsuario(String username) {
        Usuario user =  usuarios.stream().filter( usuario1 -> usuario1.getUserName().equals(username)).findFirst().orElse(null);

        if(user == null){
            throw new LogicRepoException("Username inexistente");
        }
        return user;
    }

    public String getUserNameXIdPersona(String userName) {
        Usuario user = usuarios.stream()
                .filter(usuario -> usuario.getPersona().getIdPersona().equals(userName))
                .findFirst().orElse(null);

        if (user == null) {
            throw new LogicRepoException("idPersona Incorrecto");
        }

        return user.getUserName();


    }

    public String getIDAsocXIdPersona(String idPersona) {
        String userName = getUserNameXIdPersona(idPersona);
        Usuario usuario1 = getUsuario(userName);
        if (usuario1 == null) {
            throw new LogicRepoException("idUsuario Incorrecto");
        }
        return usuario1.getAsociacion().getIdAsociacion();

    }

    public String getIDAsocXIdMascota(String idMascota) {
        String idPersona = RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota);
        String userName = this.getUserNameXIdPersona(idPersona);
        Usuario usuario1 = getUsuario(userName);
        if (usuario1 == null) {
            throw new LogicRepoException("userName Incorrecto");
        }
        return usuario1.getAsociacion().getIdAsociacion();


    }


}
