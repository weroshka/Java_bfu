import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Board {
    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    private Scanner scanner;

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void promotePawn(int row, int col, Figure promotionFigure) {
        fields[row][col] = promotionFigure;
    }

    public Board(Scanner scanner){
        this.scanner = scanner;
    }

    public Figure[][] getFields() {
        return fields;
    }

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w',this.fields), new Knight("N", 'w',this.fields),
                new Bishop("B", 'w',this.fields), new Queen("Q", 'w',this.fields),
                new King("K", 'w',this.fields), new Bishop("B", 'w',this.fields),
                new Knight("N", 'w',this.fields), new Rook("R", 'w',this.fields)
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w',this.fields), new Pawn("P", 'w',this.fields),
                new Pawn("P", 'w',this.fields), new Pawn("P", 'w',this.fields),
                new Pawn("P", 'w',this.fields), new Pawn("P", 'w',this.fields),
                new Pawn("P", 'w',this.fields), new Pawn("P", 'w',this.fields),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b',this.fields), new Knight("N", 'b',this.fields),
                new Bishop("B", 'b',this.fields), new Queen("Q", 'b',this.fields),
                new King("K", 'b',this.fields), new Bishop("B", 'b',this.fields),
                new Knight("N", 'b',this.fields), new Rook("R", 'b',this.fields)
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b',this.fields), new Pawn("P", 'b',this.fields),
                new Pawn("P", 'b',this.fields), new Pawn("P", 'b',this.fields),
                new Pawn("P", 'b',this.fields), new Pawn("P", 'b',this.fields),
                new Pawn("P", 'b',this.fields), new Pawn("P", 'b',this.fields),
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

            if(isCheck(colorGaming)) {
                this.fields[row2][col2] = null;
                this.fields[row1][col1] = figure;
                return false;
            }
            figure = this.fields[row2][col2];

            if (figure instanceof Pawn && (row2 == 0 || row2 == 7)) {
                System.out.println("Выберите фигуру для превращения: (R - Rook, N - Knight, B - Bishop, Q - Queen)");
                String promotionChoice = scanner.nextLine();
                Figure promotionFigure = null;
                switch (promotionChoice) {
                    case "R":
                        promotionFigure = new Rook("R", figure.getColor(), fields);
                        break;
                    case "N":
                        promotionFigure = new Knight("N", figure.getColor(), fields);
                        break;
                    case "B":
                        promotionFigure = new Bishop("B", figure.getColor(), fields);
                        break;
                    case "Q":
                        promotionFigure = new Queen("Q", figure.getColor(), fields);
                        break;
                    default:
                        promotionFigure = new Queen("Q", figure.getColor(), fields);
                }
                promotePawn(row2, col2, promotionFigure);
            }

            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null
                && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor()
                && !(this.fields[row2][col2] instanceof King)) {

            switch ( this.fields[row2][col2].getColor()) {
                case 'w':
                    this.takeWhite.add( this.fields[row2][col2].getColor() +  this.fields[row2][col2].getName());
                    break;
                case 'b':
                    this.takeBlack.add( this.fields[row2][col2].getColor() +  this.fields[row2][col2].getName());
                    break;
            }
            Figure figureAttacked = this.fields[row2][col2];

            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;

            if(isCheck(colorGaming)) {
                this.fields[row2][col2] = figureAttacked;
                this.fields[row1][col1] = figure;
                switch (figureAttacked.getColor()) {
                    case 'w':
                        this.takeWhite.remove(figureAttacked);
                        break;
                    case 'b':
                        this.takeBlack.remove(figureAttacked);
                        break;
                }
                return false;
            }

            figure = this.fields[row2][col2];

            if (figure instanceof Pawn && (row2 == 0 || row2 == 7)) {
                System.out.println("Выберите фигуру для превращения: (R - Rook, N - Knight, B - Bishop, Q - Queen)");
                String promotionChoice = scanner.nextLine();
                Figure promotionFigure = null;
                switch (promotionChoice) {
                    case "R":
                        promotionFigure = new Rook("R", figure.getColor(), fields);
                        break;
                    case "N":
                        promotionFigure = new Knight("N", figure.getColor(), fields);
                        break;
                    case "B":
                        promotionFigure = new Bishop("B", figure.getColor(), fields);
                        break;
                    case "Q":
                        promotionFigure = new Queen("Q", figure.getColor(), fields);
                        break;
                    default:
                        promotionFigure = new Queen("Q", figure.getColor(), fields);
                }
                promotePawn(row2, col2, promotionFigure);
            }

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
        Figure[][] fieldsOld = fields.clone();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j] != null && fields[i][j].getColor() == color) {
                    Figure figureStart = fields[i][j];
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            Figure figure = fields[newRow][newCol];
                            if (move_figure(i, j, newRow, newCol)) {
                                if (!isCheck(color)) {
                                    fields[newRow][newCol] = figure;
                                    fields[i][j] = figureStart;
                                    return false;
                                }
                                fields[newRow][newCol] = figure;
                                fields[i][j] = figureStart;
                            }
                        }
                    }
                }
            }
        }

        fields = fieldsOld;
        return true;
    }
}
