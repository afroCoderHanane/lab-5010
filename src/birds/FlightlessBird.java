package birds;

import java.util.List;

/**
 * Represents a flightless bird - birds that live on the ground and have no or
 * undeveloped wings.
 * Examples include emus, kiwis, and moas.
 * 
 * <p>
 * Note: Some flightless birds (like the moa) are extinct.
 * </p>
 * <p>
 * Flightless birds cannot be mixed with other bird classifications in the same
 * aviary.
 * </p>
 */
public class FlightlessBird extends Bird {

    /**
     * Constructs a FlightlessBird with the specified attributes.
     *
     * @param type                   the specific type of flightless bird (EMU,
     *                               KIWI, or MOA)
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 0 or
     *                               undeveloped)
     * @param preferredFood          a list of 2-4 preferred food items
     * @throws IllegalArgumentException if any validation fails as per Bird class
     */
    public FlightlessBird(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);
    }

    /**
     * Returns the classification name for flightless birds.
     *
     * @return "Flightless Birds"
     */
    @Override
    public String getClassification() {
        return "Flightless Birds";
    }
}
