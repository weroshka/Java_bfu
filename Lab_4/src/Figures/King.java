package Figures;

public class King extends Figure{
    public King(String name, char color, Figure[][] fields) {
        super(name, color, fields);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if(!super.canMove(row, col, row1, col1)){
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = fields[i][j];
                if (!(i == row && j == col) && (Math.abs(row - i) <= 1 && Math.abs(col - j) <= 1) && figure instanceof King) {
                    return false;
                }
            }
        }

        return (Math.abs(row - row1) == 1 && col == col1) || (row == row1 && Math.abs(col - col1) == 1) || ((Math.abs(row - row1) == 1 && Math.abs(col - col1) == 1));
    }
    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return this.canMove(row, col, row1, col1);
    }
}
