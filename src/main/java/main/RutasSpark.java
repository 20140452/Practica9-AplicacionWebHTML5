package main;

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
            /*Usuario logUser = request.session(true).attribute("usuario");
            if(logUser==null)
                response.redirect("/login");
            else
                response.redirect("practica9/home");*/
            return "";
        });

        get("/formulario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sectores",listaSectores);
            attributes.put("nivelesEducativos",listaNiveles);
            return new ModelAndView(attributes, "index.html");
        }, freeMarkerEngine);

        post("/registrarEncuesta", (request, response) -> {
            try{
                Encuesta postAEditar = ServiciosPost.getInstancia().find(Long.parseLong(idPostActual));

                String cuerpo = request.queryParams("cuerpo");

                postAEditar.setCuerpo(cuerpo);
                ServiciosPost.getInstancia().editar(postAEditar);

                Usuario logUser = request.session(true).attribute("usuario");
                response.redirect("/redSocial/userArea/"+logUser.getCorreo()+"/perfilUsuario");

            }catch (Exception e){
                System.out.println("Error al editar el post: " + e.toString());
            }
            return "";
        });

    }


}