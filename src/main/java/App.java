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

        ///..get delete all teams..///
        get("/teams/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            bouquetDao.deleteAllBouquets();
            memberDao.deleteMembers();
            return new ModelAndView(model, "success.hbs");
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
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idTeam = Integer.parseInt(request.params("id"));
            List<Bouquet> teams = bouquetDao.getAll();
            model.put("teams", teams);
            Bouquet foundTeam = bouquetDao.findByBouquetId(idTeam);
            model.put("team", foundTeam);
            List<Member> allMembers = bouquetDao.getAllBouquetMembers(idTeam);
            model.put("members", allMembers);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get team update form..///
        get("/teams/:teamId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int intId = Integer.parseInt(request.params("teamId"));
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

        ///..get delete single member..///
        get("/teams/:id/members/:teamId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamIdMemberDelete = Integer.parseInt(request.params("id"));
            int idMemberDelete = Integer.parseInt(request.params("teamId"));
            Member deleteMember = memberDao.findId(idMemberDelete);
            memberDao.deleteId(idMemberDelete);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
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
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            Member member1 = new Member(name, teamId);
            memberDao.add(member1);
            model.put("member", member1);
            return new ModelAndView(model, "success_2.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get single member info..///
        get("/teams/:teamId/members/:memId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idMember = Integer.parseInt(request.params("memId"));
            Member memberFind = memberDao.findId(idMember);
            model.put("member", memberFind);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        ///..get update member form..///
        get("/teams/:id/members/:teamId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idMember = Integer.parseInt(request.params("id"));
            int idTeam = Integer.parseInt(request.params("teamId"));
            Member editMember = memberDao.findId(idMember);
            model.put("editMember", true);
            model.put("idTeam", idTeam);
            List<Member> allMembers = memberDao.getAll();
            List<Bouquet> allTeams = bouquetDao.getAll();
            model.put("idMember", idMember);
            model.put("members", allMembers);
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        ///..post update member form..///
        post("/teams/:id/members/:teamId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int idMember = Integer.parseInt(request.params("id"));
            int idTeam = Integer.parseInt(request.params("teamId"));
            Member editMember = memberDao.findId(idMember);
            memberDao.update(idMember, newName, idTeam);
            model.put("idMember", idMember);
            model.put("idTeam", idTeam);
            return new ModelAndView(model, "success_2.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
