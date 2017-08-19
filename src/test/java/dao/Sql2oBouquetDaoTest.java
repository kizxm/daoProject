package dao;
import models.Member;
import models.Bouquet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Sql2oBouquetDaoTest {

    private Sql2oBouquetDao bouquetDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    ///-------------------------------------///
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        bouquetDao = new Sql2oBouquetDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Bouquet bouquetTeam1() {
        return new Bouquet("Good Guys", "Doing good for those with less");
    }

    public Bouquet bouquetTeam2() {
        return new Bouquet("Bad Guys", "Doing Bad just for the heck of it");
    }
    ///-------------------------------------///

    @Test
    public void addMethodReturnsTheSame_False() throws Exception {
        Bouquet team1 = bouquetTeam1();
        int first = team1.getId();
        bouquetDao.add(team1);
        assertNotEquals(first, team1.getId());
    }
    @Test
    public void getAllFromBouquetsEquals_1() throws Exception {
        Bouquet team1 = bouquetTeam1();
        bouquetDao.add(team1);
        assertEquals(1, bouquetDao.getAll().size());
    }
    @Test
    public void bouquetsFoundByIdReturn_True() throws Exception {
        Bouquet team1 = bouquetTeam1();
        bouquetDao.add(team1);
        Bouquet first = bouquetDao.findByBouquetId(team1.getId());
        assertEquals(team1, first);
    }
    @Test
    public void emptyFieldsReturn_True() throws Exception {
        assertEquals(0, bouquetDao.getAll().size());
    }
    @Test
    public void updateMethodUpdates_Correctly() throws Exception {
        String firstName = "Bob's Team";
        String firstDesc = "This is bob's team";
        Bouquet team1 = new Bouquet(firstName, firstDesc);
        bouquetDao.add(team1);

        bouquetDao.update(team1.getId(), "John's Team", "John made a team too");
        Bouquet secondTeam = bouquetDao.findByBouquetId(team1.getId());
        assertNotEquals(firstName, secondTeam.getTeamName());
    }
    @Test
    public void deleteByBouquetIdDeletes_True() throws Exception {
        Bouquet team1 = bouquetTeam1();
        bouquetDao.add(team1);
        bouquetDao.deleteByBouquetId(team1.getId());
        assertEquals(1, bouquetDao.getAll().size());
    }


}

