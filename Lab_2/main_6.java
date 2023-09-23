import java.util.Scanner;

public class main_6 {
    public static void main(String[] args){
        System.out.println("Input the amount of LINES ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println("Input the amount of COLUMNS");
        int m = in.nextInt();
        int input_num =0;
        int[][] in_arr = new int[n][m];
        System.out.println("Fill in the array");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                input_num = in.nextInt();
                in_arr[i][j] = input_num;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += in_arr[i][j];
            }
        }
        System.out.println(sum);
    }
}
