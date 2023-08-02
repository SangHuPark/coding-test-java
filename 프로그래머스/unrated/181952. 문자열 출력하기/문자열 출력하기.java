import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int len = str.length();
        
        if (len >= 1 && len <= 1000000 && !str.contains(" "))
            System.out.println(str);
        
        sc.close();
    }
}