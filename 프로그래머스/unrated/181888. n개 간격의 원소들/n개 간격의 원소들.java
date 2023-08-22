import java.util.*;

class Solution {
    public int[] solution(int[] num_list, int n) {
//         ArrayList<Integer> list = new ArrayList<>();
        
//         for(int i = 0; i < num_list.length; i+=n) {
//             list.add(num_list[i]);
//         }
        
//         int[] answer = list.stream().mapToInt(i->i).toArray();
//         return answer;
        
        Double length = Math.ceil(num_list.length/(n*1.0));
        int[] answer = new int[length.intValue()];

        for(int idx=0; idx<length; idx++) {
            answer[idx] = num_list[idx*n];
        }

        return answer;
    }
}