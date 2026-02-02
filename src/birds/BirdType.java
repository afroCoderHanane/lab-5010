package birds;

/**
 * Represents the specific types of birds that can be tracked in the conservatory.
 * Each bird type belongs to a particular classification (e.g., birds of prey, waterfowl, etc.).
 */
public enum BirdType {
  // Birds of Prey
  HAWK("Hawk", "Birds of Prey"),
  EAGLE("Eagle", "Birds of Prey"),
  OSPREY("Osprey", "Birds of Prey"),

  // Flightless Birds
  EMU("Emu", "Flightless Birds"),
  KIWI("Kiwi", "Flightless Birds"),
  MOA("Moa", "Flightless Birds"),

  // Owls
  OWL("Owl", "Owls"),

  // Parrots
  ROSE_RING_PARAKEET("Rose-ring Parakeet", "Parrots"),
  GRAY_PARROT("Gray Parrot", "Parrots"),
  SULFUR_CRESTED_COCKATOO("Sulfur-crested Cockatoo", "Parrots"),

  // Pigeons/Doves
  PIGEON("Pigeon", "Pigeons"),
  DOVE("Dove", "Pigeons"),

  // Shorebirds
  GREAT_AUK("Great Auk", "Shorebirds"),
  HORNED_PUFFIN("Horned Puffin", "Shorebirds"),
  AFRICAN_JACANA("African Jacana", "Shorebirds"),

  // Waterfowl
  DUCK("Duck", "Waterfowl"),
  SWAN("Swan", "Waterfowl"),
  GOOSE("Goose", "Waterfowl");

  private final String displayName;
  private final String classification;

  /**
   * Constructs a BirdType enum with a display name and classification.
   *
   * @param displayName    the human-readable name of the bird type
   * @param classification the classification category this bird belongs to
   */
  BirdType(String displayName, String classification) {
    this.displayName = displayName;
    this.classification = classification;
  }

  /**
   * Returns the human-readable display name of the bird type.
   *
   * @return the display name
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Returns the classification category of this bird type.
   *
   * @return the classification name
   */
  public String getClassification() {
    return classification;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
