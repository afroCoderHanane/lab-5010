package birds;

import java.util.List;

/**
 * Represents a shorebird - birds that live near water sources including
 * wetlands, freshwater and saltwater shorelands, and even the ocean.
 * 
 * <p>
 * Examples include the great auk (extinct), horned puffin, and African jacana.
 * </p>
 * <p>
 * Shorebirds can be mixed with other compatible bird classifications in the
 * same aviary
 * (i.e., not with birds of prey, flightless birds, or waterfowl).
 * </p>
 */
public class Shorebird extends WaterBird {

    /**
     * Constructs a Shorebird with the specified attributes.
     *
     * @param type                   the specific type of shorebird
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @param bodyOfWater            the name of the body of water where the bird
     *                               lives
     * @throws IllegalArgumentException if any validation fails as per WaterBird
     *                                  class
     */
    public Shorebird(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood, String bodyOfWater) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood, bodyOfWater);
    }

    /**
     * Returns the classification name for shorebirds.
     *
     * @return "Shorebirds"
     */
    @Override
    public String getClassification() {
        return "Shorebirds";
    }
}
