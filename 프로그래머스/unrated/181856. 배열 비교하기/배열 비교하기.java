import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int leng1 = arr1.length;
        int leng2 = arr2.length;
        
        if (leng1 > leng2) {
            return 1;
        } else if (leng1 < leng2) {
            return -1;
        } else {
            int sum1 = Arrays.stream(arr1).sum();
            int sum2 = Arrays.stream(arr2).sum();
            
            return sum1 > sum2 ? 1 : sum1 == sum2 ? 0 : -1;
        }
    }
}