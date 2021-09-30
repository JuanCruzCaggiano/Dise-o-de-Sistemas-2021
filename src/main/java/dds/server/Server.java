package dds.server;

import spark.Spark;


public class Server {
    public static void main(String[] args) {
        Spark.port(9000);
        Router.init();
        //
        //DebugScreen.enableDebugScreen(); //solo se usa en ambiente de trabajo a la hora de presentar vuela
    }
}