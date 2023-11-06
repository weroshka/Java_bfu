package Figures;

public class Rook extends Figure {
    private final Figure[][] fields;
    public Rook(String name, char color, Figure[][] fields) {
        super(name, color);
        this.fields = fields;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (super.canMove(row, col, row1, col1)) {
            // Ладья двигается по вертикали или горизонтали
            if (row == row1 || col == col1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        // Ладья атакует точно так же, как и двигается
        if (canMove(row, col, row1, col1)) {
            // Добавьте проверку на король
            if (!(fields[row1][col1] instanceof King)) {
                return true;
            }
        }
        return false;
    }
}
