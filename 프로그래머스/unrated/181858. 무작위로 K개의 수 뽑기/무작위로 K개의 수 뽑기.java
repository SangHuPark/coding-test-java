import java.util.*;

class Solution {
    public int[] solution(int[] arr, int k) {
        arr = Arrays.stream(arr).distinct().toArray();
        int[] ans = new int[k];
        Arrays.fill(ans, -1);
        int count = 0;
        
        for (int num : arr) {
            if (count == k)
                break;
            
            ans[count++] = num;
        }
    
        return ans;
    }
}