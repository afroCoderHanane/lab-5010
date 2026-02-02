package birds;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Bird class hierarchy.
 * Tests cover bird creation, validation, and various bird type behaviors.
 */
public class BirdTest {

    private List<Food> validFood;
    private List<Food> tooFewFood;
    private List<Food> tooManyFood;

    @Before
    public void setUp() {
        validFood = Arrays.asList(Food.FISH, Food.SMALL_MAMMALS, Food.OTHER_BIRDS);
        tooFewFood = Arrays.asList(Food.FISH);
        tooManyFood = Arrays.asList(Food.FISH, Food.SEEDS, Food.BERRIES, Food.INSECTS, Food.EGGS);
    }

    // ==========================================================================
    // Bird of Prey Tests
    // ==========================================================================

    @Test
    public void testCreateValidBirdOfPrey() {
        Bird hawk = new BirdOfPrey(BirdType.HAWK, "Sharp hooked beak with visible nostrils",
                false, 2, validFood);
        assertEquals(BirdType.HAWK, hawk.getType());
        assertEquals("Sharp hooked beak with visible nostrils", hawk.getDefiningCharacteristic());
        assertFalse(hawk.isExtinct());
        assertEquals(2, hawk.getNumberOfWings());
        assertEquals("Birds of Prey", hawk.getClassification());
    }

    @Test
    public void testCreateEagle() {
        Bird eagle = new BirdOfPrey(BirdType.EAGLE, "Powerful talons and excellent eyesight",
                false, 2, Arrays.asList(Food.FISH, Food.SMALL_MAMMALS));
        assertEquals(BirdType.EAGLE, eagle.getType());
        assertEquals("Birds of Prey", eagle.getClassification());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithTooFewFood() {
        new BirdOfPrey(BirdType.OSPREY, "Sharp beak", false, 2, tooFewFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithTooManyFood() {
        new BirdOfPrey(BirdType.OSPREY, "Sharp beak", false, 2, tooManyFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithNullType() {
        new BirdOfPrey(null, "Sharp beak", false, 2, validFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithNullCharacteristic() {
        new BirdOfPrey(BirdType.HAWK, null, false, 2, validFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithEmptyCharacteristic() {
        new BirdOfPrey(BirdType.HAWK, "  ", false, 2, validFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithNegativeWings() {
        new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, -1, validFood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBirdOfPreyWithNullFood() {
        new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, null);
    }

    // ==========================================================================
    // Flightless Bird Tests
    // ==========================================================================

    @Test
    public void testCreateValidFlightlessBird() {
        Bird emu = new FlightlessBird(BirdType.EMU, "Large flightless bird from Australia",
                false, 0, Arrays.asList(Food.SEEDS, Food.FRUIT, Food.INSECTS));
        assertEquals(BirdType.EMU, emu.getType());
        assertEquals(0, emu.getNumberOfWings());
        assertEquals("Flightless Birds", emu.getClassification());
    }

    @Test
    public void testCreateExtinctFlightlessBird() {
        Bird moa = new FlightlessBird(BirdType.MOA, "Giant extinct bird from New Zealand",
                true, 0, Arrays.asList(Food.VEGETATION, Food.FRUIT));
        assertTrue(moa.isExtinct());
        assertEquals("Flightless Birds", moa.getClassification());
    }

    @Test
    public void testCreateKiwi() {
        Bird kiwi = new FlightlessBird(BirdType.KIWI, "Small flightless bird with hair-like feathers",
                false, 0, Arrays.asList(Food.INSECTS, Food.LARVAE, Food.BERRIES));
        assertEquals(BirdType.KIWI, kiwi.getType());
        assertFalse(kiwi.isExtinct());
    }

    // ==========================================================================
    // Owl Tests
    // ==========================================================================

    @Test
    public void testCreateValidOwl() {
        Bird owl = new Owl(BirdType.OWL, "Facial disks that frame the eyes and bill",
                false, 2, Arrays.asList(Food.SMALL_MAMMALS, Food.INSECTS));
        assertEquals(BirdType.OWL, owl.getType());
        assertEquals("Owls", owl.getClassification());
    }

    // ==========================================================================
    // Parrot Tests
    // ==========================================================================

    @Test
    public void testCreateValidParrot() {
        Parrot parrot = new Parrot(BirdType.GRAY_PARROT, "Short curved beak, highly intelligent",
                false, 2, Arrays.asList(Food.SEEDS, Food.NUTS, Food.FRUIT), 50, "Hello there!");
        assertEquals(BirdType.GRAY_PARROT, parrot.getType());
        assertEquals(50, parrot.getVocabularySize());
        assertEquals("Hello there!", parrot.getFavoriteSaying());
        assertEquals("Parrots", parrot.getClassification());
    }

    @Test
    public void testCreateParrotWithMaxVocabulary() {
        Parrot parrot = new Parrot(BirdType.SULFUR_CRESTED_COCKATOO, "White feathers with yellow crest",
                false, 2, Arrays.asList(Food.SEEDS, Food.NUTS), 100, "Polly wants a cracker!");
        assertEquals(100, parrot.getVocabularySize());
    }

    @Test
    public void testCreateParrotWithZeroVocabulary() {
        Parrot parrot = new Parrot(BirdType.ROSE_RING_PARAKEET, "Green body with rose ring",
                false, 2, Arrays.asList(Food.SEEDS, Food.FRUIT), 0, "");
        assertEquals(0, parrot.getVocabularySize());
        assertEquals("", parrot.getFavoriteSaying());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParrotWithNegativeVocabulary() {
        new Parrot(BirdType.GRAY_PARROT, "Smart bird", false, 2, validFood, -1, "Hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParrotWithVocabularyOver100() {
        new Parrot(BirdType.GRAY_PARROT, "Smart bird", false, 2, validFood, 150, "Hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParrotWithNullFavoriteSaying() {
        new Parrot(BirdType.GRAY_PARROT, "Smart bird", false, 2, validFood, 50, null);
    }

    // ==========================================================================
    // Pigeon Tests
    // ==========================================================================

    @Test
    public void testCreateValidPigeon() {
        Bird pigeon = new Pigeon(BirdType.PIGEON, "Produces crop milk to feed young",
                false, 2, Arrays.asList(Food.SEEDS, Food.BERRIES));
        assertEquals(BirdType.PIGEON, pigeon.getType());
        assertEquals("Pigeons", pigeon.getClassification());
    }

    @Test
    public void testCreateDove() {
        Bird dove = new Pigeon(BirdType.DOVE, "Symbol of peace, produces bird milk",
                false, 2, Arrays.asList(Food.SEEDS, Food.BERRIES, Food.FRUIT));
        assertEquals(BirdType.DOVE, dove.getType());
        assertEquals("Pigeons", dove.getClassification());
    }

    // ==========================================================================
    // Shorebird Tests
    // ==========================================================================

    @Test
    public void testCreateValidShorebird() {
        Shorebird puffin = new Shorebird(BirdType.HORNED_PUFFIN, "Colorful beak, excellent swimmer",
                false, 2, Arrays.asList(Food.FISH, Food.AQUATIC_INVERTEBRATES), "Pacific Ocean");
        assertEquals(BirdType.HORNED_PUFFIN, puffin.getType());
        assertEquals("Pacific Ocean", puffin.getBodyOfWater());
        assertEquals("Shorebirds", puffin.getClassification());
    }

    @Test
    public void testCreateExtinctShorebird() {
        Shorebird greatAuk = new Shorebird(BirdType.GREAT_AUK, "Large flightless auk",
                true, 0, Arrays.asList(Food.FISH, Food.AQUATIC_INVERTEBRATES), "North Atlantic");
        assertTrue(greatAuk.isExtinct());
        assertEquals("North Atlantic", greatAuk.getBodyOfWater());
    }

    @Test
    public void testCreateAfricanJacana() {
        Shorebird jacana = new Shorebird(BirdType.AFRICAN_JACANA, "Long toes for walking on lily pads",
                false, 2, Arrays.asList(Food.INSECTS, Food.AQUATIC_INVERTEBRATES, Food.SEEDS), "Lake Victoria");
        assertEquals(BirdType.AFRICAN_JACANA, jacana.getType());
        assertEquals("Lake Victoria", jacana.getBodyOfWater());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShorebirdWithNullWater() {
        new Shorebird(BirdType.HORNED_PUFFIN, "Puffin", false, 2, validFood, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShorebirdWithEmptyWater() {
        new Shorebird(BirdType.HORNED_PUFFIN, "Puffin", false, 2, validFood, "  ");
    }

    // ==========================================================================
    // Waterfowl Tests
    // ==========================================================================

    @Test
    public void testCreateValidWaterfowl() {
        Waterfowl duck = new Waterfowl(BirdType.DUCK, "Waterproof feathers, webbed feet",
                false, 2, Arrays.asList(Food.AQUATIC_INVERTEBRATES, Food.VEGETATION), "Lake Michigan");
        assertEquals(BirdType.DUCK, duck.getType());
        assertEquals("Lake Michigan", duck.getBodyOfWater());
        assertEquals("Waterfowl", duck.getClassification());
    }

    @Test
    public void testCreateSwan() {
        Waterfowl swan = new Waterfowl(BirdType.SWAN, "Long graceful neck, elegant swimmer",
                false, 2, Arrays.asList(Food.VEGETATION, Food.AQUATIC_INVERTEBRATES), "Swan Lake");
        assertEquals(BirdType.SWAN, swan.getType());
        assertEquals("Swan Lake", swan.getBodyOfWater());
    }

    @Test
    public void testCreateGoose() {
        Waterfowl goose = new Waterfowl(BirdType.GOOSE, "Migratory bird, V-formation flyer",
                false, 2, Arrays.asList(Food.VEGETATION, Food.SEEDS, Food.BERRIES), "Hudson River");
        assertEquals(BirdType.GOOSE, goose.getType());
        assertEquals("Hudson River", goose.getBodyOfWater());
    }

    // ==========================================================================
    // General Bird Tests
    // ==========================================================================

    @Test
    public void testPreferredFoodDefensiveCopy() {
        Bird hawk = new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, validFood);
        List<Food> foods = hawk.getPreferredFood();
        foods.add(Food.EGGS); // Modify the returned list

        // Original should be unchanged
        assertEquals(3, hawk.getPreferredFood().size());
    }

    @Test
    public void testBirdToString() {
        Bird hawk = new BirdOfPrey(BirdType.HAWK, "Sharp hooked beak", false, 2,
                Arrays.asList(Food.FISH, Food.SMALL_MAMMALS));
        String str = hawk.toString();

        assertTrue(str.contains("Hawk"));
        assertTrue(str.contains("Birds of Prey"));
        assertTrue(str.contains("Sharp hooked beak"));
        assertTrue(str.contains("Wings: 2"));
        assertTrue(str.contains("fish"));
        assertTrue(str.contains("small mammals"));
    }

    @Test
    public void testExtinctBirdToString() {
        Bird moa = new FlightlessBird(BirdType.MOA, "Giant bird", true, 0,
                Arrays.asList(Food.VEGETATION, Food.FRUIT));
        String str = moa.toString();

        assertTrue(str.contains("[EXTINCT]"));
    }

    @Test
    public void testParrotToString() {
        Parrot parrot = new Parrot(BirdType.GRAY_PARROT, "Intelligent", false, 2,
                Arrays.asList(Food.SEEDS, Food.NUTS), 75, "Hello friend!");
        String str = parrot.toString();

        assertTrue(str.contains("Gray Parrot"));
        assertTrue(str.contains("75 words"));
        assertTrue(str.contains("Hello friend!"));
    }

    @Test
    public void testWaterBirdToString() {
        Waterfowl duck = new Waterfowl(BirdType.DUCK, "Waterproof", false, 2,
                Arrays.asList(Food.FISH, Food.VEGETATION), "Central Park Pond");
        String str = duck.toString();

        assertTrue(str.contains("Central Park Pond"));
    }

    @Test
    public void testBirdEquality() {
        Bird hawk1 = new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, validFood);
        Bird hawk2 = new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, validFood);
        Bird eagle = new BirdOfPrey(BirdType.EAGLE, "Sharp beak", false, 2, validFood);

        assertEquals(hawk1, hawk2);
        assertNotEquals(hawk1, eagle);
    }

    @Test
    public void testBirdHashCode() {
        Bird hawk1 = new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, validFood);
        Bird hawk2 = new BirdOfPrey(BirdType.HAWK, "Sharp beak", false, 2, validFood);

        assertEquals(hawk1.hashCode(), hawk2.hashCode());
    }
}
