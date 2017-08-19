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
}

