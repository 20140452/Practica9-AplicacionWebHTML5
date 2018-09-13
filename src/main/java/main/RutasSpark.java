package main;

import entidades.*;
import freemarker.template.Configuration;
import logical.*;
import servicios.Encriptamiento;
import servicios.JsonTransformer;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static spark.Spark.*;

public class RutasSpark {
    public void iniciarSpark() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);

        get("/", (request, response) -> {
            Usuario logUser = request.session(true).attribute("usuario");
            if(logUser==null)
                response.redirect("/login");
            else
                response.redirect("practica9/home");
            return "";
        });

    }
}