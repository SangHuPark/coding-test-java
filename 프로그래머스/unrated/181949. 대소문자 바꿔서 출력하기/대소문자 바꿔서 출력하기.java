import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        
        if (str.length() >= 1 && str.length() <= 20) {
            String output = "";
            int ascii;
            for (int i = 0; i < str.length(); i++) {
                ascii = (int) str.charAt(i);
                if (ascii < 97)
                    output += (char) (ascii + 32);
                else
                    output += (char) (ascii - 32);
            }
            System.out.println(output);
        }
        
        sc.close();
    }
}