package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.seguridad.usuario.Standard;
import dds.domain.seguridad.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class RepositorioUsuarios {


    List<Usuario> usuarios= new ArrayList<>();

    private static RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios() ;

    public static RepositorioUsuarios getRepositorio() {return repositorioUsuarios;}

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {return usuarios;}


    public Usuario getUsuario(String username) {
        Usuario usuarioBuscado = usuarios.stream().filter( usuario1 -> usuario1.getUserName().equals(username)).findFirst().orElse(null);

        return usuarioBuscado;
    }

    public String getIdUsuarioXPersona(String idPersona){
            String id = usuarios.stream().filter(usuario -> usuario.getPersona().getIdPersona()
                                                             .equals(idPersona)).findFirst().orElse(null).getIdUsuario();
            if(id == null){
                throw new LogicRepoException("idPersona Incorrecto");
            }

           return id;

    }


}
