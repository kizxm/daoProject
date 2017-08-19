package dao;

import org.sql2o.Sql2o;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(models.Member member) {
        String sql = "INSERT INTO members (name, contact, memId) VALUES (:name, :contact, :memId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", member.getName())
                    .addParameter("contact", member.getContact())
                    .addParameter("memId", member.getMemId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("CONTACT", "contact")
                    .addColumnMapping("MEMID", "memId")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members2")
                    .executeAndFetch(Member.class);
        }
    }

}
