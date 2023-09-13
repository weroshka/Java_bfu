import java.util.Scanner;

public class main_4 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите количество дорог");
        int num_road = console.nextInt();
        int j =0, max_x = 0, max_num = 0;
        while(j<=num_road){
            int i=0;
            System.out.println("Введите количество тоннелей");
            int tunnels = console.nextInt();
            int min_n = 0, flag_min_n =0;
            while(i != tunnels){
                System.out.println("Высота тоннелей в см ");
                int height = console.nextInt();
                if (flag_min_n == 0){
                    min_n = height;
                    flag_min_n =1;
                }
                else if (height < min_n){
                    min_n = height;
                }
                i++;
            }
            if (min_n > max_x){
                max_x = min_n;
                max_num = j;
            }
            j++;
            System.out.println(max_num);
            System.out.println(max_x);
        }

    }
}
