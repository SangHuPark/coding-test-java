import java.util.*;

class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        int xLimit = board[0] / 2;
        int yLimit = board[1] / 2;
        
        for (String move : keyinput) {
            if (move.equals("left")) 
                answer[0] -= answer[0] > -xLimit ? 1 : 0;
            else if (move.equals("right")) 
                answer[0] += answer[0] < xLimit ? 1 : 0;
            else if (move.equals("down"))
                answer[1] -= answer[1] > -yLimit ? 1 : 0;
            else if (move.equals("up"))
                answer[1] += answer[1] < yLimit ? 1 : 0;
        }
        
        return answer;
    }
}