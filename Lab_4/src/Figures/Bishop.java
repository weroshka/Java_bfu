package Figures;

public class Bishop extends Figure{
    private final Figure[][] fields;
    public Bishop(String name, char color, Figure[][] fields) {
        super(name, color);
        this.fields = fields;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
       if (Math.abs(row - row1) == Math.abs(col-col1)){
           int rowDirection, colDirection;

           if (row1 - row > 0) {
               rowDirection = 1;
           } else {
               rowDirection = -1;
           }

           if (col1 - col > 0) {
               colDirection = 1;
           } else {
               colDirection = -1;
           }
           for (int i = row + rowDirection, j = col + colDirection; i != row1; i += rowDirection, j += colDirection) {
               if (fields[i][j] != null) {
                   return false;
               }
           }

           return true;
       }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if (canMove(row, col, row1, col1)) {
            Figure targetFigure = fields[row1][col1];
            if (targetFigure != null && targetFigure.getColor() != this.getColor() ) {
                return true;
            }
        }
        return false;
    }
}
