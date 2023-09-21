import java.util.*;

class Solution {
    public int[][] solution(int[] num_list, int n) {
        int[][] answer = new int[num_list.length / n][n];
        int idx = 0;
        
        // for (int i = 0; i < answer.length; i++) {
        //     for (int j = 0; j < n; j++) {
        //         answer[i][j] = num_list[idx++];
        //     }
        // }
        
        for(int i = 0; i < num_list.length; i++){
            answer[i / n][i % n] = num_list[i];
        }
        
        return answer;
    }
}