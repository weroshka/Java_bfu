import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w', fields), new Knight("N", 'w', fields),
                new Bishop("B", 'w', fields), new Queen("Q", 'w', fields),
                new King("K", 'w'), new Bishop("B", 'w', fields),
                new Knight("N", 'w', fields), new Rook("R", 'w', fields)
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w', fields), new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields), new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields), new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields), new Pawn("P", 'w', fields),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b', fields), new Knight("N", 'b', fields),
                new Bishop("B", 'b', fields), new Queen("Q", 'b', fields),
                new King("K", 'b'), new Bishop("B", 'b', fields),
                new Knight("N", 'b', fields), new Rook("R", 'b', fields)
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b', fields), new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields), new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields), new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields), new Pawn("P", 'b', fields),
        };
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2) {
        Figure figure = this.fields[row1][col1];

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2] == null) {
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null
                && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor()) {
            switch (this.fields[row2][col2].getColor()) {
                case 'w':
                    this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
                case 'b':
                    this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }

        return false;
    }

    public void print_board() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }
    }
    public boolean isCheck(char color) {
        int kingRow = -1, kingCol = -1;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure instanceof King && figure.getColor() == color) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j] != null && fields[i][j].getColor() != color && fields[i][j].canAttack(i, j, kingRow, kingCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(char color) {
        if (!isCheck(color)) {
            return false;
        }
        Figure[][] board = fields.clone();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = fields[i][j];
                if (figure != null && fields[i][j].getColor() == color) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            Figure figureNew = fields[newRow][newCol];
                            if (move_figure(i, j, newRow, newCol)) {
                                if (!isCheck(color)) {
                                    fields[newRow][newCol] = figureNew;
                                    fields[i][j] = figure;
                                    return true;
                                }
                                fields[newRow][newCol] = figureNew;
                                fields[i][j] = figure;
                            }
                        }
                    }
                }
            }
        }
        fields = board;
        return false;
    }
}
