import java.util.Scanner;

public class main_5 {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Введите трехзначное число: ");
        boolean chetn = false;
        boolean chetn_1 = false;
        int sum = 0;
        int mult = 1;
        int number = console.nextInt();
        String check = Integer.toString(number);
        if (check.length() < 3){
            System.out.println("Число введено неверно! ");
            return;
        }
        int first = number / 100;
        int second = (number / 10) % 10;
        int third = number % 10;

        mult = first*second*third;
        sum = first+second+third;

        if (sum % 2 ==0){
            chetn = true;
        }
        if (mult % 2 == 0){
            chetn_1 = true;
        }

        if (chetn_1 & chetn){
            System.out.println("Число дважды четное");
        }
        else{
            System.out.println("Число НЕ дважды четное!!");
        }
    }
}
