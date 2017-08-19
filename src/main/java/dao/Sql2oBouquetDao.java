package dao;

import models.Bouquet;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oBouquetDao implements BouquetDao{
    private final Sql2o sql2o;
    public Sql2oBouquetDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Bouquet bouquet) {
        String sql = "INSERT INTO bouquets (teamName, teamDesc) VALUES (:teamName, :teamDesc)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("teamName", bouquet.getTeamName())
                    .addParameter("teamDesc", bouquet.getTeamDesc())
                    .addColumnMapping("TEAMNAME", "teamName")
                    .addColumnMapping("TEAMDESC", "teamDesc")
                    .executeUpdate()
                    .getKey();
            bouquet.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public List<Member> getAllBouquetMembers(int memId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members WHERE memId = :memId")
                    .addParameter("memId", memId)
                    .executeAndFetch(Member.class);
        }
    }
    @Override
    public List<Bouquet> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM bouquets")
                    .executeAndFetch(Bouquet.class);
        }
    }
    @Override
    public Bouquet findByBouquetId(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM bouquets WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Bouquet.class);
        }
    }
    @Override
    public void update(int id, String updateName, String updateDesc) {
        String sql = "UPDATE bouquets SET teamName = :teamName, teamDesc = :teamDesc WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("teamName", updateName)
                    .addParameter("teamDesc", updateDesc)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
