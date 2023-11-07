package Figures;

public class Queen extends Figure {
    private final Figure[][] fields;
    public Queen(String name, char color, Figure[][] fields) {
        super(name, color);
        this.fields = fields;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (super.canMove(row, col, row1, col1)) {
            int rowDiff = Math.abs(row - row1);
            int colDiff = Math.abs(col - col1);

            // Диагональное движение
            if (rowDiff == colDiff) {
                return true;
            }

            // Горизонтальное или вертикальное движение
            if (row == row1 || col == col1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if (canMove(row, col, row1, col1)) {
            // Проверка, что целевая фигура не является королем
            Figure targetFigure = fields[row1][col1];
            if (targetFigure != null) {
                return true;
            }
        }
        return false;
    }
}
