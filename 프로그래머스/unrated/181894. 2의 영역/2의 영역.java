import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int start = -1;
        int end = -1;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                start = i;
                break;
            }
        }
        
        if (start == -1) {
            int[] ans = {start};
            return ans;
        }
        
        for (int j = arr.length-1; j >= 0; j--) {
            if (arr[j] == 2) {
                end = j;
                break;
            }
        }
        
        
        for (int t = start; t <= end; t++)
            list.add(arr[t]);
        
        int[] ans = list.stream().mapToInt(n->n).toArray();
        
        return ans;
    }
}