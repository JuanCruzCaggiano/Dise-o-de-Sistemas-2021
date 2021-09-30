package dds.domain.controllers;

import dds.domain.entities.mascota.Mascota;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexController {

    public IndexController() {
    }

    public ModelAndView mostrarIndex(Request req, Response rep){

        Map<String,Object> parametros = new HashMap<>();




        return new ModelAndView(parametros,"index.hbs");
    }
}
