package boggle;

import java.util.ArrayList;
import java.util.Map;

public class SinglePlayerMode implements BoggleGameMode{
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats) {
        for (String word: allWords.keySet()) {
            if (!gameStats.getPlayerWords().contains(word.toUpperCase())) {
                gameStats.addWord(word.toUpperCase(), BoggleStats.Player.Computer);
            }
        }
    }
}
