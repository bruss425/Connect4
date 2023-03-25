import java.util.*;

public class Menu {
  // starts the game by offering the rules or offering to just play
  public static void startIntro() {
    clear();

    System.out
        .println("Welcome to Connect4! \n This is a game for two players. \n To play PRESS 1.\n For rules PRESS 2.");

    Scanner in = new Scanner(System.in);
    String options = in.nextLine();
    while (!options.equals("1") && !options.equals("2")) {
      System.out.println("Not an option, try again.");
      options = in.nextLine();
    }
    if (options.equals("2")) {
      clear();
      System.out.println("RULES");
      System.out.println(
          "Connect4 is a simple game to play. You must have 2 players to play. \n 1) To start the game player ONE will be RED and player TWO will be YELLOW. \n 2) To decide who goes first, player one will choose heads or tails and a coin will be flipped. The winner will go first. \n 3) Once the game starts, a 6x7 board will be presented. \n 3) Players must alternate turns, and only one disc can be dropped in each turn. \n 4) On your turn, drop one of your colored discs from the top into any of the seven slots. \n 5) To change the column of the disc press either 'a' for LEFT or 'd' for RIGHT then ENTER. To DROP the discs press the SPACEBAR then ENTER. \n 6) The game ends when there is a 4-in-a-row or a stalemate. \n Good luck and thank you for playing!");
      System.out.println("\n To return to main menu press the ENTER.");
      String back = in.nextLine();
      while (!back.equals("")) {
        back = in.nextLine();
      }
      if (back.equals("")) {
        startIntro();
      }

    }

    if (options.equals("1")) {
      Game.GameIntro();
      in.close();
    }
  }

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}



// URL url = new URL("https://v2.jokeapi.dev/joke/Any?safe-mode");
// \n For a fact about the moon press 3.
