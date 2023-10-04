import java.util.*;

class Solution {
    public int solution(int n) {
//         int answer = 0;
//         String strN = String.valueOf(n);
        
//         for (char ch : strN.toCharArray()) {
//             answer += ch - '0';
//         }
        
//         return answer;
        return Arrays.stream(String.valueOf(n).split(""))
            .mapToInt(Integer::parseInt).sum();
    }
}