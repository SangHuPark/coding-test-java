import java.util.*;

class Solution {
    public int[] solution(int[] num_list, int n) {
        // int[] ans = new int[num_list.length - n + 1];
        num_list = Arrays.copyOfRange(num_list, n - 1, num_list.length);
        return num_list;
    }
}