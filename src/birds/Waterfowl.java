package birds;

import java.util.List;

/**
 * Represents waterfowl - birds that live near fresh or salt water sources.
 * Examples include ducks, swans, and geese.
 * 
 * <p>
 * Waterfowl cannot be mixed with other bird classifications in the same aviary.
 * </p>
 */
public class Waterfowl extends WaterBird {

    /**
     * Constructs a Waterfowl with the specified attributes.
     *
     * @param type                   the specific type of waterfowl (DUCK, SWAN, or
     *                               GOOSE)
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
    public Waterfowl(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood, String bodyOfWater) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood, bodyOfWater);
    }

    /**
     * Returns the classification name for waterfowl.
     *
     * @return "Waterfowl"
     */
    @Override
    public String getClassification() {
        return "Waterfowl";
    }
}
