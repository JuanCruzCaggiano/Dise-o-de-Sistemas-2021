package dds.domain.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControllerFAQ {
    public ControllerFAQ() {
    }
    public ModelAndView mostrarFAQ(Request req, Response rep){

        Map<String,Object> parametros = new HashMap<>();




        return new ModelAndView(parametros,"__FAQs.hbs");
    }
}
