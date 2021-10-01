package dds.domain.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerEncontreMascotaConChapita {
    public ControllerEncontreMascotaConChapita() {
    }
    public ModelAndView mostrarMascotaConChapita(Request req, Response rep){

        Map<String,Object> parametros = new HashMap<>();




        return new ModelAndView(parametros,"encontreMascotaConChapita.hbs");
    }
}
