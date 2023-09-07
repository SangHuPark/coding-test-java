import java.util.*;

class Solution {
    public int solution(int[] date1, int[] date2) {
//         String str1 = new String();
//         String str2 = new String();
        
//         for (int i = 0; i < 3; i++) {
//             str1 += String.valueOf(date1[i]);
//             str2 += String.valueOf(date2[i]);
//         }
        
//         int num1 = Integer.parseInt(str1);
//         int num2 = Integer.parseInt(str2);
        
//         return num1 < num2 ? 1 : 0;
        
        return Arrays.compare(date1, date2) < 0 ? 1 : 0;
    }
}