package dds.server;


import dds.domain.controllers.AdopcionController;
import dds.domain.controllers.IndexController;
import dds.domain.controllers.UsuarioController;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import dds.spark.utils.BooleanHelper;
import dds.spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() { //construimos instancia de hbs
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        //Spark.get("/",(req,res)->"hola");  //+ req.queryParams("nombre") + " " +req.queryParams("apellido")
        // localhost:9000/?nombre=sarasa&apellido=sarasa2

        //Spark.get("/usuario/:id", (req,res)->req.params("id"));  //req.splat()[0] devuelve array de asteriscos
        UsuarioController usuarioController = new UsuarioController();
        Spark.get("/saludar",usuarioController::mostrarComandera, Router.engine); //usa :: para invocar al metodo pero desde la ruta y no desde el router

        AdopcionController adopcionController = new AdopcionController();
        Spark.get("/adopcion",adopcionController::mostrarMascotas, Router.engine);
        Spark.get("/adopcion/:id",adopcionController::mostrarMascotas, Router.engine);

        IndexController indexController = new IndexController();
        Spark.get("/",indexController::mostrarIndex,Router.engine);
    }

}