import java.util.Scanner;

import static java.lang.Math.max;

class Main
{
    public static int sequence(int n) {
        int s = 0;
        int m = n;
        while (n > 1){
            if (n % 2 != 0){
                n = (n * 3) + 1;
            }
            else{
                n /= 2;
            }
            s++;
            m = max(m, n);

        }
        return (s);
    }

    public static void main(String[] args)  {
        System.out.println("Введите число n");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int result = sequence(n);
        System.out.println("Количество шагов");
        System.out.println(result);
    }
}
