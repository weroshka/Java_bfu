package Figures;

public class Bishop extends Figure{
    public Bishop(String name, char color, Figure[][] fields) {
        super(name, color, fields);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if(Math.abs(row - row1) == Math.abs(col - col1) && !(row == 7 && row1 == 0) && !(row == 0 && row1 == 7) && super.canMove(row, col, row1, col1)){
            int rowDirection = (row < row1)? 1 : -1;
            int colDirection = (col < col1)? 1 : -1;
            for (int i = row + rowDirection, j = col + colDirection; (i * rowDirection) < (row1 * rowDirection) & (j * colDirection) < (col1 * colDirection); i += rowDirection, j += colDirection){
                if(fields[i][j] != null)
                    return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return this.canMove(row, col, row1, col1);
    }
}
