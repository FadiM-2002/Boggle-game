package boggle;

import java.util.ArrayList;
import java.util.Map;

/**
 * Single player mode class
 */
public class SinglePlayerMode implements BoggleGameMode{

    /**
     * gets words from a second user. If a word is valid and not already in the first user's word list, then it is
     * added to this user's word list and their score is incremented (in boggleStats).
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param gameStats Stores game statistics
     */
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats) {
        for (String word: allWords.keySet()) {
            if (!gameStats.getPlayerWords().contains(word.toUpperCase())) {
                gameStats.addWord(word.toUpperCase(), BoggleStats.Player.Computer);
            }
        }
    }
}
