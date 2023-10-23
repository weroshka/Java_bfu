public class CinemaHall {

    private final String name;
    private int width;
    private int length;
    private char[][] seats;

    public CinemaHall(String newName, int newWidth, int newLength, char[][] newSeats){
        name = newName;
        width = newWidth;
        length = newLength;
        seats = newSeats;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public char[][] getSeats() {
        return seats;
    }

    public void setSeats(char[][] newSeats){
        seats = newSeats;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    public void setLength(int newLength) {
        length = newLength;
    }

    public void printSeats(){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(seats[j][i] + " ");
            }
            System.out.print("\n");
        }
    }
}
