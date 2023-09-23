import java.util.Arrays;
import java.util.Scanner;

public class main_2 {
    public static void main(String[] args){
        System.out.println("Input amount of numbers in the 1st array");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println("Input " + n +" numbers");
        int[] firstArr = new int[n];
        int input = 0;
        for (int i = 0; i < n; i++){
            input = in.nextInt();
            firstArr[i] = input;
        }
        Arrays.sort(firstArr);

        System.out.println("Input amount of numbers in the 2st array");
        int m = in.nextInt();
        System.out.println("Input " + m +" numbers");
        int[] secondArr = new int[m];
        for(int i = 0; i < m; i++){
            input = in.nextInt();
            secondArr[i] = input;
        }
        Arrays.sort(secondArr);
        int newLen = n+m;
        int[] newArr = new int[newLen];
        System.arraycopy(firstArr, 0, newArr, 0, firstArr.length);
        System.arraycopy(secondArr, 0, newArr, firstArr.length, secondArr.length);
        Arrays.sort(newArr);
        System.out.println("Новый массив " + Arrays.toString(newArr));
    }
}
