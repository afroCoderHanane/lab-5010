package birds;

/**
 * Represents the types of food that birds can eat.
 * Each bird has a preferred diet consisting of 2-4 items from this list.
 */
public enum Food {
  BERRIES("berries"),
  SEEDS("seeds"),
  FRUIT("fruit"),
  INSECTS("insects"),
  OTHER_BIRDS("other birds"),
  EGGS("eggs"),
  SMALL_MAMMALS("small mammals"),
  FISH("fish"),
  BUDS("buds"),
  LARVAE("larvae"),
  AQUATIC_INVERTEBRATES("aquatic invertebrates"),
  NUTS("nuts"),
  VEGETATION("vegetation");

  private final String displayName;

  /**
   * Constructs a Food enum with a display name.
   *
   * @param displayName the human-readable name of the food
   */
  Food(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Returns the human-readable display name of the food.
   *
   * @return the display name
   */
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
