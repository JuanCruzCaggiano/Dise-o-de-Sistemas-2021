package dds.domain.controllers;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerConfigurarCaracteristicasAsociacion {
    public ControllerConfigurarCaracteristicasAsociacion() {
    }
    public ModelAndView mostrarConfigurarCaracteristicasAsociacion(Request req, Response rep){

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) { //TODO: y que sea admin
            parametros.put("persona", usuario.getPersona());
            parametros.put("roles", usuario.getPersona().getListaRoles());
        }else{
            rep.redirect("/");
        }




        return new ModelAndView(parametros,"configurarCaracteristicasAsociacion.hbs");
    }
}
