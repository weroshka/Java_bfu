package Figures;

public class Pawn extends Figure{
    private final Figure[][] fields;

    private boolean isFirstStep = true;

    public Pawn(String name, char color, Figure[][] fields) {
        super(name, color);
        this.fields = fields;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) {
            return false;
        }

        int rowDiff = Math.abs(row - row1);

        if (this.getColor() == 'w') {
            if (isFirstStep) {
                if ((rowDiff == 1 || rowDiff == 2) && col == col1) {
                    isFirstStep = false;
                    return true;
                }
            }
            else {
                if (rowDiff == 1 && col == col1)
                {
                    return true;
                }
            }
        }
        else if (this.getColor() == 'b') {
            if (isFirstStep) {
                if ((rowDiff == 1 || rowDiff == 2) && col == col1) {
                    isFirstStep = false;
                    return true;
                }
            } else {
                if (rowDiff == 1 && col == col1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1){
        if (super.canAttack(row, col, row1, col1)) {
            int rowDiff = Math.abs(row - row1);
            int colDiff = Math.abs(col - col1);

            if (rowDiff == 1 && colDiff == 1) {
                Figure targetFigure = fields[row1][col1];
                if (targetFigure != null)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
