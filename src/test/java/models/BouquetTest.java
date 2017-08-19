package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BouquetTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Bouquet newArrangement() {
        return new Bouquet("rose", "lily", "daisy", "gerbera");
    }

    public Bouquet newArrangement2() {
        return new Bouquet("sunflower", "dandelion", "poppy", "thistle");
    }

    @Test
    public void instantiatesCorrectly() throws Exception {
        Bouquet newFlower = newArrangement();
        assertEquals(true, newFlower instanceof Bouquet);
    }

    @Test
    public void countsBouquetInstancesCorrectly() throws Exception {
        Bouquet.clearAllFlowers();
        Bouquet newFlower = newArrangement();
        Bouquet newFlower2 = newArrangement2();
        assertEquals(2, Bouquet.getAllFlowers().size());
    }

    @Test
    public void bouquetInstantiatesWithValue_True() throws Exception {
        Bouquet.clearAllFlowers();
        Bouquet newFlower = newArrangement();
        assertEquals("rose", newFlower.getFlower1());
    }

    @Test
    public void allFlowersContainsAllFlowers_True() throws Exception {
        Bouquet newFlower = newArrangement();
        Bouquet newFlower2 = newArrangement2();
        assertTrue(Bouquet.getAll().contains(newFlower));
        assertTrue(Bouquet.getAll().contains(newFlower2));
    }
    @Test
    public void updateChangesPostContent() throws Exception {
        Bouquet newFlower = newArrangement();
        String oldContent = newFlower.getFlower1();
        int formerId = newFlower.getId();
        newFlower.update("tulip", "willow", "dandelion", "none");
        assertEquals(formerId, newFlower.getId());
        assertNotEquals(oldContent, newFlower.getFlower1());
    }
}
