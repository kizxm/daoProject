package dao;

import models.Bouquet;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
}
