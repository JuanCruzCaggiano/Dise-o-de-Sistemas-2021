package dds.domain.controllers;
import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.asociacion.Configurador;
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
        Asociacion asoc;
        Configurador config ;
        List<String>preguntas;
        List<String>pregMascotas;
        Map<String,Object> parametros = new HashMap<>();
        if(usuario.soyAdmin()) {
            asoc = usuario.getAsociacion();
            config = asoc.getConfigurador();
            preguntas = config.getPreguntasOpcionales();
            pregMascotas = config.getClaves();
            parametros.put("config",config);
            parametros.put("Admin",1);
            parametros.put("asociacion",asoc);
            parametros.put("preguntas",preguntas);
            parametros.put("preguntasMascotas",pregMascotas);
        }else{
            rep.redirect("/");
        }




        return new ModelAndView(parametros,"configurarCaracteristicasAsociacion.hbs");
    }
    public ModelAndView configurarCaracteristicas(Request req, Response rep){

        Usuario usuario = req.session().attribute("usuario");
        Map<String,Object> parametros = new HashMap<>();
        if(usuario.soyAdmin()) {

        }else{
            rep.redirect("/");
        }




        return new ModelAndView(parametros,"configurarCaracteristicasAsociacion.hbs");
    }

}
