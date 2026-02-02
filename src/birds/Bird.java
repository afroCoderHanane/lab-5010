package birds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract base class representing a bird in the conservatory.
 * All birds have a type, defining characteristic, extinction status, number of
 * wings,
 * and a list of preferred foods (2-4 items).
 * 
 * <p>
 * Subclasses must implement the {@link #getClassification()} method to return
 * the classification name of the bird (e.g., "Birds of Prey", "Waterfowl",
 * etc.).
 * </p>
 */
public abstract class Bird {
    private final BirdType type;
    private final String definingCharacteristic;
    private final boolean extinct;
    private final int numberOfWings;
    private final List<Food> preferredFood;

    /**
     * Constructs a Bird with the specified attributes.
     *
     * @param type                   the specific type of bird (e.g., HAWK, DUCK)
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 0, 1, or 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @throws IllegalArgumentException if type is null, definingCharacteristic is
     *                                  null or empty,
     *                                  numberOfWings is negative, preferredFood is
     *                                  null,
     *                                  or preferredFood doesn't contain 2-4 items
     */
    public Bird(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood) {
        if (type == null) {
            throw new IllegalArgumentException("Bird type cannot be null");
        }
        if (definingCharacteristic == null || definingCharacteristic.trim().isEmpty()) {
            throw new IllegalArgumentException("Defining characteristic cannot be null or empty");
        }
        if (numberOfWings < 0) {
            throw new IllegalArgumentException("Number of wings cannot be negative");
        }
        if (preferredFood == null) {
            throw new IllegalArgumentException("Preferred food list cannot be null");
        }
        if (preferredFood.size() < 2 || preferredFood.size() > 4) {
            throw new IllegalArgumentException("Birds must have 2-4 preferred food items");
        }

        this.type = type;
        this.definingCharacteristic = definingCharacteristic;
        this.extinct = extinct;
        this.numberOfWings = numberOfWings;
        this.preferredFood = new ArrayList<>(preferredFood); // Defensive copy
    }

    /**
     * Returns the specific type of this bird.
     *
     * @return the bird type
     */
    public BirdType getType() {
        return type;
    }

    /**
     * Returns the defining characteristic of this bird.
     *
     * @return the defining characteristic description
     */
    public String getDefiningCharacteristic() {
        return definingCharacteristic;
    }

    /**
     * Returns whether this bird species is extinct.
     *
     * @return true if extinct, false otherwise
     */
    public boolean isExtinct() {
        return extinct;
    }

    /**
     * Returns the number of wings this bird has.
     *
     * @return the number of wings
     */
    public int getNumberOfWings() {
        return numberOfWings;
    }

    /**
     * Returns a defensive copy of the preferred food list.
     *
     * @return a new list containing the preferred food items
     */
    public List<Food> getPreferredFood() {
        return new ArrayList<>(preferredFood);
    }

    /**
     * Returns the classification name of this bird.
     * Each subclass must implement this to return its specific classification
     * (e.g., "Birds of Prey", "Flightless Birds", "Parrots", etc.).
     *
     * @return the classification name
     */
    public abstract String getClassification();

    /**
     * Returns a string representation of this bird including its type,
     * classification, characteristics, and other relevant information.
     *
     * @return a descriptive string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.getDisplayName());
        sb.append(" (").append(getClassification()).append(")");
        sb.append(": ").append(definingCharacteristic);
        if (extinct) {
            sb.append(" [EXTINCT]");
        }
        sb.append(". Wings: ").append(numberOfWings);
        sb.append(". Preferred food: ");
        for (int i = 0; i < preferredFood.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(preferredFood.get(i).getDisplayName());
        }
        sb.append(".");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bird bird = (Bird) o;
        return extinct == bird.extinct
                && numberOfWings == bird.numberOfWings
                && type == bird.type
                && Objects.equals(definingCharacteristic, bird.definingCharacteristic)
                && Objects.equals(preferredFood, bird.preferredFood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, definingCharacteristic, extinct, numberOfWings, preferredFood);
    }
}
