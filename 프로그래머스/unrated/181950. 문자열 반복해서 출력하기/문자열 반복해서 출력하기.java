import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = sc.nextInt();
        
        String total = new String();
        
        if (str.length() >= 1 && str.length() <= 10 && n >= 1 && n <= 5) {
            for(int i = 0; i < n; i++)
                total = total.concat(str);
        }
        
        System.out.println(total);
        sc.close();
    }
}