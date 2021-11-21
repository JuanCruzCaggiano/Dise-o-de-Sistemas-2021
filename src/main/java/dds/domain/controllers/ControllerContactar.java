package dds.domain.controllers;

import dds.db.RepositorioMascotas;
import dds.db.RepositorioPersonas;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.seguridad.usuario.Usuario;
import dds.servicios.avisos.Contacto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerContactar {
    public ControllerContactar() {
    }

    public ModelAndView contactarPersonaMascotaPerdida(Request req, Response rep) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario != null) {
            String idMascota = req.params("id");
            String idPersona = RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota);
            Persona persona = RepositorioPersonas.getRepositorio().getPersona(idPersona);

            List<Contacto> listaDeContactos = usuario.getPersona().getNotificador().getContactos();
            Contacto propio = listaDeContactos.get(0);
            persona.getNotificador().notificarPersona(propio.getNombre() + " encontró a tu mascota" + "\n" + "celular: " + propio.getTelefono() + "\n" + "mail : " + propio.getEmail());


            Map<String, Object> parametros = new HashMap<>();
            if (persona != null) {
                parametros.put("persona", usuario.getPersona());
                parametros.put("roles", persona.getListaRoles());
                parametros.put("contacto", persona.getNotificador().getContactos().get(0));
            } else {
                rep.redirect("/");
            }
            return new ModelAndView(parametros, "contactarDuenio.hbs");
        } else {
            rep.redirect("/#faltaLogin");

        }
        return new ModelAndView(new HashMap<>(), "/");
    }

    public ModelAndView contactarPersonaQuieroAdoptar(Request req, Response rep) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario != null) {
            String idMascota = req.params("id");
            String idPersona = RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota);
            Persona persona = RepositorioPersonas.getRepositorio().getPersona(idPersona);

            List<Contacto> listaDeContactos = usuario.getPersona().getNotificador().getContactos();
            Contacto propio = listaDeContactos.get(0);
            persona.getNotificador().notificarPersona(propio.getNombre() + " quiere adoptar a tu mascota" + "\n" + "celular: " + propio.getTelefono() + "\n" + "mail : " + propio.getEmail());


            Map<String, Object> parametros = new HashMap<>();
            if (persona != null) {
                //parametros.put("persona", persona);
                parametros.put("persona",usuario.getPersona());
                parametros.put("roles", persona.getListaRoles());
                parametros.put("contacto", persona.getNotificador().getContactos().get(0));
            } else {
                rep.redirect("/");
            }
            return new ModelAndView(parametros, "contactarDuenio.hbs");
        } else {
            rep.redirect("/#faltaLogin");
        }
        return new ModelAndView(new HashMap<>(), "/");
    }
}