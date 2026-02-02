package conservatory;

import birds.Bird;
import birds.BirdOfPrey;
import birds.FlightlessBird;
import birds.Waterfowl;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an aviary in the conservatory that houses birds.
 * An aviary has a maximum capacity of 5 birds and enforces mixing rules:
 * <ul>
 * <li>No extinct birds can be added</li>
 * <li>Birds of prey, flightless birds, and waterfowl cannot be mixed with other
 * types</li>
 * <li>Other bird types (owls, parrots, pigeons, shorebirds) can be mixed
 * together</li>
 * </ul>
 */
public class Aviary {
    private static final int MAX_CAPACITY = 5;

    private final int id;
    private final String location;
    private final List<Bird> birds;

    /**
     * Constructs an empty Aviary with the specified ID and location.
     *
     * @param id       the unique identifier for this aviary
     * @param location the physical location description of this aviary
     * @throws IllegalArgumentException if location is null or empty
     */
    public Aviary(int id, String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        this.id = id;
        this.location = location;
        this.birds = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of this aviary.
     *
     * @return the aviary ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the physical location description of this aviary.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns a defensive copy of the list of birds in this aviary.
     *
     * @return a new list containing all birds in this aviary
     */
    public List<Bird> getBirds() {
        return new ArrayList<>(birds);
    }

    /**
     * Returns whether this aviary is empty.
     *
     * @return true if no birds are housed, false otherwise
     */
    public boolean isEmpty() {
        return birds.isEmpty();
    }

    /**
     * Returns whether this aviary is at maximum capacity.
     *
     * @return true if 5 birds are housed, false otherwise
     */
    public boolean isFull() {
        return birds.size() >= MAX_CAPACITY;
    }

    /**
     * Returns whether the specified bird is housed in this aviary.
     *
     * @param bird the bird to check for
     * @return true if the bird is in this aviary, false otherwise
     */
    public boolean hasBird(Bird bird) {
        return birds.contains(bird);
    }

    /**
     * Returns the classification type of birds currently housed in this aviary.
     * Returns null if the aviary is empty.
     *
     * @return the classification string, or null if empty
     */
    public String getClassificationType() {
        if (birds.isEmpty()) {
            return null;
        }
        return birds.get(0).getClassification();
    }

    /**
     * Determines if a bird is a "restricted" type that cannot be mixed with others.
     * Birds of prey, flightless birds, and waterfowl are restricted.
     *
     * @param bird the bird to check
     * @return true if the bird is restricted, false otherwise
     */
    private boolean isRestrictedType(Bird bird) {
        return bird instanceof BirdOfPrey
                || bird instanceof FlightlessBird
                || bird instanceof Waterfowl;
    }

    /**
     * Checks if the specified bird can be added to this aviary.
     * A bird can be added if:
     * <ul>
     * <li>The bird is not extinct</li>
     * <li>The aviary is not full</li>
     * <li>The aviary is empty, OR</li>
     * <li>The bird is compatible with existing birds (same classification for
     * restricted types, or both are non-restricted types)</li>
     * </ul>
     *
     * @param bird the bird to check
     * @return true if the bird can be added, false otherwise
     */
    public boolean canAddBird(Bird bird) {
        if (bird == null) {
            return false;
        }

        // Extinct birds cannot be added to an aviary
        if (bird.isExtinct()) {
            return false;
        }

        // Cannot add if aviary is full
        if (isFull()) {
            return false;
        }

        // Any bird can be added to an empty aviary
        if (isEmpty()) {
            return true;
        }

        // Check compatibility with existing birds
        Bird existingBird = birds.get(0);

        // If the new bird is a restricted type
        if (isRestrictedType(bird)) {
            // It can only be with birds of the same classification
            return bird.getClassification().equals(existingBird.getClassification());
        }

        // If existing birds are a restricted type, new bird cannot be added
        if (isRestrictedType(existingBird)) {
            return false;
        }

        // Both are non-restricted types, they can be mixed
        return true;
    }

    /**
     * Adds a bird to this aviary.
     *
     * @param bird the bird to add
     * @throws IllegalArgumentException if bird is null
     * @throws IllegalStateException    if the bird cannot be added (extinct, full,
     *                                  or incompatible)
     */
    public void addBird(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }
        if (!canAddBird(bird)) {
            if (bird.isExtinct()) {
                throw new IllegalStateException("Cannot add extinct bird to aviary");
            } else if (isFull()) {
                throw new IllegalStateException("Aviary is at maximum capacity");
            } else {
                throw new IllegalStateException("Bird is incompatible with existing birds in this aviary");
            }
        }
        birds.add(bird);
    }

    /**
     * Returns a sign describing this aviary and the birds it houses.
     * The sign includes information about each bird's type, characteristics,
     * and any interesting information specific to that bird type.
     *
     * @return a formatted string representing the aviary sign
     */
    public String getSign() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Aviary ").append(id).append(" ===\n");
        sb.append("Location: ").append(location).append("\n\n");

        if (isEmpty()) {
            sb.append("This aviary is currently empty.\n");
        } else {
            sb.append("Birds housed here:\n");
            sb.append("-".repeat(40)).append("\n");
            for (Bird bird : birds) {
                sb.append(bird.toString()).append("\n\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Aviary " + id + " (" + location + ") - " + birds.size() + "/" + MAX_CAPACITY + " birds";
    }
}
