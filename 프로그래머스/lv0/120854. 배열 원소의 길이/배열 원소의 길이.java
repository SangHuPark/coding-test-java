import java.util.*;

class Solution {
    public int[] solution(String[] strList) {
//         int[] answer = new int[strlist.length];
        
//         for (int i = 0; i < strlist.length; i++) {
//             answer[i] = strlist[i].length();
//         }
        
//         return answer;
        
        return Arrays.stream(strList).mapToInt(String::length).toArray();
    }
}