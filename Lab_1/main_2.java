import java.util.Scanner;

public class main_2 {
    public static void main(String[] args)  {
        System.out.println("Введите число членов последовательности");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        if (n<0){
            System.out.println("Введено неверное число!");
        }
        int result = 0;
        boolean is_added = true;
        while (n>0){
            System.out.println("Введите число ");
            int m = console.nextInt();
            if (is_added) {
                result += m;
                is_added = false;
            }
            else{
                result -= m;
                is_added = true;
            }
            n--;
        }
        System.out.println("Сумма ряда: ");
        System.out.println(result);
    }

}
