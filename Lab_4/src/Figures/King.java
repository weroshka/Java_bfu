package Figures;

public class King extends Figure {
    public King(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (row1 >= 0 && row1 < 8 && col1 >= 0 && col1 < 8) {
            int rowDiff = Math.abs(row - row1);
            int colDiff = Math.abs(col - col1);

            return (rowDiff <= 1 && colDiff <= 1);
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return false;
    }
}