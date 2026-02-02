package birds;

import java.util.List;

/**
 * Represents an owl - birds distinguished by facial disks that frame the eyes
 * and bill.
 * 
 * <p>
 * Owls can be mixed with other compatible bird classifications in the same
 * aviary
 * (i.e., not with birds of prey, flightless birds, or waterfowl).
 * </p>
 */
public class Owl extends Bird {

    /**
     * Constructs an Owl with the specified attributes.
     *
     * @param type                   the specific type of owl (OWL)
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @throws IllegalArgumentException if any validation fails as per Bird class
     */
    public Owl(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);
    }

    /**
     * Returns the classification name for owls.
     *
     * @return "Owls"
     */
    @Override
    public String getClassification() {
        return "Owls";
    }
}
