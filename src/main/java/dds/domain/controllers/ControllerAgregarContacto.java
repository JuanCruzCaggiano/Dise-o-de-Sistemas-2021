package dds.domain.controllers;

import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.persona.transaccion.RegistrarMascota;
import dds.domain.entities.seguridad.usuario.Usuario;
import dds.servicios.avisos.FormaNotificacion;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAgregarContacto {
    public ControllerAgregarContacto() {
    }
    //String nombre, String apellido, String telefono, String email, List<FormaNotificacion> formasDeNoti
    public Response agregarContacto(Request request, Response response) throws NoSuchAlgorithmException {
        Usuario usuario = request.session().attribute("usuario");
        Asociacion asoc = usuario.getAsociacion();
        String nombre = (request.queryParams("nombre") != null) ? request.queryParams("nombre") : "";
        String apellido  = (request.queryParams("apellido") != null) ? request.queryParams("apellido") : "";
        String telefono = (request.queryParams("telefono") != null) ? request.queryParams("telefono") : "";
        String email = (request.queryParams("email") != null) ? request.queryParams("email") : "";
        String formaEmail = (request.queryParams("formaEmail") != null) ? request.queryParams("formaEmail") : "";
        String formaWhatsapp = (request.queryParams("formaWhatsapp") != null) ? request.queryParams("formaWhatsapp") : "";
        String formaSMS = (request.queryParams("formaSMS") != null) ? request.queryParams("formaSMS") : "";

        //if()
        ArrayList<FormaNotificacion> formasDeNoti = null;



        //Mascota mascota = new Mascota(tip,nombre,apodo,dt,desc,fotos,caracteristica,sex);
        usuario.getPersona().getNotificador().agendarContacto(nombre,apellido,telefono,email,formasDeNoti);

        Map<String,Object> parametros = new HashMap<>();

        response.redirect("/panel#registroConExito");  //hay que ver como era el redirect
        return response;
    }
}
