import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] query) {
//         ArrayList<Integer> list = new ArrayList<>();
//         int idx = 0;
        
//         for (int i = 0; i < arr.length; i++)
//             list.add(arr[i]);
        
//         for (int q : query) {
//             if (idx % 2 == 0 && q != list.size() - 1) {
//                 for (int i = q + 1; i < list.size(); i++)
//                     list.remove(i);
//             }
//             else if (idx % 2 != 0 && q != 0) {
//                 for (int i = q - 1; i >= 0; i--)
//                     list.remove(i);
//             }
//             idx++;
//         }
        
//         return list;
            for(int i =0; i<query.length; i++) {
                if (i % 2 == 0) {
                    arr = Arrays.copyOfRange(arr, 0, query[i]+1);
                } else {
                    arr = Arrays.copyOfRange(arr, query[i], arr.length);
                }
            }
            return arr;
    }
}