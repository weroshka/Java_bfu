import java.util.Scanner;
public class main_3 {
    public static void main(String[] args){
        System.out.println("Input amount of elements in your array ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int input =0;
        int[] array = new int[n];
        System.out.println("Input numbers ");
        for(int i = 0; i<n; ++i){
            input = in.nextInt();
            array[i] = input;
        }
        // сохраняет максимальный суммарный подмассив, найденный на данный момент
        int maxCurr = 0;
        // сохраняет максимальную сумму подмассива, заканчивающегося на текущей позиции
        int maxEnd = 0;
        for(int i : array){
            maxEnd = maxEnd + i;
            maxEnd = Integer.max(maxEnd, 0);

            // обновить результат, если текущая сумма подмассива окажется больше
            maxCurr = Integer.max(maxCurr, maxEnd);

        }
        System.out.println(maxCurr);
    }
}
