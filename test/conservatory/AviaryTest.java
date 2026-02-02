package conservatory;

import birds.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Aviary class.
 * Tests cover aviary creation, capacity limits, and bird mixing rules.
 */
public class AviaryTest {

    private Aviary aviary;
    private Bird hawk;
    private Bird eagle;
    private Bird duck;
    private Bird swan;
    private Bird parrot;
    private Bird owl;
    private Bird pigeon;
    private Bird emu;
    private Bird moa; // extinct
    private Shorebird puffin;

    @Before
    public void setUp() {
        aviary = new Aviary(1, "North Wing - Section A");

        List<Food> preyFood = Arrays.asList(Food.SMALL_MAMMALS, Food.OTHER_BIRDS);
        List<Food> waterfowlFood = Arrays.asList(Food.VEGETATION, Food.AQUATIC_INVERTEBRATES);
        List<Food> parrotFood = Arrays.asList(Food.SEEDS, Food.NUTS, Food.FRUIT);
        List<Food> generalFood = Arrays.asList(Food.SEEDS, Food.INSECTS);

        hawk = new BirdOfPrey(BirdType.HAWK, "Sharp hooked beak", false, 2, preyFood);
        eagle = new BirdOfPrey(BirdType.EAGLE, "Powerful talons", false, 2, preyFood);
        duck = new Waterfowl(BirdType.DUCK, "Waterproof feathers", false, 2, waterfowlFood, "Lake");
        swan = new Waterfowl(BirdType.SWAN, "Long graceful neck", false, 2, waterfowlFood, "Pond");
        parrot = new Parrot(BirdType.GRAY_PARROT, "Intelligent", false, 2, parrotFood, 50, "Hello!");
        owl = new Owl(BirdType.OWL, "Facial disks", false, 2, generalFood);
        pigeon = new Pigeon(BirdType.PIGEON, "Produces bird milk", false, 2, generalFood);
        emu = new FlightlessBird(BirdType.EMU, "Large flightless", false, 0, generalFood);
        moa = new FlightlessBird(BirdType.MOA, "Giant extinct", true, 0, generalFood);
        puffin = new Shorebird(BirdType.HORNED_PUFFIN, "Colorful beak", false, 2,
                Arrays.asList(Food.FISH, Food.AQUATIC_INVERTEBRATES), "Pacific Ocean");
    }

    // ==========================================================================
    // Aviary Creation Tests
    // ==========================================================================

    @Test
    public void testCreateValidAviary() {
        Aviary av = new Aviary(1, "Test Location");
        assertEquals(1, av.getId());
        assertEquals("Test Location", av.getLocation());
        assertTrue(av.isEmpty());
        assertFalse(av.isFull());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAviaryWithNullLocation() {
        new Aviary(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAviaryWithEmptyLocation() {
        new Aviary(1, "  ");
    }

    // ==========================================================================
    // Adding Birds to Empty Aviary
    // ==========================================================================

    @Test
    public void testAddBirdToEmptyAviary() {
        assertTrue(aviary.canAddBird(duck));
        aviary.addBird(duck);
        assertFalse(aviary.isEmpty());
        assertEquals(1, aviary.getBirds().size());
    }

    @Test
    public void testAddBirdOfPreyToEmptyAviary() {
        assertTrue(aviary.canAddBird(hawk));
        aviary.addBird(hawk);
        assertEquals("Birds of Prey", aviary.getClassificationType());
    }

    @Test
    public void testAddFlightlessBirdToEmptyAviary() {
        assertTrue(aviary.canAddBird(emu));
        aviary.addBird(emu);
        assertEquals("Flightless Birds", aviary.getClassificationType());
    }

    @Test
    public void testAddOwlToEmptyAviary() {
        assertTrue(aviary.canAddBird(owl));
        aviary.addBird(owl);
        assertEquals("Owls", aviary.getClassificationType());
    }

    // ==========================================================================
    // Extinct Bird Tests
    // ==========================================================================

    @Test
    public void testCannotAddExtinctBird() {
        assertFalse(aviary.canAddBird(moa));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddExtinctBirdThrows() {
        aviary.addBird(moa);
    }

    @Test
    public void testCannotAddExtinctShorebirdToEmptyAviary() {
        Shorebird greatAuk = new Shorebird(BirdType.GREAT_AUK, "Extinct auk", true, 0,
                Arrays.asList(Food.FISH, Food.AQUATIC_INVERTEBRATES), "Atlantic");
        assertFalse(aviary.canAddBird(greatAuk));
    }

    // ==========================================================================
    // Aviary Capacity Tests
    // ==========================================================================

    @Test
    public void testAviaryCapacity() {
        List<Food> food = Arrays.asList(Food.SEEDS, Food.FRUIT);

        for (int i = 0; i < 5; i++) {
            Pigeon p = new Pigeon(BirdType.PIGEON, "Pigeon " + i, false, 2, food);
            assertTrue(aviary.canAddBird(p));
            aviary.addBird(p);
        }

        assertTrue(aviary.isFull());
        assertEquals(5, aviary.getBirds().size());
    }

    @Test
    public void testCannotExceedCapacity() {
        List<Food> food = Arrays.asList(Food.SEEDS, Food.FRUIT);

        for (int i = 0; i < 5; i++) {
            aviary.addBird(new Pigeon(BirdType.PIGEON, "Pigeon " + i, false, 2, food));
        }

        Pigeon extraPigeon = new Pigeon(BirdType.DOVE, "Extra", false, 2, food);
        assertFalse(aviary.canAddBird(extraPigeon));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddToFullAviaryThrows() {
        List<Food> food = Arrays.asList(Food.SEEDS, Food.FRUIT);

        for (int i = 0; i < 5; i++) {
            aviary.addBird(new Pigeon(BirdType.PIGEON, "Pigeon " + i, false, 2, food));
        }

        aviary.addBird(new Pigeon(BirdType.DOVE, "Extra", false, 2, food));
    }

    // ==========================================================================
    // Bird Mixing Rules - Restricted Types
    // ==========================================================================

    @Test
    public void testBirdsOfPreyCanMixTogether() {
        aviary.addBird(hawk);
        assertTrue(aviary.canAddBird(eagle));
        aviary.addBird(eagle);
        assertEquals(2, aviary.getBirds().size());
    }

    @Test
    public void testBirdOfPreyCannotMixWithOthers() {
        aviary.addBird(hawk);
        assertFalse(aviary.canAddBird(pigeon));
        assertFalse(aviary.canAddBird(owl));
        assertFalse(aviary.canAddBird(duck));
        assertFalse(aviary.canAddBird(parrot));
    }

    @Test
    public void testFlightlessBirdsCanMixTogether() {
        aviary.addBird(emu);
        Bird kiwi = new FlightlessBird(BirdType.KIWI, "Small flightless", false, 0,
                Arrays.asList(Food.INSECTS, Food.LARVAE));
        assertTrue(aviary.canAddBird(kiwi));
    }

    @Test
    public void testFlightlessBirdCannotMixWithOthers() {
        aviary.addBird(emu);
        assertFalse(aviary.canAddBird(pigeon));
        assertFalse(aviary.canAddBird(owl));
        assertFalse(aviary.canAddBird(hawk));
    }

    @Test
    public void testWaterfowlCanMixTogether() {
        aviary.addBird(duck);
        assertTrue(aviary.canAddBird(swan));
        aviary.addBird(swan);
        assertEquals(2, aviary.getBirds().size());
    }

    @Test
    public void testWaterfowlCannotMixWithOthers() {
        aviary.addBird(duck);
        assertFalse(aviary.canAddBird(pigeon));
        assertFalse(aviary.canAddBird(owl));
        assertFalse(aviary.canAddBird(hawk));
    }

    // ==========================================================================
    // Bird Mixing Rules - Non-Restricted Types
    // ==========================================================================

    @Test
    public void testOwlsCanMixWithPigeons() {
        aviary.addBird(owl);
        assertTrue(aviary.canAddBird(pigeon));
        aviary.addBird(pigeon);
        assertEquals(2, aviary.getBirds().size());
    }

    @Test
    public void testOwlsCanMixWithParrots() {
        aviary.addBird(owl);
        assertTrue(aviary.canAddBird(parrot));
    }

    @Test
    public void testParrotsCanMixWithPigeons() {
        aviary.addBird(parrot);
        assertTrue(aviary.canAddBird(pigeon));
    }

    @Test
    public void testShorebirdsCanMixWithPigeons() {
        aviary.addBird(puffin);
        assertTrue(aviary.canAddBird(pigeon));
        aviary.addBird(pigeon);
        assertEquals(2, aviary.getBirds().size());
    }

    @Test
    public void testShorebirdsCanMixWithOwls() {
        aviary.addBird(puffin);
        assertTrue(aviary.canAddBird(owl));
    }

    @Test
    public void testShorebirdsCanMixWithParrots() {
        aviary.addBird(puffin);
        assertTrue(aviary.canAddBird(parrot));
    }

    @Test
    public void testCannotAddRestrictedToNonRestricted() {
        aviary.addBird(pigeon);
        assertFalse(aviary.canAddBird(hawk));
        assertFalse(aviary.canAddBird(emu));
        assertFalse(aviary.canAddBird(duck));
    }

    // ==========================================================================
    // Aviary Helper Methods
    // ==========================================================================

    @Test
    public void testHasBird() {
        aviary.addBird(duck);
        assertTrue(aviary.hasBird(duck));
        assertFalse(aviary.hasBird(swan));
    }

    @Test
    public void testGetBirdsDefensiveCopy() {
        aviary.addBird(duck);
        List<Bird> birds = aviary.getBirds();
        birds.add(swan); // Modify returned list

        assertEquals(1, aviary.getBirds().size()); // Original unchanged
    }

    @Test
    public void testEmptyAviaryHasNullClassification() {
        assertNull(aviary.getClassificationType());
    }

    @Test
    public void testCannotAddNullBird() {
        assertFalse(aviary.canAddBird(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullBirdThrows() {
        aviary.addBird(null);
    }

    // ==========================================================================
    // Aviary Sign Tests
    // ==========================================================================

    @Test
    public void testEmptyAviarySign() {
        String sign = aviary.getSign();
        assertTrue(sign.contains("Aviary 1"));
        assertTrue(sign.contains("North Wing - Section A"));
        assertTrue(sign.contains("empty"));
    }

    @Test
    public void testAviarySignWithBirds() {
        aviary.addBird(duck);
        aviary.addBird(swan);
        String sign = aviary.getSign();

        assertTrue(sign.contains("Aviary 1"));
        assertTrue(sign.contains("Duck"));
        assertTrue(sign.contains("Swan"));
    }

    @Test
    public void testAviaryToString() {
        aviary.addBird(duck);
        String str = aviary.toString();

        assertTrue(str.contains("Aviary 1"));
        assertTrue(str.contains("1/5 birds"));
    }
}
