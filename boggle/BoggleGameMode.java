package boggle;

import java.util.ArrayList;
import java.util.Map;

/**
 * Game mode interface
 */
public interface BoggleGameMode {

    /**
     * gets words from the user's opponent, who can either be another user or the computer. If a word is valid and
     * not already in the first user's word list, then it is added to the opponent's word list and their score
     * is incremented (in boggleStats).
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param gameStats Stores game statistics
     * @param difficulty A difficulty level indicated by the user
     * @param timeLimit The time limit imposed on each human player for each round
     */
    void opMove(BoggleGrid board, Map<String,ArrayList<Position>> allWords, BoggleStats gameStats, Integer difficulty,
                Integer timeLimit);

}
