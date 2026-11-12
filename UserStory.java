import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/*
 * Stores and analyzes baseball player statistics.
 * Uses arrays to store player names, hits, and at-bats.
 */
public class UserStory {
  private String[] names;
  private int[] hits;
  private int[] atBats;
  private int size;

  /*
   * No-argument constructor.
   * Creates empty arrays for the baseball data.
   */
  public UserStory() {
    names = new String[100];
    hits = new int[100];
    atBats = new int[100];
    size = 0;
  }

  /*
   * Parameterized constructor.
   * Reads baseball statistics from a text file.
   * Precondition: The file must contain valid player data.
   * Postcondition: Player information is stored in arrays.
   */
  public UserStory(String filename) {
    this();

    try {
      Scanner input = new Scanner(new File(filename));

      while (input.hasNextLine() && size < names.length) {
        String line = input.nextLine();
        String[] data = line.split(",");

        names[size] = data[0];
        hits[size] = Integer.parseInt(data[1]);
        atBats[size] = Integer.parseInt(data[2]);

        size++;
      }

      input.close();

    } catch (IOException e) {
      System.out.println("Error reading the file.");
    }
  }

  /*
   * Finds the player with the highest batting average.
   * Postcondition: Returns the player with the highest average.
   */
  public Player getBestPlayer() {
    if (size == 0) {
      return new Player();
    }

    int bestIndex = 0;

    for (int i = 1; i < size; i++) {
      double currentAverage =
        (double) hits[i] / atBats[i];

      double bestAverage =
        (double) hits[bestIndex] / atBats[bestIndex];

      if (currentAverage > bestAverage) {
        bestIndex = i;
      }
    }

    return new Player(
      names[bestIndex],
      hits[bestIndex],
      atBats[bestIndex]
    );
  }

  /*
   * Calculates the team's average batting average.
   * Postcondition: Returns the average of all player averages.
   */
  public double getTeamAverage() {
    if (size == 0) {
      return 0.0;
    }

    double total = 0.0;

    for (int i = 0; i < size; i++) {
      if (atBats[i] > 0) {
        total += (double) hits[i] / atBats[i];
      }
    }

    return total / size;
  }

  /*
   * Calculates the total number of hits by all players.
   * Postcondition: Returns the combined number of hits.
   */
  public int getTotalHits() {
    int total = 0;

    for (int i = 0; i < size; i++) {
      total += hits[i];
    }

    return total;
  }

  /*
   * Creates a new array containing players with averages
   * greater than or equal to .300.
   * Postcondition: Returns an array of qualifying players.
   */
  public Player[] getPlayersAbove300() {
    int count = 0;

    for (int i = 0; i < size; i++) {
      if (atBats[i] > 0 &&
          (double) hits[i] / atBats[i] >= 0.300) {
        count++;
      }
    }

    Player[] result = new Player[count];
    int index = 0;

    for (int i = 0; i < size; i++) {
      if (atBats[i] > 0 &&
          (double) hits[i] / atBats[i] >= 0.300) {

        result[index] = new Player(
          names[i], hits[i], atBats[i]
        );

        index++;
      }
    }

    return result;
  }

  /*
   * Returns detailed information about the dataset.
   */
  public String toString() {
    return "Baseball Statistics Analyzer\n" +
      "Number of Players: " + size + "\n" +
      "Total Hits: " + getTotalHits() + "\n" +
      "Team Average: " + getTeamAverage();
  }
}