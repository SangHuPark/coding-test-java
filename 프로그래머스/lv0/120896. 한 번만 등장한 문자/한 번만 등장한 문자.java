import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        int[] arr = new int[26];
        String[] strArr = s.split("");
        
        for (int i = 0; i < strArr.length; i++) {
            arr[(int) (s.charAt(i) - 'a')]++;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                answer += Character.toString((char)(i + 'a'));
            }
        }
        
        return answer;
    }
}