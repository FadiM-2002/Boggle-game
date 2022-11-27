package boggle;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Two player mode class
 */
public class TwoPlayerMode implements BoggleGameMode{

    /**
     * gets words from the computer, which will be valid words that are not already in the player's word list. These
     * words are added to the computer's word list and their score is incremented (in boggleStats).
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param gameStats Stores game statistics
     */
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats) {
        System.out.println("It's P2's turn to find some words!");
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("")) break;
            for (String word: allWords.keySet()) {
                if (!gameStats.getPlayerWords().contains(word.toUpperCase()) && word.equalsIgnoreCase(input)) {
                    gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Computer);
                }
            }
        }
    }
}
