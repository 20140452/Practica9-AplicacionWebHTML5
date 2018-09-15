package main;

import entidades.ServiciosEncuestas;
import servicios.ServiciosBootStrap;
import javax.persistence.EntityManager;
import static spark.Spark.staticFiles;

public class Main {
    private static EntityManager em;

    public static void main(String[] args) throws Exception{
        staticFiles.location("/templates");
        staticFiles.externalLocation("photos");
        ServiciosBootStrap.getInstancia().init();
        ServiciosEncuestas.getInstancia().instanciarDB();
        new RutasSpark().iniciarSpark();
        new Filtros().aplicarFiltros();
    }


}
