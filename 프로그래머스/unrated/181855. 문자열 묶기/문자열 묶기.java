import java.util.*;

class Solution {
    public int solution(String[] strArr) {
//         int[] arr = new int[strArr.length];
        
//         for (int i = 0; i < strArr.length; i++) {
//             arr[i] = strArr[i].length();
//         }
        
//         int max = Arrays.stream(arr).max().getAsInt();
//         int[] countArr = new int[max];
        
//         for (int num : arr) {
//             countArr[num-1]++;
//         }
        
        int[] countArr = new int[31];

        for(int i=0; i < strArr.length; i++) {
            countArr[strArr[i].length()]++;
        }

        int answer = Arrays.stream(countArr).max().getAsInt();
        
        return answer;
    }
}