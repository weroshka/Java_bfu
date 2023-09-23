import java.util.Arrays;
import java.util.Scanner;

public class main_1 {
    public static String function1(String str){
        int start = 0, end = 0, maxLen = 0;
        int[] checked = new int[128];
        Arrays.fill(checked, -1);
        for (int i = 0; i < str.length(); ++i){
            //если символ уже встречался в строке
            if (checked[str.charAt(i)] >= start){
                //объявляем новое начало строки
                start = checked[str.charAt(i)]+1;
            }
            checked[str.charAt(i)] = i;
            if (i - start + 1 > maxLen){
                maxLen = i - start + 1;
                end = i;
            }

        }
        return str.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println("Введите строку ");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String b = function1(command);
        System.out.println(b);
    }
}


//решение без использования статического массива
/*import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class main_1 {
    //отдельная функция, которая будет считать макс подстроку
    public static int function1(String str){
        int n = str.length();
        int start = 0, end = 0;
        int maxLength = 0;
        //инициализируем множество уникальных символов letters
        Set<Character> letters = new HashSet<>();
        while (end < str.length()){
            //charAt возвращает значение по указанному индексу
            //letters.add добавляет элемент в множество, если он отсутствует
            if (letters.add(str.charAt(end))){
                end++;
                maxLength = Math.max(maxLength, letters.size());
            }
            else{
                letters.remove(str.charAt(start));
                start++;
            }
        }
        // Возвращаем длину самой длинной подстроки без повторяющихся символов
        return maxLength;
        }

    public static void main(String[] args) {
        System.out.println("Введите строку");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        int b = function1(command);
        System.out.println(b);
    }
}
*/