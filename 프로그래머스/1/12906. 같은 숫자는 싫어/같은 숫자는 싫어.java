import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        
        stack.push(arr[0]);
        for(int idx = 1; idx < arr.length; idx++) {
            if (stack.peek() == arr[idx] && stack.size() > 0) {
                stack.pop();
                stack.push(arr[idx]);
            } else {
                stack.push(arr[idx]);
            }
        }
        
        int size = stack.size();
        
        int[] answer = new int[size];
        
        for(int idx = size-1; idx >= 0; idx--) {
            answer[idx] = stack.pop();
        }

        return answer;
    }
}