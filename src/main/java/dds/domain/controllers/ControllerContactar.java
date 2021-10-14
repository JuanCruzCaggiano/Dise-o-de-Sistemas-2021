package dds.domain.controllers;

import dds.db.RepositorioMascotas;
import dds.db.RepositorioPersonas;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerContactar {
    public ControllerContactar() {
    }

    public ModelAndView mostrarContactoAdop(Request req, Response rep){
        String idMascota = req.params("id");
        String idPersona = RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota);
        Persona persona = RepositorioPersonas.getRepositorio().getPersona(idPersona);

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            parametros.put("persona", usuario.getPersona());
            parametros.put("roles", usuario.getPersona().getListaRoles());
            parametros.put("usuario",persona);
        }



        return new ModelAndView(parametros,"contactarDuenio.hbs");
    }
}
