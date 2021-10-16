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

    public ModelAndView contactarPersona(Request req, Response rep){
        String idMascota = req.params("id");

        Usuario usuario = req.session().attribute("usuario");
        List<Contacto> listaDeContactos = usuario.getPersona().getNotificador().getContactos();
        Contacto propio = listaDeContactos.get(0);
        usuario.getPersona().getNotificador().notificarPersona(propio.getNombre()+" quiere adoptar a tu mascota"+"\n" + "celular: " + propio.getTelefono() + "\n"+ "mail : " + propio.getEmail());


        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            parametros.put("persona", usuario.getPersona());
            parametros.put("roles", usuario.getPersona().getListaRoles());
            parametros.put("contacto",propio);
        }
        rep.redirect("/");
        return new ModelAndView(parametros,"contactarDuenio.hbs");
    }
    /*public ModelAndView mostrarContactoAdop(Request req, Response rep){
        String idMascota = req.params("id");
        String idPersona = RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota);
        Persona persona = RepositorioPersonas.getRepositorio().getPersona(idPersona);
        List<Contacto> listaDeContactos = persona.getNotificador().getContactos();
        Contacto propio = listaDeContactos.get(0);

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            parametros.put("persona", usuario.getPersona());
            parametros.put("roles", usuario.getPersona().getListaRoles());
            parametros.put("usuario",persona);
            parametros.put("contacto",propio);
        }



        return new ModelAndView(parametros,"contactarDuenio.hbs");
    }*/
}
