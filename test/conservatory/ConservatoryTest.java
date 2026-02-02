package conservatory;

import birds.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Conservatory class.
 * Tests cover rescuing birds, assigning to aviaries, food calculation,
 * bird lookup, and printing maps/indexes.
 */
public class ConservatoryTest {

    private Conservatory conservatory;
    private Bird hawk;
    private Bird eagle;
    private Bird duck;
    private Bird swan;
    private Bird goose;
    private Parrot parrot;
    private Bird owl;
    private Bird pigeon;
    private Bird dove;
    private Bird emu;
    private Bird moa;
    private Shorebird puffin;

    @Before
    public void setUp() {
        conservatory = new Conservatory();

        List<Food> preyFood = Arrays.asList(Food.SMALL_MAMMALS, Food.OTHER_BIRDS);
        List<Food> waterfowlFood = Arrays.asList(Food.VEGETATION, Food.AQUATIC_INVERTEBRATES);
        List<Food> parrotFood = Arrays.asList(Food.SEEDS, Food.NUTS, Food.FRUIT);
        List<Food> generalFood = Arrays.asList(Food.SEEDS, Food.INSECTS);
        List<Food> pigeonFood = Arrays.asList(Food.SEEDS, Food.BERRIES);

        hawk = new BirdOfPrey(BirdType.HAWK, "Sharp hooked beak", false, 2, preyFood);
        eagle = new BirdOfPrey(BirdType.EAGLE, "Powerful talons", false, 2, preyFood);
        duck = new Waterfowl(BirdType.DUCK, "Waterproof feathers", false, 2, waterfowlFood, "Lake Michigan");
        swan = new Waterfowl(BirdType.SWAN, "Long graceful neck", false, 2, waterfowlFood, "Swan Lake");
        goose = new Waterfowl(BirdType.GOOSE, "Migratory bird", false, 2, waterfowlFood, "Hudson River");
        parrot = new Parrot(BirdType.GRAY_PARROT, "Intelligent", false, 2, parrotFood, 50, "Hello!");
        owl = new Owl(BirdType.OWL, "Facial disks", false, 2, generalFood);
        pigeon = new Pigeon(BirdType.PIGEON, "Produces bird milk", false, 2, pigeonFood);
        dove = new Pigeon(BirdType.DOVE, "Symbol of peace", false, 2, pigeonFood);
        emu = new FlightlessBird(BirdType.EMU, "Large flightless", false, 0, generalFood);
        moa = new FlightlessBird(BirdType.MOA, "Giant extinct", true, 0, generalFood);
        puffin = new Shorebird(BirdType.HORNED_PUFFIN, "Colorful beak", false, 2,
                Arrays.asList(Food.FISH, Food.AQUATIC_INVERTEBRATES), "Pacific Ocean");
    }

    // ==========================================================================
    // Rescue Bird Tests
    // ==========================================================================

    @Test
    public void testRescueBird() {
        conservatory.rescueBird(duck);
        assertEquals(1, conservatory.getRescuedBirds().size());
        assertTrue(conservatory.getRescuedBirds().contains(duck));
    }

    @Test
    public void testRescueMultipleBirds() {
        conservatory.rescueBird(duck);
        conservatory.rescueBird(hawk);
        conservatory.rescueBird(parrot);
        assertEquals(3, conservatory.getRescuedBirds().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRescueNullBird() {
        conservatory.rescueBird(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testRescueSameBirdTwice() {
        conservatory.rescueBird(duck);
        conservatory.rescueBird(duck);
    }

    // ==========================================================================
    // Assign Bird to Aviary Tests
    // ==========================================================================

    @Test
    public void testAssignBirdCreatesNewAviary() {
        String result = conservatory.assignBirdToAviary(duck);
        assertTrue(result.contains("Duck"));
        assertTrue(result.contains("Aviary 1"));
        assertEquals(1, conservatory.getAviaries().size());
    }

    @Test
    public void testAssignCompatibleBirdsToSameAviary() {
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(swan);

        assertEquals(1, conservatory.getAviaries().size());
        Aviary aviary = conservatory.getAviaries().get(0);
        assertEquals(2, aviary.getBirds().size());
    }

    @Test
    public void testAssignIncompatibleBirdsToSeparateAviaries() {
        conservatory.assignBirdToAviary(hawk);
        conservatory.assignBirdToAviary(duck);

        assertEquals(2, conservatory.getAviaries().size());
    }

    @Test
    public void testAssignBirdOfPreyToSameAviary() {
        conservatory.assignBirdToAviary(hawk);
        conservatory.assignBirdToAviary(eagle);

        assertEquals(1, conservatory.getAviaries().size());
    }

    @Test
    public void testAssignMixableNonRestrictedBirds() {
        conservatory.assignBirdToAviary(owl);
        conservatory.assignBirdToAviary(pigeon);
        conservatory.assignBirdToAviary(parrot);

        assertEquals(1, conservatory.getAviaries().size());
        assertEquals(3, conservatory.getAviaries().get(0).getBirds().size());
    }

    @Test
    public void testAssignShorebirdWithMixableBirds() {
        conservatory.assignBirdToAviary(puffin);
        conservatory.assignBirdToAviary(owl);

        assertEquals(1, conservatory.getAviaries().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAssignExtinctBird() {
        conservatory.assignBirdToAviary(moa);
    }

    @Test
    public void testAssignNonExtinctFlightlessBird() {
        String result = conservatory.assignBirdToAviary(emu);
        assertTrue(result.contains("Emu"));
        assertEquals(1, conservatory.getAviaries().size());
    }

    @Test
    public void testAssignAlreadyAssignedBird() {
        conservatory.assignBirdToAviary(duck);
        String result = conservatory.assignBirdToAviary(duck);

        assertTrue(result.contains("already"));
        assertEquals(1, conservatory.getAviaries().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssignNullBird() {
        conservatory.assignBirdToAviary(null);
    }

    // ==========================================================================
    // Aviary Capacity Tests
    // ==========================================================================

    @Test
    public void testAviaryCreatedWhenFull() {
        // Fill first aviary with 5 waterfowl
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(swan);
        conservatory.assignBirdToAviary(goose);

        Waterfowl duck2 = new Waterfowl(BirdType.DUCK, "Duck 2", false, 2,
                Arrays.asList(Food.VEGETATION, Food.FISH), "River");
        Waterfowl swan2 = new Waterfowl(BirdType.SWAN, "Swan 2", false, 2,
                Arrays.asList(Food.VEGETATION, Food.FISH), "Pond");
        Waterfowl duck3 = new Waterfowl(BirdType.DUCK, "Duck 3", false, 2,
                Arrays.asList(Food.VEGETATION, Food.FISH), "Lake");

        conservatory.assignBirdToAviary(duck2);
        conservatory.assignBirdToAviary(swan2);

        // First aviary is now full (5 birds)
        assertEquals(1, conservatory.getAviaries().size());
        assertTrue(conservatory.getAviaries().get(0).isFull());

        // This should create a new aviary
        conservatory.assignBirdToAviary(duck3);
        assertEquals(2, conservatory.getAviaries().size());
    }

    // ==========================================================================
    // Maximum Aviaries Test
    // ==========================================================================

    @Test(expected = IllegalStateException.class)
    public void testMaximumAviariesReached() {
        List<Food> food = Arrays.asList(Food.SEEDS, Food.INSECTS);

        // Create 20 aviaries with birds of prey (each needs its own or can share)
        // Since birds of prey must be together, we'll use different types
        for (int i = 0; i < 20; i++) {
            BirdOfPrey bp = new BirdOfPrey(BirdType.HAWK, "Hawk " + i, false, 2, food);
            conservatory.assignBirdToAviary(bp);

            // Fill each aviary to capacity so next bird creates new aviary
            for (int j = 0; j < 4; j++) {
                bp = new BirdOfPrey(BirdType.EAGLE, "Eagle " + i + "-" + j, false, 2, food);
                conservatory.assignBirdToAviary(bp);
            }
        }

        // Now we have 20 full aviaries, trying to add should fail
        // Actually this creates new aviary which should fail
        Bird extraBird = new Parrot(BirdType.GRAY_PARROT, "Extra", false, 2, food, 10, "Hi");
        conservatory.assignBirdToAviary(extraBird);
    }

    // ==========================================================================
    // Calculate Food Quantities Tests
    // ==========================================================================

    @Test
    public void testCalculateFoodQuantitiesEmpty() {
        Map<Food, Integer> quantities = conservatory.calculateFoodQuantities();
        assertTrue(quantities.isEmpty());
    }

    @Test
    public void testCalculateFoodQuantitiesSingleBird() {
        conservatory.assignBirdToAviary(duck);
        Map<Food, Integer> quantities = conservatory.calculateFoodQuantities();

        assertEquals(Integer.valueOf(1), quantities.get(Food.VEGETATION));
        assertEquals(Integer.valueOf(1), quantities.get(Food.AQUATIC_INVERTEBRATES));
    }

    @Test
    public void testCalculateFoodQuantitiesMultipleBirds() {
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(swan);

        Map<Food, Integer> quantities = conservatory.calculateFoodQuantities();

        // Both ducks and swans eat vegetation and aquatic invertebrates
        assertEquals(Integer.valueOf(2), quantities.get(Food.VEGETATION));
        assertEquals(Integer.valueOf(2), quantities.get(Food.AQUATIC_INVERTEBRATES));
    }

    @Test
    public void testCalculateFoodQuantitiesOverlapping() {
        conservatory.assignBirdToAviary(pigeon); // SEEDS, BERRIES
        conservatory.assignBirdToAviary(dove); // SEEDS, BERRIES
        conservatory.assignBirdToAviary(parrot); // SEEDS, NUTS, FRUIT

        Map<Food, Integer> quantities = conservatory.calculateFoodQuantities();

        assertEquals(Integer.valueOf(3), quantities.get(Food.SEEDS));
        assertEquals(Integer.valueOf(2), quantities.get(Food.BERRIES));
        assertEquals(Integer.valueOf(1), quantities.get(Food.NUTS));
        assertEquals(Integer.valueOf(1), quantities.get(Food.FRUIT));
    }

    // ==========================================================================
    // Lookup Bird Tests
    // ==========================================================================

    @Test
    public void testLookupBirdInAviary() {
        conservatory.assignBirdToAviary(duck);
        String result = conservatory.lookupBird(duck);

        assertTrue(result.contains("Duck"));
        assertTrue(result.contains("Aviary 1"));
    }

    @Test
    public void testLookupBirdNotInAviary() {
        String result = conservatory.lookupBird(duck);
        assertTrue(result.contains("not found"));
    }

    @Test
    public void testLookupRescuedButNotAssigned() {
        conservatory.rescueBird(duck);
        String result = conservatory.lookupBird(duck);

        assertTrue(result.contains("rescued"));
        assertTrue(result.contains("not yet assigned"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLookupNullBird() {
        conservatory.lookupBird(null);
    }

    // ==========================================================================
    // Get Aviary Sign Tests
    // ==========================================================================

    @Test
    public void testGetAviarySign() {
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(swan);

        String sign = conservatory.getAviarySign(1);

        assertTrue(sign.contains("Aviary 1"));
        assertTrue(sign.contains("Duck"));
        assertTrue(sign.contains("Swan"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAviarySignInvalidId() {
        conservatory.getAviarySign(999);
    }

    // ==========================================================================
    // Print Map Tests
    // ==========================================================================

    @Test
    public void testPrintMapEmpty() {
        String map = conservatory.printMap();
        assertTrue(map.contains("CONSERVATORY MAP"));
        assertTrue(map.contains("No aviaries"));
    }

    @Test
    public void testPrintMapWithAviaries() {
        conservatory.assignBirdToAviary(hawk);
        conservatory.assignBirdToAviary(duck);

        String map = conservatory.printMap();

        assertTrue(map.contains("CONSERVATORY MAP"));
        assertTrue(map.contains("Aviary 1"));
        assertTrue(map.contains("Aviary 2"));
        assertTrue(map.contains("Hawk"));
        assertTrue(map.contains("Duck"));
    }

    // ==========================================================================
    // Print Index Tests
    // ==========================================================================

    @Test
    public void testPrintIndexEmpty() {
        String index = conservatory.printIndex();
        assertTrue(index.contains("BIRD INDEX"));
        assertTrue(index.contains("No birds"));
    }

    @Test
    public void testPrintIndexAlphabetical() {
        conservatory.assignBirdToAviary(hawk);
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(eagle);

        String index = conservatory.printIndex();

        assertTrue(index.contains("BIRD INDEX"));

        // Check alphabetical order: Duck, Eagle, Hawk
        int duckPos = index.indexOf("Duck");
        int eaglePos = index.indexOf("Eagle");
        int hawkPos = index.indexOf("Hawk");

        assertTrue(duckPos < eaglePos);
        assertTrue(eaglePos < hawkPos);
    }

    @Test
    public void testPrintIndexWithLocations() {
        conservatory.assignBirdToAviary(parrot);
        conservatory.assignBirdToAviary(owl);

        String index = conservatory.printIndex();

        assertTrue(index.contains("Gray Parrot"));
        assertTrue(index.contains("Owl"));
        assertTrue(index.contains("Aviary 1"));
    }

    // ==========================================================================
    // Integration Tests
    // ==========================================================================

    @Test
    public void testFullWorkflow() {
        // Rescue several birds
        conservatory.rescueBird(hawk);
        conservatory.rescueBird(eagle);
        conservatory.rescueBird(duck);
        conservatory.rescueBird(parrot);
        conservatory.rescueBird(owl);

        // Assign them to aviaries
        conservatory.assignBirdToAviary(hawk);
        conservatory.assignBirdToAviary(eagle);
        conservatory.assignBirdToAviary(duck);
        conservatory.assignBirdToAviary(parrot);
        conservatory.assignBirdToAviary(owl);

        // Hawk and Eagle should be in same aviary
        // Duck should be alone
        // Parrot and Owl can be together
        assertEquals(3, conservatory.getAviaries().size());

        // Check food requirements
        Map<Food, Integer> food = conservatory.calculateFoodQuantities();
        assertFalse(food.isEmpty());

        // Check map and index
        String map = conservatory.printMap();
        assertTrue(map.contains("Hawk"));
        assertTrue(map.contains("Duck"));

        String index = conservatory.printIndex();
        int duckIdx = index.indexOf("Duck");
        int hawkIdx = index.indexOf("Hawk");
        assertTrue(duckIdx < hawkIdx);
    }

    @Test
    public void testConservatoryToString() {
        conservatory.rescueBird(duck);
        conservatory.assignBirdToAviary(duck);

        String str = conservatory.toString();
        assertTrue(str.contains("1 aviaries"));
        assertTrue(str.contains("1 rescued"));
    }
}
