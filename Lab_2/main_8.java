import java.util.Scanner;

public class main_8 {
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
        int[][] out_arr = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                out_arr[n-j-1][i] = in_arr[i][j];
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.print(out_arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
