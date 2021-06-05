package dds.db;

import dds.domain.asociacion.Asociacion;
import dds.domain.seguridad.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepoUsuarios {


    List<Usuario> usuarios= new ArrayList<>();

    private static RepoUsuarios repositorioUsuarios = new RepoUsuarios() ;


    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }


    public static RepoUsuarios getRepositorio() {return repositorioUsuarios;}


    public List<Usuario> getUsuarios() {return usuarios;}


    public Usuario getUsuario(String username) {
        Usuario usuarioBuscado = usuarios.stream().filter( usuario1 -> usuario1.getUserName().equals(username)).findFirst().orElse(null);

        return usuarioBuscado;
    }




}
