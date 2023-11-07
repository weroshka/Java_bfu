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
    private Figure  fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w',fields), new Knight("N", 'w', fields),
                new Bishop("B", 'w', fields), new Queen("Q", 'w', fields),
                new King("K", 'w'), new Bishop("B", 'w', fields),
                new Knight("N", 'w', fields),new Rook("R", 'w', fields)
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w', fields),    new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields),    new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields),    new Pawn("P", 'w', fields),
                new Pawn("P", 'w', fields),    new Pawn("P", 'w', fields),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b', fields), new Knight("N", 'b', fields),
                new Bishop("B", 'b', fields), new Queen("Q", 'b', fields),
                new King("K", 'b'), new Bishop("B", 'b', fields),
                new Knight("N", 'b', fields),new Rook("R", 'b', fields)
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b', fields),    new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields),    new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields),    new Pawn("P", 'b', fields),
                new Pawn("P", 'b', fields),    new Pawn("P", 'b', fields),
        };
    }

    public void initTest(){
        this.fields[0][3] = new King("K", 'w');

        this.fields[6][6] = new Queen("Q", 'w', fields);
        this.fields[5][2] = new Pawn("P", 'w', fields);
        this.fields[7][3] =  new King("K", 'b');
    }
    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
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
        }
        else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null
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

            if (isCheckmate(figure.getColor())) {
                System.out.println("Мат!");
            } else if (isCheck(figure.getColor())) {
                System.out.println("Шах!");
            }

            return true;
        }

        return false;
    }
    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
             }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++){
            System.out.print("    "+col);
        }


    }

    public boolean isCheck(char color) {

        int kingRow = -1;
        int kingCol = -1;

        // Найдем координаты короля указанного цвета
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
        // Проверим, атакован ли король указанного цвета
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() != color) {
                    if (figure.canAttack(row, col, kingRow, kingCol)) {
                        return true; // Король атакован
                    }
                }
            }
        }
        return false; // Король не атакован
    }
    public boolean isCheckmate(char color) {
        // Проверим, находится ли король под шахом
        if (!isCheck(color)) {
            return false; // Король не находится под шахом, поэтому нет мата
        }
        Figure[][] board = fields.clone();
        // Для каждой фигуры указанного цвета, попробуем каждый возможный ход
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() == color) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            if (move_figure(row, col, newRow, newCol)) {
                                // Если после хода король не находится под шахом, то это не мат
                                if (!isCheck(color)) {
                                    // Отменяем ход и возвращаем false
                                    fields[newRow][newCol] = fields[row][col];
                                    fields[row][col] = figure;
                                    return false;
                                }

                                // Отменяем ход
                                fields[newRow][newCol] = fields[row][col];
                                fields[row][col] = figure;

                            }
                        }
                    }
                }
            }
        }
        fields = board.clone();
        return true; // Нет доступных ходов для защиты от шаха, это мат
    }

}
