import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int[] range : intervals) {
            int start = range[0];
            int end = range[1] + 1;
            
            for (int i = start; i < end; i++) {
                int temp = arr[i];
                list.add(temp);
            }
        }
        
        int[] ans = list.stream().mapToInt(j->j).toArray();
        return ans;
    }
}