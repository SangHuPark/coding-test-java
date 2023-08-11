import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        /* ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        
        while(i < arr.length) {
            if(list.isEmpty()){
                list.add(arr[i]);
                i++;
            } else {
                int last = list.get(list.size()-1);
                if(!list.isEmpty() && last < arr[i]){
                    list.add(arr[i]);
                    i++;
                }
                else if(!list.isEmpty() && last >= arr[i])
                    list.remove(list.size()-1);
            }
        }
            
        int[] stk = list.stream().mapToInt(j->j).toArray();
        return stk; */
        
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < arr.length) {
            if (stack.empty() || stack.peek() < arr[i]) {
                stack.push(arr[i]);
                i++;
            } else if (stack.peek() >= arr[i]) {
                stack.pop();
            }
        }
        
        int[] stk = stack.stream().mapToInt(j->j).toArray();
        return stk;
    }
}