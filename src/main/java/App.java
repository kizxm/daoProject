import models.Bouquet;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");



//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            ArrayList<Bouquet> allFlowers = Bouquet.getAllFlowers();
//            model.put("allFlowers", allFlowers);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/flowers/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "flower-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/flowers/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String flower1 = request.queryParams("flower1");
//            String flower2 = request.queryParams("flower2");
//            String flower3 = request.queryParams("flower3");
//            String flower4 = request.queryParams("flower4");
//            Bouquet newFlower = new Bouquet(flower1, flower2, flower3, flower4);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/flowers/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfFlowerToFind = Integer.parseInt(req.params("id"));
//            Bouquet flowersMade = Bouquet.findById(idOfFlowerToFind);
//            model.put("flowers", flowersMade);
//            return new ModelAndView(model, "flower-details.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/flowers/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToEdit = Integer.parseInt(req.params("id"));
//            Bouquet editFlowers = Bouquet.findById(idOfPostToEdit);
//            model.put("editFlowers", editFlowers);
//            return new ModelAndView(model, "flower-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/flowers/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String flower1 = req.queryParams("flower1");
//            String flower2 = req.queryParams("flower2");
//            String flower3 = req.queryParams("flower3");
//            String flower4 = req.queryParams("flower4");
//            int idOfPostToEdit = Integer.parseInt(req.params("id"));
//            Bouquet editFlowers = Bouquet.findById(idOfPostToEdit);
//            editFlowers.update(flower1, flower2, flower3, flower4);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//

    }
}