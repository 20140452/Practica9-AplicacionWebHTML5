package main;

import entidades.ServiciosEncuestas;
import entidades.ServiciosNivelEducativo;
import entidades.ServiciosSectores;
import freemarker.template.Configuration;
import logical.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;
import java.util.List;

import static spark.Spark.*;

public class RutasSpark {
    public void iniciarSpark() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);
        List<Encuesta> encuestasLocales = new ArrayList<>();
        List<Sector> listaSectores = ServiciosSectores.getInstancia().listatOrdenados();
        List<NivelEducativo> listaNiveles = ServiciosNivelEducativo.getInstancia().listatOrdenados();


        get("/", (request, response) -> {
            response.redirect("/formulario");
            return "";
        });

        get("/formulario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sectores",listaSectores);
            attributes.put("nivelesEducativos",listaNiveles);
            return new ModelAndView(attributes, "formulario.html");
        }, freeMarkerEngine);

        post("/registrarEncuesta", (request, response) -> {
            try {
                String nombre = request.queryParams("nombre");
                String sector = request.queryParams("sector");
                String nivel = request.queryParams("nivel");
                String geolocation = request.queryParams("geolocalizacion").replace("Ubicación Actual: (",
                        "").replace(")","");

                float latitud = 0;
                float longitud = 0;

                if(!geolocation.equals("No se pudo obtener su localización...")) {
                    latitud = Float.parseFloat(geolocation.split(",")[0].trim());
                    longitud = Float.parseFloat(geolocation.split(",")[0].trim());
                }

                //Encuesta nuevaEncuesta = new Encuesta(nombre,new Sector(sector), new NivelEducativo(nivel),latitud,longitud);
                Encuesta nuevaEncuesta = new Encuesta(nombre,ServiciosSectores.getInstancia().findBySector(sector),
                        ServiciosNivelEducativo.getInstancia().findByNivel(nivel),latitud,longitud);
                ServiciosEncuestas.getInstancia().crear(nuevaEncuesta);

                System.out.println("    - Nombre: " + nombre + "\n"
                        + " - Sector: " + sector + "\n"
                        + " - Nivel: " + nivel + "\n"
                        + " - Latitud: " + latitud
                        + " - Longitud: " + longitud);

                response.redirect("/formulario");
            } catch (Exception e) {
                System.out.println("Error al registrar la encuesta!" + e.toString());
                response.redirect("/formulario");
            }
            return "";
        });

        get("/listaEncuestasDB", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("encuestas",ServiciosEncuestas.getInstancia().findAll());
            return new ModelAndView(attributes, "tabla.html");
        }, freeMarkerEngine);


    }


}