package dds.domain.controllers;

import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.persona.roles.Rescatista;
import dds.domain.entities.persona.transaccion.EncontreMascotaPerdidaSinChapita;
import dds.domain.entities.seguridad.usuario.Usuario;
import dds.servicios.helpers.PhotoUploaderHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerEncontreMascotaSinChapita {
    public ControllerEncontreMascotaSinChapita() {
    }

    public ModelAndView mostrarEncontreMascotaSinChapita(Request request, Response response) {

        Usuario usuario = request.session().attribute("usuario");
        Map<String, Object> parametros = new HashMap<>();

        if (usuario != null) {
            if (usuario.soyAdmin()) {
                parametros.put("Admin", 1);
                parametros.put("asociacion", usuario.getAsociacion());
            } else {
                if (!usuario.getPersona().getListaRoles().stream().anyMatch(p -> (p.getNombre().equals("Rescatista")))) {
                    usuario.getPersona().agregarRol(Rescatista.getRescatista());
                }
                parametros.put("standard", 1);
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
        } else {
            response.redirect("/#faltaLogin");
        }
        return new ModelAndView(parametros, "encontreMascotaSinChapita.hbs");
    }


    //personaRescat.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita((float)-34.605807,(float)-58.438423,new ArrayList<>(),"Perfecto estado",personaRescat.getIdPersona()));
    public Response crearMascotaPerdidaSinChapita(Request request, Response response) {
        Usuario usuario = request.session().attribute("usuario");
        String latitud = null;
        String longitud = null;
        String descripcion = null;
        String foto = null;
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        try {
            latitud =  PhotoUploaderHelper.getHelper().convertInputStreamToString(request.raw().getPart("lat").getInputStream());
            longitud = PhotoUploaderHelper.getHelper().convertInputStreamToString(request.raw().getPart("long").getInputStream());
            descripcion = PhotoUploaderHelper.getHelper().convertInputStreamToString(request.raw().getPart("descripcion").getInputStream());
            foto = PhotoUploaderHelper.getHelper().uploadPhoto(request.raw().getPart("foto").getInputStream());

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> listaFotos = new ArrayList<>();
        listaFotos.add(foto);
        Persona rescatista = usuario.getPersona();
        Float fLatitud = Float.parseFloat(latitud);
        Float fLongitud = Float.parseFloat(longitud);
        rescatista.ejecutarTransaccion(new EncontreMascotaPerdidaSinChapita(fLatitud, fLongitud, listaFotos, descripcion, rescatista.getIdPersona()));
        response.redirect("/panel#registroMascotaConExito");
        return response;
    }

}
