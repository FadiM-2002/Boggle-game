package boggle;

/**
 * The BoggleGrid class for the first Assignment in CSC207, Fall 2022
 * The BoggleGrid represents the grid on which we play Boggle 
 */
public class BoggleGrid implements GridPrototype, IterableGrid{

    /**
     * size of grid
     */  
    private int size;
    /**
     * characters assigned to grid
     */      
    private char[][] board;


    /** BoggleGrid constructor
     * ----------------------
     * @param size  The size of the Boggle grid to initialize
     */
    public BoggleGrid(int size) {
        this.size = size;
        this.board = new char[size][size];
    }

    /** BoggleGrid constructor
     * ----------------------
     * @param targetGrid  The Boggle grid to clone and then initialize
     */
    public BoggleGrid(BoggleGrid targetGrid) {
        this.size = targetGrid.numCols();
        this.board = new char[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = targetGrid.getCharAt(i, j);
            }
        }
    }

    /**
     * Creates a clone of the targetGrid
     * @return a BoggleGrid which is a clone of targetGrid
     */
    @Override
    public BoggleGrid clone() {
        return new BoggleGrid(this);
    }

    /**
     * Assigns a letter in the string of letters to each grid position
     * Letters should be assigned left to right, top to bottom
     *
     * @param letters a string of letters, one for each grid position.
     */
    public void initalizeBoard(String letters) {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = letters.charAt(i * size + j);
            }
        }
    }

    /**
     * Provide a nice-looking string representation of the grid,
     * so that the user can easily scan it for words.
     *
     * @return String to print
     */
    @Override
    public String toString() {
        String boardString = "";
        for(int row = 0; row < this.size; row++){
            for(int col = 0; col < this.size; col++){
                boardString += this.board[row][col] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }

    /**
     * @return int the number of rows on the board
     */
    public int numRows() {
        return this.size;
    }

    /**
     * @return int the number of columns on the board (assumes square grid)
     */
    public int numCols() {
        return this.size;
    }

    /**
     * @return char the character at a given grid position
     */
    public char getCharAt(int row, int col) {
        return this.board[row][col];
    }

    // returns the iterator that will be used to iterate over the grid
    @Override
    public GridIterator getIterator() {
        return new BoggleIterator(board);
    }
}
