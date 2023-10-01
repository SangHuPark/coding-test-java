import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        int[] arr = new int[26];
        
        for (char ch : s.toCharArray()) {
            arr[(int)(ch - 'a')]++;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                answer += Character.toString((char)(i + 'a'));
            }
        }
        
        return answer;
    }
}