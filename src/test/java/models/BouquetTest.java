package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Team newArrangement() {
        return new Team("rose", "lily", "harrison, alice, bob");
    }

    public Team newArrangement2() {
        return new Team("sunflower", "dandelion", "george, kim, bob");
    }

    @Test
    public void instantiatesCorrectly() throws Exception {
        Team newFlower = newArrangement();
        assertEquals(true, newFlower instanceof Team);
    }

    @Test
    public void countsBouquetInstancesCorrectly() throws Exception {
        Team.clearAllFlowers();
        Team newFlower = newArrangement();
        Team newFlower2 = newArrangement2();
        assertEquals(2, Team.getAllFlowers().size());
    }

    @Test
    public void bouquetInstantiatesWithValue_True() throws Exception {
        Team.clearAllFlowers();
        Team newFlower = newArrangement();
        assertEquals("rose", newFlower.getteamName());
    }

    @Test
    public void allFlowersContainsAllFlowers_True() throws Exception {
        Team newFlower = newArrangement();
        Team newFlower2 = newArrangement2();
        assertTrue(Team.getAll().contains(newFlower));
        assertTrue(Team.getAll().contains(newFlower2));
    }
    @Test
    public void updateChangesPostContent() throws Exception {
        Team newFlower = newArrangement();
        String oldContent = newFlower.getteamName();
        int formerId = newFlower.getId();
        newFlower.update("tulip", "willow", "john, smith, kat");
        assertEquals(formerId, newFlower.getId());
        assertNotEquals(oldContent, newFlower.getteamName());
    }
}


