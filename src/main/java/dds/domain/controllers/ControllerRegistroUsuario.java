package dds.domain.controllers;
import dds.db.RepositorioUsuarios;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerRegistroUsuario {
    public ControllerRegistroUsuario() {
    }
    public ModelAndView mostrarRegistroUsuario(Request req, Response rep){

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            rep.redirect("/");
        }



        return new ModelAndView(parametros,"registroUsuario.hbs");
    }

    public ModelAndView crearUsuario(Request request, Response response) throws NoSuchAlgorithmException {
        String user = (request.queryParams("usuario") != null) ? request.queryParams("usuario") : "";
        String pass = (request.queryParams("password") != null) ? request.queryParams("password") : "";
        String nombre = (request.queryParams("nombre") != null) ? request.queryParams("nombre") : "";
        String apellido = (request.queryParams("apellido") != null) ? request.queryParams("apellido") : "";
        String email = (request.queryParams("email") != null) ? request.queryParams("email") : "";
        Standard usuario = new Standard(user,pass); //TODO: Falta modificar los datos para poder crear a la persona y sus roles de manera exitosa(hay que ampliar el form)
        Map<String,Object> parametros = new HashMap<>(); //TODO: Dejo esto asi para testear la creacion exitosa de un usuario sin persona asociada
        RepositorioUsuarios.getRepositorio().agregarUsuario(usuario);
        response.redirect("/"); //TODO: hay que cambiarlo para que no sea un model and view sino un redirect de una
        return new ModelAndView(parametros,"index.hbs");
    }
}
