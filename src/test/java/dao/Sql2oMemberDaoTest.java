package dao;
import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;
///-------------------------------------///
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Member testMember1() { return new Member("Hanson Test", 1);}
    public Member testMember2() { return new Member("Aaron Smith", 2);}
///-------------------------------------///

    @Test
    public void idInstantiates_True() throws Exception {
        Member member1 = testMember1();
        int first = member1.getId();
        memberDao.add(member1);
        assertNotEquals(first, member1.getId());
    }

//    @Test
//    public void getAllAddsMembers_1() throws Exception {
//        Member member = testMember1();
//        memberDao.add(member);
//        assertEquals(2, memberDao.getAll().size());
//    }
}