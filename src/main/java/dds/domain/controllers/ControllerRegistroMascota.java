package dds.domain.controllers;

import dds.db.RepositorioUsuarios;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ControllerRegistroMascota {
    public ControllerRegistroMascota() {
    }

    public ModelAndView registrarMascota(Request request, Response response) throws NoSuchAlgorithmException {
        String nombre = (request.queryParams("nombre") != null) ? request.queryParams("nombre") : "";
        String raza = (request.queryParams("raza") != null) ? request.queryParams("raza") : "";
        String desc = (request.queryParams("desc") != null) ? request.queryParams("desc") : "";
        //TODO: Falta ampliar la vista para poder poner todos los atributos de una mascota el resto es similar a lo del registro usuario


        Map<String,Object> parametros = new HashMap<>();

        response.redirect("/");
        return new ModelAndView(parametros,"index.hbs");
    }
}
