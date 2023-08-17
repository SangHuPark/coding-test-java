import java.util.*;

class Solution {
    public int[] solution(int n, int k) {
//         ArrayList<Integer> list = new ArrayList<>();
        
//         for (int i = 1; i <= n; i++) {
//             if (i % k == 0)
//                 list.add(i);
//         }
        
//         int[] ans = list.stream().mapToInt(j->j).toArray();
//         return ans;
        
        int count = n / k;
        int[] answer = new int[count];

        for (int i = 1; i <= count; i++) {
            answer[i - 1] = k * i;
        }

        return answer;
    }
}