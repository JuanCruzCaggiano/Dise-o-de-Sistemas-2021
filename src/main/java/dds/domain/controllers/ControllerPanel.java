package dds.domain.controllers;
import dds.domain.entities.persona.Persona;

import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerPanel {
    public ControllerPanel() {
    }
    public ModelAndView mostrarRegistroMascota(Request req, Response rep){
        Usuario usuario = req.session().attribute("usuario");

        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            parametros.put("persona", usuario.getPersona());
            parametros.put("roles", usuario.getPersona().getListaRoles());
            if(usuario.getPersona().getListaRoles().stream().anyMatch(p->(p.getNombre().equals("Duenio")))){
                parametros.put("Duenio",1);
            }
            if(usuario.getPersona().getListaRoles().stream().anyMatch(p->(p.getNombre().equals("Adoptante")))){
                parametros.put("Adoptante",1);
            }
            if(usuario.getPersona().getListaRoles().stream().anyMatch(p->(p.getNombre().equals("Rescatista")))){
                parametros.put("Rescatista",1);
            }
            if(usuario.getPersona().getListaRoles().stream().anyMatch(p->(p.getNombre().equals("Voluntario")))){
                parametros.put("Voluntario",1);
            }
            if(usuario.soyAdmin()){
                parametros.put("Admin",1);
            }
        }else{
            rep.redirect("/");
        }


        return new ModelAndView(parametros,"registroMascota.hbs");
    }
}
