package dds.domain.controllers;

import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerEncontreMascotaSinChapita {
    public ControllerEncontreMascotaSinChapita() {
    }

    public ModelAndView mostrarEncontreMascotaSinChapita(Request req, Response rep) {

        Usuario usuario = req.session().attribute("usuario");
        Map<String, Object> parametros = new HashMap<>();

        if (usuario != null) {
            if (usuario.soyAdmin()) {
                parametros.put("Admin", 1);
                parametros.put("asociacion", usuario.getAsociacion());
            } else {
                parametros.put("persona", usuario.getPersona());
                parametros.put("roles", usuario.getPersona().getListaRoles());
                if (usuario.getPersona().getListaRoles().stream().anyMatch(p -> (p.getNombre().equals("Duenio")))) {
                    parametros.put("Duenio", 1);
                }
                if (usuario.getPersona().getListaRoles().stream().anyMatch(p -> (p.getNombre().equals("Adoptante")))) {
                    parametros.put("Adoptante", 1);
                }
                if (usuario.getPersona().getListaRoles().stream().anyMatch(p -> (p.getNombre().equals("Rescatista")))) {
                    parametros.put("Rescatista", 1);
                }
                if (usuario.getPersona().getListaRoles().stream().anyMatch(p -> (p.getNombre().equals("Voluntario")))) {
                    parametros.put("Voluntario", 1);
                }
            }
        }


        return new ModelAndView(parametros, "encontreMascotaSinChapita.hbs");
    }
}
