package boggle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GridPrototypeTest {

    //Tests the clone method with a Boggle grid of size 4
    @Test
    void TestClone1() {
        BoggleGrid originalGrid = new BoggleGrid(4);
        originalGrid.initalizeBoard("IseemToBeHavinga");
        BoggleGrid clonedGrid = originalGrid.clone();

        assertEquals(originalGrid.numCols(), clonedGrid.numCols());
        assertEquals(originalGrid.numRows(), clonedGrid.numRows());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(originalGrid.getCharAt(i, j), clonedGrid.getCharAt(i, j));
            }
        }
    }

    //Tests the clone method with a Boggle grid of size 5
    @Test
    void TestClone2() {
        BoggleGrid originalGrid = new BoggleGrid(5);
        originalGrid.initalizeBoard("abcedfghijklmnopqrstuvwxy");
        BoggleGrid clonedGrid = originalGrid.clone();

        assertEquals(originalGrid.numCols(), clonedGrid.numCols());
        assertEquals(originalGrid.numRows(), clonedGrid.numRows());

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(originalGrid.getCharAt(i, j), clonedGrid.getCharAt(i, j));
            }
        }
    }

    //Tests the addGrid and getGridByName methods of the GridPrototypeRegistry class
    @Test
    void TestGridPrototypeRegistry() {
        GridPrototypeRegistry registry = new GridPrototypeRegistry();

        BoggleGrid grid1 = new BoggleGrid(4);
        grid1.initalizeBoard("hhseklnmnooqavvc");
        registry.addGrid("north", grid1);

        BoggleGrid grid2 = new BoggleGrid(4);
        grid2.initalizeBoard("hhseklnmnooqavvc");
        registry.addGrid("Friday", grid2);

        BoggleGrid grid3 = new BoggleGrid(5);
        grid3.initalizeBoard("hhseklnmnooqavvctrehdlsig");
        registry.addGrid("no name", grid3);

        BoggleGrid grid4 = new BoggleGrid(4);
        grid4.initalizeBoard("yesgoodafternoon");
        registry.addGrid("He fought the windmill", grid4);

        BoggleGrid clonedGrid3 = registry.getGridByName("no name");
        BoggleGrid clonedGrid4 = registry.getGridByName("He fought the windmill");
        BoggleGrid clonedGrid1 = registry.getGridByName("north");
        BoggleGrid clonedGrid2 = registry.getGridByName("Friday");


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(grid1.getCharAt(i, j), clonedGrid1.getCharAt(i, j));
                assertEquals(grid2.getCharAt(i, j), clonedGrid2.getCharAt(i, j));
                assertEquals(grid4.getCharAt(i, j), clonedGrid4.getCharAt(i, j));
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(grid3.getCharAt(i, j), clonedGrid3.getCharAt(i, j));
            }
        }

    }


}
