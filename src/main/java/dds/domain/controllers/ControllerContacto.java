package dds.domain.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerContacto {
    public ControllerContacto() {
    }
    public ModelAndView mostrarContacto(Request req, Response rep){

        Map<String,Object> parametros = new HashMap<>();




        return new ModelAndView(parametros,"__contacto.hbs");
    }
}
