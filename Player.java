/*
 * Represents a baseball player and stores their statistics.
 * Each player has a name, number of hits, and number of at-bats.
 */
public class Player {
  private String name;
  private int hits;
  private int atBats;

  /*
   * Creates a player with default statistics.
   * Postcondition: A player with default values is created.
   */
  public Player() {
    name = "Unknown";
    hits = 0;
    atBats = 0;
  }

  /*
   * Creates a player using the given information.
   * Postcondition: The player's information is stored.
   */
  public Player(String name, int hits, int atBats) {
    this.name = name;
    this.hits = hits;
    this.atBats = atBats;
  }

  // Returns the player's name.
  public String getName() {
    return name;
  }

  // Returns the player's number of hits.
  public int getHits() {
    return hits;
  }

  // Returns the player's number of at-bats.
  public int getAtBats() {
    return atBats;
  }

  /*
   * Calculates the player's batting average.
   * Precondition: At-bats must be greater than zero.
   * Postcondition: Returns the batting average.
   */
  public double getBattingAverage() {
    if (atBats == 0) {
      return 0.0;
    }

    return (double) hits / atBats;
  }

  /*
   * Returns information about the player.
   */
  public String toString() {
    return name + " - Hits: " + hits +
      ", At-Bats: " + atBats +
      ", Average: " + getBattingAverage();
  }
}