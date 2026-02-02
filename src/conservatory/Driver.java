package conservatory;

import birds.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Main driver class to demonstrate the Bird Conservatory system.
 */
public class Driver {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Welcome to the Bird Conservatory System");
        System.out.println("=================================================");

        // 1. Create the conservatory
        Conservatory conservatory = new Conservatory();
        System.out.println("Conservatory created.\n");

        // 2. Create some sample foods
        List<Food> preyFood = Arrays.asList(Food.SMALL_MAMMALS, Food.OTHER_BIRDS);
        List<Food> waterfowlFood = Arrays.asList(Food.VEGETATION, Food.AQUATIC_INVERTEBRATES);
        List<Food> parrotFood = Arrays.asList(Food.SEEDS, Food.NUTS, Food.FRUIT);

        // 3. Create birds
        Bird hawk = new BirdOfPrey(BirdType.HAWK, "Sharp hooked beak", false, 2, preyFood);
        Bird eagle = new BirdOfPrey(BirdType.EAGLE, "Powerful talons", false, 2, preyFood);
        Bird duck = new Waterfowl(BirdType.DUCK, "Waterproof feathers", false, 2, waterfowlFood, "Lake Michigan");
        Bird parrot = new Parrot(BirdType.GRAY_PARROT, "Intelligent", false, 2, parrotFood, 50,
                "Polly wants a cracker");
        Bird emu = new FlightlessBird(BirdType.EMU, "Large flightless", false, 0,
                Arrays.asList(Food.SEEDS, Food.FRUIT));
        Bird owl = new Owl(BirdType.OWL, "Silent flight", false, 2, Arrays.asList(Food.SMALL_MAMMALS, Food.INSECTS));
        Bird pigeon = new Pigeon(BirdType.PIGEON, "Urban dweller", false, 2, Arrays.asList(Food.SEEDS, Food.BERRIES));

        System.out.println("Rescuing birds...");
        conservatory.rescueBird(hawk);
        conservatory.rescueBird(eagle);
        conservatory.rescueBird(duck);
        conservatory.rescueBird(parrot);
        conservatory.rescueBird(emu);
        conservatory.rescueBird(owl);
        conservatory.rescueBird(pigeon);
        System.out.println("All birds rescued successfully.\n");

        // 4. Assign birds to aviaries
        System.out.println("Assigning birds to aviaries...");
        System.out.println(" - " + conservatory.assignBirdToAviary(hawk));
        System.out.println(" - " + conservatory.assignBirdToAviary(eagle));
        System.out.println(" - " + conservatory.assignBirdToAviary(duck));
        System.out.println(" - " + conservatory.assignBirdToAviary(parrot));
        System.out.println(" - " + conservatory.assignBirdToAviary(emu));
        System.out.println(" - " + conservatory.assignBirdToAviary(owl));
        System.out.println(" - " + conservatory.assignBirdToAviary(pigeon));
        System.out.println();

        // 5. Print the map
        System.out.println(conservatory.printMap());

        // 6. Calculate food needs
        System.out.println("Food Requirements:");
        Map<Food, Integer> food = conservatory.calculateFoodQuantities();
        for (Map.Entry<Food, Integer> entry : food.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n=================================================");
        System.out.println("   Demonstration Complete");
        System.out.println("=================================================");
    }
}
