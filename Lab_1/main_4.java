import java.util.Scanner;

public class main_4{
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите количество дорог");
        int num_road = console.nextInt();
        int max = 0, roads = 1;
        for (int road = 0; road < num_road; road++){
            System.out.println("Введите количество тоннелей");
            int tunnels = console.nextInt();
            int m = 1000000;
            for (int tunnel = 0; tunnel < tunnels; tunnel++){
                System.out.println("Высота тоннелей в см ");
                int height = console.nextInt();
                if (m > height){
                    m = height;
                }
            }
            if (max < m){
                max = m;
                roads = road +1;
            }
        }
        System.out.println(roads);
        System.out.println(max);
    }
}
