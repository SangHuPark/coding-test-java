import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] numbers, String direction) {
//         int[] answer = new int[numbers.length];
//         Boolean bool = direction.equals("right") ? true : false;
//         int length = numbers.length - 1;
        
//         if (bool) {
//             answer[0] = numbers[length];
//             for (int i = 0; i < length; i++) {
//                 answer[i+1] = numbers[i];
//             }
//         } else {
//             answer[length] = numbers[0];
//             for (int i = 0; i < length; i++) {
//                 answer[i] = numbers[i+1];
//             }
//         }
        
//         return answer;
        List<Integer> list = Arrays.stream(numbers)
            .boxed().collect(Collectors.toList());

        if (direction.equals("right")) {
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
        } else {
            list.add(list.size(), list.get(0));
            list.remove(0);
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        return answer;
    }
}