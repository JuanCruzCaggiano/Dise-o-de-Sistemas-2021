package dds.domain.controllers;


import dds.db.RepositorioUsuarios;
import dds.domain.entities.seguridad.usuario.Usuario;
import dds.domain.entities.seguridad.validador.ValidadorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ControllerLogin {

    public ControllerLogin() {
    }



    public ModelAndView login (Request request, Response response) throws NoSuchAlgorithmException {

        //INPUT
        String user = (request.queryParams("usuario") != null) ? request.queryParams("usuario") : "";
        String pass = (request.queryParams("password") != null) ? request.queryParams("password") : "";


        Boolean datosOk = false;
        if (user.isEmpty()) {
            response.status(401);
            response.redirect("/");
        } else {
            Usuario usuario = RepositorioUsuarios.getRepositorio().getUsuario(user);
            try {
                datosOk = ValidadorUsuario.getValidadorUsuario().validarIdentidad (user, pass);
            } catch (Exception e) {
                datosOk = false;
            }


            if (datosOk) {
                request.session(true);
                request.session().attribute("user", user);
                request.session().attribute("usuario", usuario);
                request.session().maxInactiveInterval(3600);
                response.redirect("/");
            } else {
                response.status(401);
                response.redirect("/login#loginError");
            }
        }
        //OUTPUT
        Map<String, Object> map = new HashMap<>();
        map.put("loginError", 1);
        map.put("usuario", user);
        return new ModelAndView(map, "registroMascota.hbs");
    }

}
