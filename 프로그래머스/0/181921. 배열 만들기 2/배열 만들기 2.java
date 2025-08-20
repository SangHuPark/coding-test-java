import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();
        
        for (int num = l; num <= r; num++) {
            String s = String.valueOf(num);
            boolean ok = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c != '5' && c != '0') { 
                    ok = false; break; 
                }
            }
            if (!ok) continue;
            
            list.add(num);
        }
        
        if(list.isEmpty())
            return new int[] { -1 };
        
        int[] answer = new int[list.size()];
        for(int idx = 0; idx < answer.length; idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
}