package Figures;

public class Queen extends Figure{
    public Queen(String name, char color, Figure[][] fields) {
        super(name, color, fields);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if((row >=0 && row < 8 ) && (col >=0 && col < 8) && (row1 >=0 && row1 < 8 ) && (col1 >=0 && col1 < 8) ){
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
