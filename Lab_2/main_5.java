import java.util.Scanner;

public class main_5 {

    public static void main(String[] args){
        System.out.println("Input the amount of elements in array");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int input_num =0;
        Integer not_found = null;
        int[] arr = new int[n];
        System.out.println("Input elements ");
        for(int i = 0; i< n; i++){
            input_num = in.nextInt();
            arr[i] = input_num;
        }
        boolean find = true;
        System.out.println("Input TARGET");
        int target = in.nextInt();
        for(int i = 0; i<n-1; i++){
            for(int j =0; j<n; j++){
                if(arr[i]+arr[j] == target){
                    System.out.println("The couple is " + arr[i] + " " + arr[j]);
                    find = false;
                }

            }
        }
        if (find){
            System.out.println(not_found);
        }
    }
}
