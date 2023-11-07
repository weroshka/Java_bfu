package Figures;

public class Knight extends Figure{
    private final Figure[][] fields;
    public Knight(String name, char color, Figure[][] fields) {
        super(name, color);
        this.fields = fields;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        int rowDiff = Math.abs(row - row1);
        int colDiff = Math.abs(col - col1);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            int stepRow;
            if (row < row1) {
                stepRow = 1;
            } else {
                stepRow = -1;
            }

            int stepCol;
            if (col < col1) {
                stepCol = 1;
            } else {
                stepCol = -1;
            }

            int checkRow = row + stepRow;
            int checkCol = col + stepCol;

            if (fields[checkRow][checkCol] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if (canMove(row, col, row1, col1)) {
            Figure targetFigure = fields[row1][col1];
            if (targetFigure != null) {
                if (targetFigure.getColor() != this.getColor()) {
                    return true;
                }
            }
        }
        return false;
    }
}

