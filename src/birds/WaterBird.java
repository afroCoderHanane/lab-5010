package birds;

import java.util.List;

/**
 * Abstract base class representing birds that live near water sources.
 * Water birds have an additional attribute tracking the body of water they live
 * near.
 * 
 * <p>
 * Subclasses include Shorebirds and Waterfowl.
 * </p>
 */
public abstract class WaterBird extends Bird {
    private final String bodyOfWater;

    /**
     * Constructs a WaterBird with the specified attributes.
     *
     * @param type                   the specific type of water bird
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @param bodyOfWater            the name of the body of water where the bird
     *                               lives
     * @throws IllegalArgumentException if bodyOfWater is null or empty
     */
    public WaterBird(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood, String bodyOfWater) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);

        if (bodyOfWater == null || bodyOfWater.trim().isEmpty()) {
            throw new IllegalArgumentException("Body of water cannot be null or empty");
        }

        this.bodyOfWater = bodyOfWater;
    }

    /**
     * Returns the name of the body of water where this bird lives.
     *
     * @return the body of water name
     */
    public String getBodyOfWater() {
        return bodyOfWater;
    }

    /**
     * Returns a string representation of this water bird including
     * its body of water.
     *
     * @return a descriptive string representation
     */
    @Override
    public String toString() {
        return super.toString() + " Lives near: " + bodyOfWater + ".";
    }
}
