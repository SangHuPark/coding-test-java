import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        int idx = 0;
        for(String q : quiz) {
            String[] expr = q.split(" |= ");
            
            int x = Integer.parseInt(expr[0]);
            int y = Integer.parseInt(expr[2]);
            int z = Integer.parseInt(expr[expr.length-1]);
            
            if("+".equals(expr[1]))
                answer[idx++] = x + y == z ? "O" : "X";
            else
                answer[idx++] = x - y == z ? "O" : "X";
        }
        
        return answer;
    }
}