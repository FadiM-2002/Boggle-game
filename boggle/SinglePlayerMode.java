package boggle;

import java.util.ArrayList;
import java.util.Map;

/**
 * Single player mode class
 */
public class SinglePlayerMode implements BoggleGameMode{

    /**
     * gets words from the computer, which will be valid words that are not already in the player's word list. These
     * words are added to the computer's word list and their score is incremented (in boggleStats) based on the
     * difficulty selected.
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param gameStats Stores game statistics
     * @param difficulty A difficulty level indicated by the user
     */
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats,
                       Integer difficulty) {
        double difficultyRate = difficulty * 0.01;
        int numberOfWords = (int)(difficultyRate * allWords.size());
        int counter = 0;
        for (String word: allWords.keySet()) {
            if (!gameStats.getPlayerWords().contains(word.toUpperCase())) {
                counter++;
                gameStats.addWord(word.toUpperCase(), BoggleStats.Player.Computer);
            }
            if (counter == numberOfWords) break;
        }
    }
}
