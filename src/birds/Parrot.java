package birds;

import java.util.List;

/**
 * Represents a parrot - birds with short, curved beaks known for their
 * intelligence
 * and ability to mimic sounds.
 * 
 * <p>
 * Parrots have additional attributes: vocabulary size (up to 100 words) and
 * a favorite saying. Examples include rose-ring parakeets, gray parrots, and
 * sulfur-crested cockatoos.
 * </p>
 * 
 * <p>
 * Parrots can be mixed with other compatible bird classifications in the same
 * aviary
 * (i.e., not with birds of prey, flightless birds, or waterfowl).
 * </p>
 */
public class Parrot extends Bird {
    private final int vocabularySize;
    private final String favoriteSaying;

    /**
     * Constructs a Parrot with the specified attributes.
     *
     * @param type                   the specific type of parrot
     * @param definingCharacteristic a description of the bird's defining
     *                               characteristic
     * @param extinct                whether this bird species is extinct
     * @param numberOfWings          the number of wings (typically 2)
     * @param preferredFood          a list of 2-4 preferred food items
     * @param vocabularySize         the number of words the parrot can learn
     *                               (0-100)
     * @param favoriteSaying         the parrot's single favorite saying
     * @throws IllegalArgumentException if vocabularySize is negative or greater
     *                                  than 100,
     *                                  or if favoriteSaying is null
     */
    public Parrot(BirdType type, String definingCharacteristic, boolean extinct,
            int numberOfWings, List<Food> preferredFood,
            int vocabularySize, String favoriteSaying) {
        super(type, definingCharacteristic, extinct, numberOfWings, preferredFood);

        if (vocabularySize < 0 || vocabularySize > 100) {
            throw new IllegalArgumentException("Vocabulary size must be between 0 and 100");
        }
        if (favoriteSaying == null) {
            throw new IllegalArgumentException("Favorite saying cannot be null");
        }

        this.vocabularySize = vocabularySize;
        this.favoriteSaying = favoriteSaying;
    }

    /**
     * Returns the number of words in this parrot's vocabulary.
     *
     * @return the vocabulary size (0-100)
     */
    public int getVocabularySize() {
        return vocabularySize;
    }

    /**
     * Returns this parrot's favorite saying.
     *
     * @return the favorite saying
     */
    public String getFavoriteSaying() {
        return favoriteSaying;
    }

    /**
     * Returns the classification name for parrots.
     *
     * @return "Parrots"
     */
    @Override
    public String getClassification() {
        return "Parrots";
    }

    /**
     * Returns a string representation of this parrot including vocabulary
     * and favorite saying information.
     *
     * @return a descriptive string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" Vocabulary: ").append(vocabularySize).append(" words.");
        if (!favoriteSaying.isEmpty()) {
            sb.append(" Favorite saying: \"").append(favoriteSaying).append("\".");
        }
        return sb.toString();
    }
}
