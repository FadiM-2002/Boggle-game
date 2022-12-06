package boggle;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Two player mode class
 */
public class TwoPlayerMode implements BoggleGameMode{

    /**
     * gets words from a second user. If a word is valid and not already in the first user's word list, then it is
     * added to this user's word list and their score is incremented (in boggleStats).
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param gameStats Stores game statistics
     * @param difficulty A difficulty level indicated by the user
     */
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats,
                       Integer difficulty, Integer timeLimit) {
        System.out.println("It's P2's turn to find some words!");
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);

        //checks whether user has selected to use a time limit and either runs a game without the timer or with the timer
        boolean timed = (timeLimit != 0);
        if (!timed) {
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
        else {
            BackgroundTimer timer = new BackgroundTimer(timeLimit);
            int timePlayed;
            timer.start();
            while(true) {
                String input = scanner.nextLine();
                if (!timer.isAlive()) {
                    timePlayed = timeLimit - timer.getTime();
                    break;
                }
                if (input.equals("")) {
                    timePlayed = timeLimit - timer.getTime();
                    break;
                }
                for (String word: allWords.keySet()) {
                    if (!gameStats.getPlayerWords().contains(word.toUpperCase()) && word.equalsIgnoreCase(input)) {
                        gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Computer);
                    }
                }
            }
            gameStats.setOpponentTime(timePlayed);
            if (timer.isAlive()) {
                timer.stop();
            }
        }

    }
}
