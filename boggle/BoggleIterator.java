package boggle;

public class BoggleIterator implements GridIterator{
    private char[][] board;
    private int row = 0;
    private int col = 0;
    public BoggleIterator(char[][] board) {
        this.board = board;
    }

    @Override
    public boolean hasNext() {
        return row < board.length && col < board.length;
    }
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
