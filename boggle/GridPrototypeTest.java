package boggle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the methods related to user story 1.4 (copy grid)
 */
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

    //Tests the addGrid method in the GridPrototypeRegistry class
    @Test
    void TestAddGrid() {
        BoggleGrid originalGrid = new BoggleGrid(4);
        originalGrid.initalizeBoard("IseemToBeHavinga");
        GridPrototypeRegistry gridRegistry = new GridPrototypeRegistry();
        gridRegistry.addGrid("first saved grid!!", originalGrid);
        assertEquals(1, gridRegistry.getSavedGridNum());

        BoggleGrid grid2 = new BoggleGrid(4);
        originalGrid.initalizeBoard("IseemToBeaaaaaaa");
        gridRegistry.addGrid("2nd grid", grid2);
        assertEquals(2, gridRegistry.getSavedGridNum());

        BoggleGrid grid3 = new BoggleGrid(4);
        originalGrid.initalizeBoard("IseemToBeBeBeBeB");
        gridRegistry.addGrid("grid three", grid3);
        assertEquals(3, gridRegistry.getSavedGridNum());

    }

    //Tests the getGridByName method of GridPrototypeRegistry class
    @Test
    void TestGetGridByName() {
        GridPrototypeRegistry gridRegistry = new GridPrototypeRegistry();

        BoggleGrid grid1 = new BoggleGrid(4);
        grid1.initalizeBoard("IseemToBeHavinga");
        gridRegistry.addGrid("first saved grid!!", grid1);
        assertEquals(1, gridRegistry.getSavedGridNum());

        BoggleGrid grid2 = new BoggleGrid(4);
        grid2.initalizeBoard("IseemToBeaaaaaaa");
        gridRegistry.addGrid("2nd grid", grid2);
        assertEquals(2, gridRegistry.getSavedGridNum());

        BoggleGrid grid3 = new BoggleGrid(4);
        grid3.initalizeBoard("IseemToBeBeBeBeB");
        gridRegistry.addGrid("grid three", grid3);
        assertEquals(3, gridRegistry.getSavedGridNum());


        BoggleGrid getGrid1 = gridRegistry.getGridByName("2nd grid");
        BoggleGrid getGrid2 = gridRegistry.getGridByName("grid three");
        BoggleGrid getGrid3 = gridRegistry.getGridByName("first saved grid!!");

        BoggleGrid expected1 = new BoggleGrid(4);
        expected1.initalizeBoard("IseemToBeaaaaaaa");
        BoggleGrid expected2 = new BoggleGrid(4);
        expected2.initalizeBoard("IseemToBeBeBeBeB");
        BoggleGrid expected3 = new BoggleGrid(4);
        expected3.initalizeBoard("IseemToBeHavinga");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1.getCharAt(i, j), getGrid1.getCharAt(i, j));
                assertEquals(expected2.getCharAt(i, j), getGrid2.getCharAt(i, j));
                assertEquals(expected3.getCharAt(i, j), getGrid3.getCharAt(i, j));
            }
        }
    }

    @Test
    void TestGetGridByName2() {
        GridPrototypeRegistry gridRegistry = new GridPrototypeRegistry();

        BoggleGrid grid1 = new BoggleGrid(5);
        grid1.initalizeBoard("dostoevskyFyodorLeotolsto");
        gridRegistry.addGrid("crime and war and punishment and peace", grid1);
        assertEquals(1, gridRegistry.getSavedGridNum());

        BoggleGrid grid2 = new BoggleGrid(5);
        grid2.initalizeBoard("abcdefghijklmnopqrstuvwxy");
        gridRegistry.addGrid("alpha", grid2);
        assertEquals(2, gridRegistry.getSavedGridNum());

        BoggleGrid grid3 = new BoggleGrid(5);
        grid3.initalizeBoard("turgenevivangoncharovtwoo");
        gridRegistry.addGrid("oblomov's bed", grid3);
        assertEquals(3, gridRegistry.getSavedGridNum());

        BoggleGrid grid4 = new BoggleGrid(5);
        grid4.initalizeBoard("mrflaubertnotredamenapole");
        gridRegistry.addGrid("bovary", grid4);
        assertEquals(4, gridRegistry.getSavedGridNum());

        BoggleGrid getGrid1 = gridRegistry.getGridByName("crime and war and punishment and peace");
        BoggleGrid getGrid2 = gridRegistry.getGridByName("alpha");
        BoggleGrid getGrid3 = gridRegistry.getGridByName("oblomov's bed");
        BoggleGrid getGrid4 = gridRegistry.getGridByName("bovary");

        BoggleGrid expected1 = new BoggleGrid(5);
        expected1.initalizeBoard("dostoevskyFyodorLeotolsto");
        BoggleGrid expected2 = new BoggleGrid(5);
        expected2.initalizeBoard("abcdefghijklmnopqrstuvwxy");
        BoggleGrid expected3 = new BoggleGrid(5);
        expected3.initalizeBoard("turgenevivangoncharovtwoo");
        BoggleGrid expected4 = new BoggleGrid(5);
        expected4.initalizeBoard("mrflaubertnotredamenapole");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(expected1.getCharAt(i, j), getGrid1.getCharAt(i, j));
                assertEquals(expected2.getCharAt(i, j), getGrid2.getCharAt(i, j));
                assertEquals(expected3.getCharAt(i, j), getGrid3.getCharAt(i, j));
                assertEquals(expected4.getCharAt(i, j), getGrid4.getCharAt(i, j));
            }
        }
    }
}
