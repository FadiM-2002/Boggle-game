package boggle;

import java.util.ArrayList;
import java.util.Map;

public interface BoggleGameMode {

    void opMove(BoggleGrid board, Map<String,ArrayList<Position>> allWords, BoggleStats gameStats);

}
