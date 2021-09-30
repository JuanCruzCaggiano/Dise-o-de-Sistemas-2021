package dds.domain.controllers;

import dds.db.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

//hacer un controller por entidad/recurso   y login
public class UsuarioController {
    private RepositorioUsuarios repositorio;

    public UsuarioController(){
        this.repositorio=RepositorioUsuarios.getRepositorio();
    }

    public String saludar(Request request, Response response){
        return ("hola");
    }
    public ModelAndView mostrarComandera(Request req,Response rep){
        return new ModelAndView(new HashMap<>(),"registroMascota.hbs");
    }
}
