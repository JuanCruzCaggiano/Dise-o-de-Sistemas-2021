package dds.domain.controllers;

import dds.db.RepositorioUsuarios;
import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.persona.TipoDocumento;
import dds.domain.entities.persona.transaccion.RegistrarMascota;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.domain.entities.seguridad.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerRegistroMascota {
    public ControllerRegistroMascota() {
    }

    public Response registrarMascota(Request request, Response response) throws NoSuchAlgorithmException {
        Usuario usuario = request.session().attribute("usuario");
        Asociacion asoc = usuario.getAsociacion();
        String tipo = (request.queryParams("tipo") != null) ? request.queryParams("tipo") : "";
        String nombre = (request.queryParams("nombre") != null) ? request.queryParams("nombre") : "";
        String desc = (request.queryParams("desc") != null) ? request.queryParams("desc") : "";
        String apodo  = (request.queryParams("apodo") != null) ? request.queryParams("apodo") : "";
        String sexo = (request.queryParams("sexo") != null) ? request.queryParams("sexo") : "";
        String caracs = (request.queryParams("caracteristicas") != null) ? request.queryParams("caracteristicas") : "";
        String foto = (request.queryParams("fotos") != null) ? request.queryParams("fotos") : "";
        String fecha = (request.queryParams("fecha") != null) ? request.queryParams("fecha") : "";
        List<String> fotos = new ArrayList<>(); // TODO: Manejo de fotos
        Sexo sex= Sexo.valueOf(sexo);
        TipoMascota tip = TipoMascota.valueOf(tipo);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(fecha,dtf);
        HashMap <String, String> caracteristica = new HashMap<>();
        List<String> preguntasCaracs = asoc.getConfigurador().getClaves();
        for (int i =0 ;i<preguntasCaracs.size();i++){
            caracteristica.put(preguntasCaracs.get(i),(request.queryParams(preguntasCaracs.get(i)) != null) ? request.queryParams(preguntasCaracs.get(i)) : "");
        }
        //Mascota mascota = new Mascota(tip,nombre,apodo,dt,desc,fotos,caracteristica,sex);
        usuario.getPersona().ejecutarTransaccion(new RegistrarMascota(usuario.getPersona(),tip,nombre,apodo,dt,desc,fotos,caracteristica,sex));

        Map<String,Object> parametros = new HashMap<>();

        response.redirect("/panel");  //hay que ver como era el redirect
        return response;
    }
}
