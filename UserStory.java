
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/*
 * Stores and analyzes MLB player statistics.
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
   * Reads all player statistics from a text file.
   * Precondition: The file contains player names, hits, and at-bats.
   * Postcondition: Valid player data is stored in the arrays.
   */
  public UserStory(String filename) {
    this();

    try {
      Scanner input = new Scanner(new File(filename));

      while (input.hasNextLine() && size < names.length) {
        String line = input.nextLine().trim();

        if (!line.equals("")) {
          String[] data = line.split(",");

          if (data.length == 3) {
            names[size] = data[0].trim();
            hits[size] = Integer.parseInt(data[1].trim());
            atBats[size] = Integer.parseInt(data[2].trim());

            size++;
          }
        }
      }

      input.close();

    } catch (IOException e) {
      System.out.println("Error reading players.txt");
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

    int bestIndex = -1;

    for (int i = 0; i < size; i++) {
      if (atBats[i] > 0) {
        if (bestIndex == -1 ||
            (double) hits[i] / atBats[i] >
            (double) hits[bestIndex] / atBats[bestIndex]) {
          bestIndex = i;
        }
      }
    }

    if (bestIndex == -1) {
      return new Player();
    }

    return new Player(names[bestIndex], hits[bestIndex], atBats[bestIndex]);
  }

  /*
   * Calculates the average batting average of all players.
   * Postcondition: Returns the team's average batting average.
   */
  public double getTeamAverage() {
    if (size == 0) {
      return 0.0;
    }

    double total = 0.0;
    int count = 0;

    for (int i = 0; i < size; i++) {
      if (atBats[i] > 0) {
        total += (double) hits[i] / atBats[i];
        count++;
      }
    }

    if (count == 0) {
      return 0.0;
    }

    return total / count;
  }

  /*
   * Calculates the total number of hits from all players.
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
   * of .300 or higher.
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
   * Returns detailed information about the MLB dataset.
   */
  public String toString() {
    return "MLB Statistics Analyzer\n" +
      "Number of Players: " + size + "\n" +
      "Total Hits: " + getTotalHits() + "\n" +
      "Average Batting Average: " + getTeamAverage();
  }
}
