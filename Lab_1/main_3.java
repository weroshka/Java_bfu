import java.util.Scanner;

public class main_3 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите координату x1");
        int x1 = console.nextInt();
        System.out.println("Введите координату y1");
        int y1 = console.nextInt();
        int x = 0, y = 0, counter = 0, result = 0;

        while (true) {
            System.out.println("Введите направление");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            if (command.equals("стоп")){
                break;
            }
            System.out.println("Введите количество шагов");
            int n = console.nextInt();
            if ((x1 == x) & (y1 == y)){
                result = counter;
            }

            if (command.equals("запад")) {
                x = x - n;
                counter++;
            }
            else if (command.equals("восток")) {
                x = x + n;
                counter++;
            }
            else if (command.equals("юг")) {
                y = y - n;
                counter++;
            }
            else if (command.equals("север")) {
                y = y + n;
                counter++;
            }
        }
        System.out.println(result);
    }
}
