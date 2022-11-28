package boggle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the rotateGrid method which implement the user story 'rotate grid'
 */
class RotateGridTest {

    //Testing rotateGrid method with grid of size 4
    @Test
    void TestRotateGrid1() {
        BoggleGrid grid1 = new BoggleGrid(4);
        grid1.initalizeBoard("abcdefghijklmnop");
        BoggleGrid actualGrid = grid1.rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(4);
        expectedGrid.initalizeBoard("dhlpcgkobfjnaeim");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }

    //Testing rotateGrid method with grid of size 5
    @Test
    void TestRotateGrid2() {
        BoggleGrid grid2 = new BoggleGrid(5);
        grid2.initalizeBoard("abcdefghijklmnopqrstuvwxy");
        BoggleGrid actualGrid = grid2.rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(5);
        expectedGrid.initalizeBoard("ejotydinsxchmrwbglqvafkpu");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }

    //Rotating the board twice with a grid of size 4
    @Test
    void TestRotateGridTwoTimes() {
        BoggleGrid grid1 = new BoggleGrid(4);
        grid1.initalizeBoard("abcdefghijklmnop");
        BoggleGrid actualGrid = grid1.rotateGrid().rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(4);
        expectedGrid.initalizeBoard("ponmlkjihgfedcba");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }

    //Rotating the board twice with a grid of size 5
    @Test
    void TestRotateGridTwoTimes2() {
        BoggleGrid grid2 = new BoggleGrid(5);
        grid2.initalizeBoard("abcdefghijklmnopqrstuvwxy");
        BoggleGrid actualGrid = grid2.rotateGrid().rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(5);
        expectedGrid.initalizeBoard("yxwvutsrqponmlkjihgfedcba");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }

    //Rotating the board four times (returns to initial state) with a grid of size 4
    @Test
    void TestRotateGrid360Degree() {
        BoggleGrid grid1 = new BoggleGrid(4);
        grid1.initalizeBoard("abcdefghijklmnop");
        BoggleGrid actualGrid = grid1.rotateGrid().rotateGrid().rotateGrid().rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(4);
        expectedGrid.initalizeBoard("abcdefghijklmnop");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }

    //Rotating the board four times (returns to initial state) with a grid of size 5
    @Test
    void TestRotateGrid360Degree2() {
        BoggleGrid grid2 = new BoggleGrid(5);
        grid2.initalizeBoard("abcdefghijklmnopqrstuvwxy");
        BoggleGrid actualGrid = grid2.rotateGrid().rotateGrid().rotateGrid().rotateGrid();
        BoggleGrid expectedGrid = new BoggleGrid(5);
        expectedGrid.initalizeBoard("abcdefghijklmnopqrstuvwxy");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(expectedGrid.getCharAt(i, j), actualGrid.getCharAt(i, j));
            }
        }
    }
}