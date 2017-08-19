import dao.Sql2oBouquetDao;
import dao.Sql2oMemberDao;
import models.Bouquet;
import dao.Sql2oMemberDao;
import dao.Sql2oBouquetDao;
import models.Member;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oBouquetDao bouquetDao = new Sql2oBouquetDao(sql2o);

        ///..index..///
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get new team form..///
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Bouquet> teams = bouquetDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        ///..post new team form..///
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String teamDesc = request.queryParams("teamDesc");
            Bouquet newTeam = new Bouquet(teamName, teamDesc);
            bouquetDao.add(newTeam);
            List<Bouquet> teams = bouquetDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get read more..///
        get("/teams/:memId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idTeam = Integer.parseInt(request.params("memId"));
            List<Bouquet> teams = bouquetDao.getAll();
            model.put("teams", teams);
            Bouquet foundTeam = bouquetDao.findByBouquetId(idTeam);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get team update..///
        get("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int intId = Integer.parseInt(request.params("memId"));
            model.put("teamEdit", true);
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        ///..post team update..///
        post("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idEdit = Integer.parseInt(request.queryParams("newTeamId"));
            String newName = request.queryParams("newTeamName");
            String newDesc = request.queryParams("newTeamDesc");
            bouquetDao.update(bouquetDao.findByBouquetId(idEdit).getId(), newName, newDesc);
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get delete single team..///
        get("/teams/:teamId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idDelete = Integer.parseInt(request.params("teamId"));
            Bouquet delete = bouquetDao.findByBouquetId(idDelete);
            bouquetDao.deleteByBouquetId(idDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get delete all teams..///
        get("/teams/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            bouquetDao.deleteAllBouquets();
            memberDao.deleteMembers();
            List<Member> allMembers = memberDao.getAll();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get delete single member..///
        get("/members/:memberId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idMember = Integer.parseInt(request.params("memberId"));
            Member deleteMember = memberDao.findId(idMember);
            memberDao.deleteId(idMember);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get delete all members..///
        get("/members/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteMembers();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get new member form..///
        get("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        ///..post new member form..///
        post("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("teams", allTeams);

            String name = request.queryParams("name");
            int memId = Integer.parseInt(request.queryParams("memId"));
            Member member1 = new Member(name, memId);
            memberDao.add(member1);
            model.put("member", member1);
            return new ModelAndView(model, "success_2.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get single member info..///
        get("/members/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idMember = Integer.parseInt(request.params("id"));
            Member memberFind = memberDao.findId(idMember);
            model.put("member", memberFind);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get update member form..///
        get("/teams/:memId/members/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idMember = Integer.parseInt(request.params("id"));
            int idTeam = Integer.parseInt(request.params("memId"));
            Member editMember = memberDao.findId(idMember);
            model.put("editMember", editMember);
            model.put("idTeam", idTeam);
            List<Member> allMembers = memberDao.getAll();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("members", allMembers);
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        ///..post update member form..///
        post("/teams/:memId/members/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int idMember = Integer.parseInt(request.params("id"));
            int idTeam = Integer.parseInt(request.params("memId"));
            Member editMember = memberDao.findId(idMember);
            memberDao.update(idMember, newName, idTeam);
            model.put("idMember", idMember);
            return new ModelAndView(model, "sucess.hbs");
        }, new HandlebarsTemplateEngine());





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
