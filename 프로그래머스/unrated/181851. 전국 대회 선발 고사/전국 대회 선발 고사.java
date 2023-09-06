import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] rankArr = new int[3];
        int idx = 0;
        
        for (int i = 1; i <= rank.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                if (attendance[j] && rank[j] == i)
                    list.add(j);
            }
            
            if (list.size() == 3)
                break;
        }
        
        int answer = 10000 * list.get(0) + 100 * list.get(1) 
            + list.get(2);
        
        return answer;
    }
}