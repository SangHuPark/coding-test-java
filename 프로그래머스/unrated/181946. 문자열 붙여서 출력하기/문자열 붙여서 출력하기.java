import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        
        String output = "";
        
        if (str1.length() >= 1 && str1.length() <= 10 
            && str2.length() >= 1 && str2.length() <= 10) {
            output = str1.concat(str2);
            System.out.println(output);
        }
    }
}