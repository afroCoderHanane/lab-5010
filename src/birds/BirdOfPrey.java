package birds;

import java.util.List;

/**
 * Represents a bird of prey - birds with sharp, hooked beaks and visible
 * nostrils.
 * Examples include hawks, eagles, and osprey.
 * 
 * <p>
 * Birds of prey cannot be mixed with other bird classifications in the same
 * aviary.
 * </p>
 */
public class BirdOfPrey extends Bird {

    /**
     * Constructs a BirdOfPrey with the specified attributes.
     *
     * @param type                   the specific type of bird of prey (HAWK, EAGLE,
     *                               or OSPREY)
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @throws IllegalArgumentException if any validation fails as per Bird class
     */
    public BirdOfPrey(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);
    }

    /**
     * Returns the classification name for birds of prey.
     *
     * @return "Birds of Prey"
     */
    @Override
    public String getClassification() {
        return "Birds of Prey";
    }
}
