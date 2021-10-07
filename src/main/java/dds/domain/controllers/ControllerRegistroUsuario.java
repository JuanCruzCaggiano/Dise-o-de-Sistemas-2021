package dds.domain.controllers;
import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import dds.db.RepositorioUsuarios;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.persona.TipoDocumento;
import dds.domain.entities.persona.roles.*;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.domain.entities.seguridad.usuario.Usuario;
import dds.servicios.avisos.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerRegistroUsuario {
    public ControllerRegistroUsuario() {
    }
    public ModelAndView mostrarRegistroUsuario(Request req, Response rep){

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario!=null) {
            rep.redirect("/");
        }

        List<String> enumNames = Stream.of(TipoDocumento.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        parametros.put("tipoDoc",enumNames);
        return new ModelAndView(parametros,"registroUsuario.hbs");
    }

    public ModelAndView crearUsuario(Request request, Response response) throws NoSuchAlgorithmException, ParseException {
        String user = (request.queryParams("usuario") != null) ? request.queryParams("usuario") : "";
        List<RolPersona>roles = new ArrayList<>();
        String adoptante = (request.queryParams("adoptante") != null) ? request.queryParams("adoptante") : "";
        if (adoptante!="") roles.add(new Adoptante());
        String duenio = (request.queryParams("duenio") != null) ? request.queryParams("duenio") : "";
        if (duenio!="") roles.add(new Duenio());
        String rescatista = (request.queryParams("rescatista") != null) ? request.queryParams("rescatista") : "";
        if (rescatista!="") roles.add(new Rescatista());
        String voluntario = (request.queryParams("voluntario") != null) ? request.queryParams("voluntario") : "";
        if (voluntario!="") roles.add(new Voluntario());
        String documento = (request.queryParams("documento") != null) ? request.queryParams("documento") : "";
        String telefono = (request.queryParams("telefono") != null) ? request.queryParams("telefono") : "";
        String fecha = (request.queryParams("fecha") != null) ? request.queryParams("fecha") : "";
        String direccion = (request.queryParams("direccion") != null) ? request.queryParams("direccion") : "";
        String tipoDoc = (request.queryParams("tipoDoc") != null) ? request.queryParams("tipoDoc") : "";
        String pass = (request.queryParams("password") != null) ? request.queryParams("password") : "";
        String nombre = (request.queryParams("nombre") != null) ? request.queryParams("nombre") : "";
        String apellido = (request.queryParams("apellido") != null) ? request.queryParams("apellido") : "";
        String email = (request.queryParams("email") != null) ? request.queryParams("email") : "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(fecha,dtf);
        List<FormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(new Email());
        formasDeNoti.add(new SMS());
        formasDeNoti.add(new WhatsApp());
        List<Mascota> mascotas = new ArrayList();

        TipoDocumento tipoDocumento = TipoDocumento.valueOf(tipoDoc);


        Persona persona = new Persona(nombre,apellido,tipoDocumento,Integer.valueOf(documento),dt,direccion,telefono,email,formasDeNoti);
        Map<String,Object> parametros = new HashMap<>(); //
        Standard usuario = new Standard(user,pass,persona);
        RepositorioUsuarios.getRepositorio().agregarUsuario(usuario);

        request.session().attribute("user",usuario);
        response.redirect("/"); //
        return new ModelAndView(parametros,"index.hbs");
    }
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
}
