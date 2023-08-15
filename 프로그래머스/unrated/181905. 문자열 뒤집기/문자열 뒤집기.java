import java.util.*;

class Solution {
    public String solution(String my_string, int s, int e) {
        char[] ans = my_string.toCharArray();
        
        for (int i = (s + e) / 2; i >= s; i--) {
            char temp = ans[i];
            ans[i] = ans[(s + e) - i];
            ans[(s + e) - i] = temp;
        }
        
        return new String(ans);
    }
}