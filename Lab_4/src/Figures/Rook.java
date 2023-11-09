package Figures;

public class Rook extends Figure{
    public Rook(String name, char color, Figure[][] fields) {
        super(name, color, fields);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if(super.canMove(row, col, row1, col1)){
            if(row == row1) {
                int colDirection = (col < col1)? 1 : -1;

                for (int i = col + colDirection; i * colDirection < col1 * colDirection; i+=colDirection) {
                    if(fields[row][i] != null)
                        return false;
                }

                return true;
            }

            if(col == col1){
                int rowDirection = (row < row1)? 1 : -1;

                for (int i = row + rowDirection; i * rowDirection < row1 * rowDirection; i += rowDirection) {
                    if(fields[i][col] != null)
                        return false;
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return this.canMove(row, col, row1, col1);
    }
}
