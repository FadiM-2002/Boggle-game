package boggle;

/*
An iterator object that will be used to iterate through all the positions in the boggle grid
 */
public class BoggleIterator implements GridIterator{
    private char[][] board; // boggle grid that will be iterated over
    private int row = 0; // the row position in the grid
    private int col = 0; // the column position in the grid
    public BoggleIterator(char[][] board) {
        this.board = board;
    }
    // returns false of the current position is the last position in the grid and true otherwise
    @Override
    public boolean hasNext() {
        return row < board.length && col < board.length;
    }
    // returns the next position in the grid
    @Override
    public Position next() {
        Position position = new Position(row, col);
        if (col + 1 == board.length) {
            col = 0;
            row++;
        }
        else col++;
        return position;
    }
}
