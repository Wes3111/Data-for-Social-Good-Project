/*
 * Runs the Baseball Statistics Analyzer.
 * Displays the results of the data analysis.
 */
public class Main {
  public static void main(String[] args) {

    // Loads baseball statistics from the text file.
    UserStory team = new UserStory("players.txt");

    // Displays general information about the dataset.
    System.out.println(team);

    // Displays the player with the highest batting average.
    System.out.println("\nBest Player:");
    System.out.println(team.getBestPlayer());

    // Displays players with batting averages of .300 or higher.
    System.out.println("\nPlayers Hitting .300 or Higher:");

    Player[] topPlayers = team.getPlayersAbove300();

    for (int i = 0; i < topPlayers.length; i++) {
      System.out.println(topPlayers[i]);
    }
  }
}