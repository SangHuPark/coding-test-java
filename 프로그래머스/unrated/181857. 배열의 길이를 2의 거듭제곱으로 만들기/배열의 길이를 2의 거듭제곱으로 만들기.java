import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        
        while (count < arr.length) {
            count *= 2;
        }
        
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        
        for (int i = arr.length; i < count; i++) {
            list.add(0);
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
}