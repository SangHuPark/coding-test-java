import java.util.*;

class Solution {
    public int[] solution(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int idx = 0;
        int leng = array.length;
        
        Arrays.sort(arr);
        
        for (int i = 0; i < leng; i++) {
            if (array[i] == arr[leng - 1]) {
                idx = i;
                // System.out.println(array[i] + ", " + i);
            }
            System.out.println(array[i] + ", " + i);
        }
        
        return new int[] {arr[leng - 1], idx};
    }
}