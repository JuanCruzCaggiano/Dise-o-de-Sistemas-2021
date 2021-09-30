package dds.domain.controllers;


import dds.db.RepositorioMascotas;
import dds.domain.entities.mascota.Mascota;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdopcionController {
    private RepositorioMascotas repositorio;

    public AdopcionController(){this.repositorio=RepositorioMascotas.getRepositorio();}


    public ModelAndView mostrarMascotas(Request req,Response rep){

        Map<String,Object> parametros = new HashMap<>();
        System.out.print("asdddddddddddddddddddd1");
        List<Mascota> mascotas = this.repositorio.getMascotas();

        System.out.print("asdddddddddddddddddddd");
        parametros.put("mascotas",mascotas);

        return new ModelAndView(parametros,"adopcion.hbs");
    }
    public ModelAndView mascotaId(Request req,Response rep){
        //validar que exista el id
        Mascota mascota = this.repositorio.getMascota(new String(req.params("id")));//o lo que sea el id
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("mascotas",mascota);
        return new ModelAndView(parametros,"mascota.hbs"); //TODO: crear ventana que muestre 1 sola mascota por id con datos de contacto
        // {{#if mascota}} value = "{{mascota.apodo}}" {{/if}}  esto muestras el apodo de la mascota en el .hbs
    }
/* ESTO VA EN MASCOTA CONTROLLER y va en un spark.post
    public Response mascotaId(Request req,Response rep){
        //validar que exista el id
        Mascota mascota = this.repositorio.getMascota(new String(req.params("id")));//o lo que sea el id
        String nuevoNombre = request.queryParams("nombre"); //meter try catch y lo que va en el queryparams tiene que coincidir con el input del html
        mascota.setNombre(nuevoNombre);
    }*/
}
