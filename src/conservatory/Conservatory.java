package conservatory;

import birds.Bird;
import birds.Food;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a conservatory that houses many different types of birds in
 * aviaries.
 * The conservatory can have a maximum of 20 aviaries, each holding up to 5
 * birds.
 * 
 * <p>
 * The conservatory provides functionality to:
 * </p>
 * <ul>
 * <li>Rescue new birds and bring them into the conservatory</li>
 * <li>Assign birds to appropriate aviaries following mixing rules</li>
 * <li>Calculate food requirements for all birds</li>
 * <li>Look up which aviary a bird is housed in</li>
 * <li>Print signs for individual aviaries</li>
 * <li>Print a map of all aviaries and their birds</li>
 * <li>Print an alphabetical index of all birds and their locations</li>
 * </ul>
 */
public class Conservatory {
    private static final int MAX_AVIARIES = 20;

    private final List<Aviary> aviaries;
    private final List<Bird> rescuedBirds;
    private int nextAviaryId;

    /**
     * Constructs an empty Conservatory with no aviaries or birds.
     */
    public Conservatory() {
        this.aviaries = new ArrayList<>();
        this.rescuedBirds = new ArrayList<>();
        this.nextAviaryId = 1;
    }

    /**
     * Rescues a new bird and brings it into the conservatory.
     * The bird is added to the list of rescued birds but is not yet
     * assigned to an aviary.
     *
     * @param bird the bird to rescue
     * @throws IllegalArgumentException if bird is null
     * @throws IllegalStateException    if the bird has already been rescued
     */
    public void rescueBird(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }
        if (rescuedBirds.contains(bird)) {
            throw new IllegalStateException("This bird has already been rescued");
        }
        rescuedBirds.add(bird);
    }

    /**
     * Returns a defensive copy of all rescued birds.
     *
     * @return a list of all rescued birds
     */
    public List<Bird> getRescuedBirds() {
        return new ArrayList<>(rescuedBirds);
    }

    /**
     * Returns a defensive copy of all aviaries.
     *
     * @return a list of all aviaries in the conservatory
     */
    public List<Aviary> getAviaries() {
        return new ArrayList<>(aviaries);
    }

    /**
     * Assigns a bird to an aviary in the conservatory.
     * The bird is placed in a compatible aviary if one exists with space,
     * otherwise a new aviary is created.
     *
     * @param bird the bird to assign to an aviary
     * @return a message indicating where the bird was assigned
     * @throws IllegalArgumentException if bird is null
     * @throws IllegalStateException    if the bird is extinct and cannot be added
     *                                  to an aviary,
     *                                  or if the conservatory is at maximum
     *                                  capacity
     */
    public String assignBirdToAviary(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }

        if (bird.isExtinct()) {
            throw new IllegalStateException("Extinct birds cannot be added to an aviary");
        }

        // First, check if bird is already in an aviary
        for (Aviary aviary : aviaries) {
            if (aviary.hasBird(bird)) {
                return bird.getType().getDisplayName() + " is already in Aviary "
                        + aviary.getId() + " (" + aviary.getLocation() + ")";
            }
        }

        // Try to find a compatible aviary with space
        Aviary compatibleAviary = findCompatibleAviary(bird);

        if (compatibleAviary != null) {
            compatibleAviary.addBird(bird);
            return bird.getType().getDisplayName() + " assigned to Aviary "
                    + compatibleAviary.getId() + " (" + compatibleAviary.getLocation() + ")";
        }

        // No compatible aviary found, create a new one
        Aviary newAviary = createNewAviary(bird);
        newAviary.addBird(bird);
        return bird.getType().getDisplayName() + " assigned to new Aviary "
                + newAviary.getId() + " (" + newAviary.getLocation() + ")";
    }

    /**
     * Finds an existing compatible aviary for the given bird.
     *
     * @param bird the bird to find an aviary for
     * @return a compatible aviary, or null if none exists
     */
    private Aviary findCompatibleAviary(Bird bird) {
        for (Aviary aviary : aviaries) {
            if (aviary.canAddBird(bird)) {
                return aviary;
            }
        }
        return null;
    }

    /**
     * Creates a new aviary for the given bird.
     *
     * @param bird the bird that will be placed in the new aviary
     * @return the newly created aviary
     * @throws IllegalStateException if the conservatory is at maximum aviary
     *                               capacity
     */
    private Aviary createNewAviary(Bird bird) {
        if (aviaries.size() >= MAX_AVIARIES) {
            throw new IllegalStateException(
                    "Conservatory has reached maximum capacity of " + MAX_AVIARIES + " aviaries");
        }

        String location = generateLocation(bird);
        Aviary newAviary = new Aviary(nextAviaryId++, location);
        aviaries.add(newAviary);
        return newAviary;
    }

    /**
     * Generates a location name for a new aviary based on the bird type.
     *
     * @param bird the bird that will be housed in the aviary
     * @return a descriptive location name
     */
    private String generateLocation(Bird bird) {
        String classification = bird.getClassification();
        int count = 1;
        for (Aviary aviary : aviaries) {
            if (aviary.getClassificationType() != null
                    && aviary.getClassificationType().equals(classification)) {
                count++;
            }
        }
        return classification + " Wing - Section " + count;
    }

    /**
     * Calculates the quantities of each food type needed to feed all birds
     * in the conservatory. Each bird is assumed to require 1 unit of each
     * food type in their preferred food list.
     *
     * @return a map of food types to quantities needed
     */
    public Map<Food, Integer> calculateFoodQuantities() {
        Map<Food, Integer> foodQuantities = new HashMap<>();

        for (Aviary aviary : aviaries) {
            for (Bird bird : aviary.getBirds()) {
                for (Food food : bird.getPreferredFood()) {
                    foodQuantities.merge(food, 1, Integer::sum);
                }
            }
        }

        return foodQuantities;
    }

    /**
     * Looks up which aviary a specific bird is housed in.
     *
     * @param bird the bird to look up
     * @return a string describing the bird's location, or a message if not found
     * @throws IllegalArgumentException if bird is null
     */
    public String lookupBird(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }

        for (Aviary aviary : aviaries) {
            if (aviary.hasBird(bird)) {
                return bird.getType().getDisplayName() + " is located in Aviary "
                        + aviary.getId() + " (" + aviary.getLocation() + ")";
            }
        }

        // Check if bird is rescued but not assigned
        if (rescuedBirds.contains(bird)) {
            return bird.getType().getDisplayName()
                    + " has been rescued but is not yet assigned to an aviary";
        }

        return bird.getType().getDisplayName() + " is not found in this conservatory";
    }

    /**
     * Returns the sign for a specific aviary.
     *
     * @param aviaryId the ID of the aviary
     * @return the sign string for the specified aviary
     * @throws IllegalArgumentException if no aviary with the given ID exists
     */
    public String getAviarySign(int aviaryId) {
        for (Aviary aviary : aviaries) {
            if (aviary.getId() == aviaryId) {
                return aviary.getSign();
            }
        }
        throw new IllegalArgumentException("No aviary found with ID: " + aviaryId);
    }

    /**
     * Returns a map of the conservatory listing all aviaries by location
     * and the birds they house.
     *
     * @return a formatted string representing the conservatory map
     */
    public String printMap() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════════════════╗\n");
        sb.append("║              CONSERVATORY MAP                                ║\n");
        sb.append("╚══════════════════════════════════════════════════════════════╝\n\n");

        if (aviaries.isEmpty()) {
            sb.append("No aviaries have been created yet.\n");
        } else {
            for (Aviary aviary : aviaries) {
                sb.append("┌─ Aviary ").append(aviary.getId());
                sb.append(" ─ ").append(aviary.getLocation()).append(" ");
                sb.append("─".repeat(Math.max(0, 45 - aviary.getLocation().length())));
                sb.append("┐\n");

                List<Bird> birds = aviary.getBirds();
                if (birds.isEmpty()) {
                    sb.append("│  (empty)").append(" ".repeat(53)).append("│\n");
                } else {
                    for (Bird bird : birds) {
                        String birdInfo = "  • " + bird.getType().getDisplayName()
                                + " (" + bird.getClassification() + ")";
                        sb.append("│").append(birdInfo);
                        sb.append(" ".repeat(Math.max(0, 62 - birdInfo.length()))).append("│\n");
                    }
                }
                sb.append("└").append("─".repeat(62)).append("┘\n\n");
            }
        }

        return sb.toString();
    }

    /**
     * Returns an alphabetical index of all birds in the conservatory
     * along with their locations.
     *
     * @return a formatted string representing the alphabetical bird index
     */
    public String printIndex() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════════════════╗\n");
        sb.append("║              BIRD INDEX (A-Z)                                ║\n");
        sb.append("╚══════════════════════════════════════════════════════════════╝\n\n");

        // Collect all birds with their aviary info
        List<BirdLocation> birdLocations = new ArrayList<>();

        for (Aviary aviary : aviaries) {
            for (Bird bird : aviary.getBirds()) {
                birdLocations.add(new BirdLocation(bird, aviary));
            }
        }

        if (birdLocations.isEmpty()) {
            sb.append("No birds are currently housed in the conservatory.\n");
        } else {
            // Sort alphabetically by bird type name
            birdLocations.sort(Comparator.comparing(bl -> bl.bird.getType().getDisplayName()));

            for (BirdLocation bl : birdLocations) {
                sb.append(String.format("%-30s → Aviary %d (%s)%n",
                        bl.bird.getType().getDisplayName(),
                        bl.aviary.getId(),
                        bl.aviary.getLocation()));
            }
        }

        return sb.toString();
    }

    /**
     * Helper class to associate a bird with its aviary location.
     */
    private static class BirdLocation {
        final Bird bird;
        final Aviary aviary;

        BirdLocation(Bird bird, Aviary aviary) {
            this.bird = bird;
            this.aviary = aviary;
        }
    }

    @Override
    public String toString() {
        return "Conservatory with " + aviaries.size() + " aviaries and "
                + rescuedBirds.size() + " rescued birds";
    }
}
