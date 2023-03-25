import java.util.*;

public class Game {
  public static int cursor;
  public static String moveArrow;
  public static int turn;
  public static Scanner in = new Scanner(System.in);
  public static String[][] board;
  public static String coin;

  // intros the menu - coin is flipped and then ends with displaying the board
  public static void GameIntro() {
    Menu.clear();

    System.out.println(
        "Lets play Connect4! \n Player one please choose 'H' for Heads or 'T' for Tails to decide who goes first. ");

    Scanner in = new Scanner(System.in);
    String flipChoice = in.nextLine();

    while (!flipChoice.equals("T") && !flipChoice.equals("H")) {
      System.out.println("Not an option, try again.");
      flipChoice = in.nextLine();
    }
    if (flipChoice.equals("T") || flipChoice.equals("H")) {
      System.out.println("Flipping...");
      Toss t = new Toss();

      String flipAnswer = t.flip();
      String answerWritten; // for rewriting H or T into Heads or Tails
      if (flipAnswer.equals("T")) {
        answerWritten = "TAILS";
      } else {
        answerWritten = "HEADS";
      }
      if (flipAnswer.equals(flipChoice)) {
        System.out.println("The answer was " + answerWritten + "! Player ONE will be YELLOW and go first.");
      } else {
        System.out.println("The answer was " + answerWritten + "! Player TWO will be YELLOW and go first.");
      }
      // game begins by calling display method
      System.out.println("Press RETURN to continue and begin game.");
      String beginGame = in.nextLine();
      while (!beginGame.equals("")) {
        System.out.println("Not an option, try again.");
        beginGame = in.nextLine();
      }
      if (beginGame.equals("")) {
        Menu.clear();

        display();
      }
    }
  }

  // displays the board and updates board as cursor is moved and pieces are
  // dropped
  public static void display() {
    cursor = 3;
    turn = 1;
    coin = "🟡";
    System.out.println(printCoin(cursor));
    System.out.println(printArrow(cursor));

    board = new String[6][7];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = "🔲";
      }
    }

    System.out.println(displayBoard(board));
    moveArrow = in.nextLine();
    while (moveArrow.equals(" ")) { // if someone drops a piece do the thing all over again
      drop();
      moveCursor();
    }
  }

  // keeps a count of the cursor based on the input and uses input to move the
  // lines above the board
  public static void moveCursor() {
    moveArrow = in.nextLine();
    while (!moveArrow.equals(" ")) {
      while (!moveArrow.equals("a") && !moveArrow.equals("d")) {
        System.out.println("Not an option, try again.");
        moveArrow = in.nextLine();
      }
      if (moveArrow.equals("d")) {
        Menu.clear();
        if (cursor < 6) {
          cursor++;
        }
        System.out.println(printCoin(cursor));
        System.out.println(printArrow(cursor));
        System.out.println(displayBoard(board));
        moveArrow = in.nextLine();
      }
      if (moveArrow.equals("a")) {
        Menu.clear();
        if (cursor > 0) {
          cursor--;
        }
        System.out.println(printCoin(cursor));
        System.out.println(printArrow(cursor));
        System.out.println(displayBoard(board));
        moveArrow = in.nextLine();
      }
    }
  }

  // based on the turn it prints the correct color coin
  // based on cursor the coin moves
  public static String printCoin(int cursor1) {
    String cursorPrint = "";
    for (int i = 0; i < cursor1; i++) {
      cursorPrint += " 🔳";
    }
    if (turn % 2 == 1) {
      coin = "🟡";
    } else {
      coin = "🔴";
    }
    cursorPrint += " " + coin;
    for (int j = cursor1; j < 6; j++) {
      cursorPrint += " 🔳";
    }
    return cursorPrint;
  }

  // arrow moves based on cursor depending on a and d input
  public static String printArrow(int cursor1) {
    String cursorPrint = "";
    for (int i = 0; i < cursor1; i++) {
      cursorPrint += " 🔳";
    }
    cursorPrint += " ⬇️ ";
    for (int j = cursor1; j < 6; j++) {
      cursorPrint += " 🔳";
    }
    return cursorPrint;
  }

  public static String displayBoard(String[][] board) {
    String print = (Arrays.deepToString(board).replace("], ", "|\n").replace("[[", "|").replace("]]", "|")
        .replace("[", "|").replace(",", ""));
    return print;
  }

  // drops a piece and outputs the screen again
  public static void drop() {

    boolean open = false;
    int spot = -1;
    if (turn < 43) {
      for (int i = 5; i >= 0; i--) {
        if (board[i][cursor].equals("🔲")) {
          open = true;
          spot = i;
          break;
        }
      }
      if (open) {

        if (turn % 2 == 1) {
          coin = "🟡";
        } else {
          coin = "🔴";
        }
        board[spot][cursor] = coin;
        turn++;
        Menu.clear();
        System.out.println(printCoin(cursor));
        System.out.println(printArrow(cursor));
        System.out.println(displayBoard(board));
        checkWinner();
      } else {
        Menu.clear();
        System.out.println(printCoin(cursor));
        System.out.println(printArrow(cursor));
        System.out.println(displayBoard(board));
        System.out.println("Column is full, try a different one"); // this does not work
      }

    }
  }

  // after every piece is dropped this method is called to check for winners
  // if winner is found winenr screen is called
  public static void checkWinner() {
    boolean winner = false;
    for (int i = 0; i < 6; i++) { // checking for 4-across
      for (int j = 0; j < 4; j++) {
        if (board[i][j] == board[i][j + 1] &&
            board[i][j] == board[i][j + 2] &&
            board[i][j] == board[i][j + 3] &&
            board[i][j] != "🔲") {
          winner = true;
        }
      }
    }
    for (int i = 0; i < 3; i++) { // check for 4-vertical
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == board[i + 1][j] &&
            board[i][j] == board[i + 2][j] &&
            board[i][j] == board[i + 3][j] &&
            board[i][j] != "🔲") {
          winner = true;
        }
      }
    }
    for (int i = 0; i < 3; i++) { // checking for up diagnol
      for (int j = 0; j < 4; j++) {
        if (board[i][j] == board[i + 1][j + 1] &&
            board[i][j] == board[i + 2][j + 2] &&
            board[i][j] == board[i + 3][j + 3] &&
            board[i][j] != "🔲") {
          winner = true;
        }
      }
    }
    for (int i = 0; i < 3; i++) { // checking for down diagnol
      for (int j = 3; j < 7; j++) {
        if (board[i][j] == board[i + 1][j - 1] &&
            board[i][j] == board[i + 2][j - 2] &&
            board[i][j] == board[i + 3][j - 3] &&
            board[i][j] != "🔲") {
          winner = true;
        }
      }
    }

    if (winner) {
      winnerScreen();
    }
  }

  // screen presented when someone wins
  public static void winnerScreen() {
    for (int i = 0; i < 3; i++) {
      Menu.clear();
      System.out.println("WINNER");
      System.out.println("WINNER");
      System.out.println("WINNER");
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      }
      Menu.clear();
      System.out.println(displayBoard(board));
      if (turn % 2 == 1) {
        coin = "🔴";
      } else {
        coin = "🟡";
      }
      System.out.println(coin + " has won the game!");
      System.out.println("Would you like to play again? - y for Yes or n for no");
      String playAgain = in.nextLine();
      if (playAgain.equals("y")) {
        GameIntro();
      } else {
        Menu.clear();
        System.out.println("Thank you for playing.");
      }

    }
  }

}

// a class/method to flp coin not rlly sure why I made this its own class but it
// works :D
class Toss {
  public String flip() {
    Random r = new Random();
    int chance = r.nextInt(2);
    if (chance == 1) {
      return "T";
    } else {
      return "H";
    }
  }
}