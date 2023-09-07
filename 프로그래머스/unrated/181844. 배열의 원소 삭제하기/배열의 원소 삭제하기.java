import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        ArrayList<Integer> list = new ArrayList<>();
//         boolean bool = false;
        
//         for (int i = 0; i < arr.length; i++) {
//             for (int j = 0; j < delete_list.length; j++) {
//                 if (arr[i] == delete_list[j])
//                     bool = true;
//             }
            
//             if (!bool)
//                 list.add(arr[i]);
            
//             bool = false;
//         }
        
        for(int n : arr) {
            list.add(n);
        }
        
        for(int n: delete_list) {
            list.remove((Integer)n);
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
}