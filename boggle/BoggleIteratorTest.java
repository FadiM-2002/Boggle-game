package boggle;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BoggleIteratorTest {
    @Test
    void findAllWordsTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BoggleGame game = new BoggleGame();
        Method method = game.getClass().getDeclaredMethod("findAllWords", Map.class, Dictionary.class, BoggleGrid.class);
        method.setAccessible(true);

        Dictionary boggleDict = new Dictionary("wordlist.txt");
        Map<String, ArrayList<Position>> allWords = new HashMap<>();
        BoggleGrid grid = new BoggleGrid(4);
        grid.initalizeBoard("RHLDNHTGIPHSNMJO");

        Object r = method.invoke(game, allWords, boggleDict, grid);
        Set<String> expected = new HashSet<>(Arrays.asList("GHOST", "HOST", "THIN"));
        assertEquals(expected, allWords.keySet());
    }
    @Test
    void gridIteratorTest() {
        BoggleGrid grid = new BoggleGrid(4);
        grid.initalizeBoard("RHLDNHTGIPHSNMJO");
        GridIterator iterator = grid.getIterator();
        String actual = "";
        while (iterator.hasNext()) {
            Position position = iterator.next();
            actual = actual.concat(Character.toString(grid.getCharAt(
                    position.getRow(), position.getCol())));
        }
        assertEquals("RHLDNHTGIPHSNMJO", actual);
    }
}