import java.util.Scanner;

public class main_3 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите число x1");
        int x1 = console.nextInt();
        System.out.println("Введите число y1");
        int y1 = console.nextInt();
        int x= 0, y=0, direction=0, counter=0, flag = 0;
        String[] ms ={"север","запад","юг", "восток"};
        String[] dr = {"налево", "направо", "разворот", "стоп"};
        while (flag == 0){
            System.out.println("Введите команду");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            if (command.equals("вперед")) {
                int n = console.nextInt();
                if (direction == 0) {
                    y = y + n;
                } else if (direction == 1) {
                    x = x - n;
                } else if (direction == 2) {
                    y = y - n;
                } else if (direction == 3) {
                    x = x + n;
                }
            }

            else if (command.equals("налево") || command.equals("направо") || command.equals("разворот")) {
                if (command.equals("налево")) {
                    direction = (direction + 1) % 4;
                }
                if (command.equals("направо")) {
                    direction = (direction - 1) % 4;
                }
                if (command.equals("разворот")) {
                    direction = (direction + 2) % 4;
                }


            }


            else if (command.equals("стоп")){

                    flag=1;}
            counter++;
            }

        if((x1==x) & (y1 == y)){
            System.out.println(counter);
            }



        }
}
