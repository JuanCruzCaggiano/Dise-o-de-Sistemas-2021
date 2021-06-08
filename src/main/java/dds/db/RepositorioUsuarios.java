package dds.db;

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
           return usuarios.stream().filter(usuario -> usuario.getIdUsuario().equals(idPersona)).findFirst().orElse(null).getIdUsuario();
    }


}
