import java.util.*;

class Solution {
    public int solution(int[] array) {
        int[] arr = new int[1000];
        Arrays.fill(arr, 0);
        
        for (int idx : array) {
            arr[idx]++;
        }
        
        int tmp = arr[0];
        int answer = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (tmp < arr[i]) {
                tmp = arr[i];
                answer = i;
            } else if (tmp == arr[i]) {
                answer = -1;
            }
        }
        
        return answer;
    }
}