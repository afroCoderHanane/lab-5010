package birds;

import java.util.List;

/**
 * Represents a pigeon or dove - birds known for feeding their young "bird milk"
 * similar to the milk of mammals.
 * 
 * <p>
 * Found all over the world, there are several varieties that are extinct.
 * </p>
 * <p>
 * Pigeons can be mixed with other compatible bird classifications in the same
 * aviary
 * (i.e., not with birds of prey, flightless birds, or waterfowl).
 * </p>
 */
public class Pigeon extends Bird {

    /**
     * Constructs a Pigeon with the specified attributes.
     *
     * @param type                   the specific type of pigeon (PIGEON or DOVE)
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @throws IllegalArgumentException if any validation fails as per Bird class
     */
    public Pigeon(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);
    }

    /**
     * Returns the classification name for pigeons.
     *
     * @return "Pigeons"
     */
    @Override
    public String getClassification() {
        return "Pigeons";
    }
}
