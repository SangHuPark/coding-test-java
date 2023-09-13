import java.util.stream.*;

class Solution {
    public int[] solution(int n) {
//         ArrayList<Integer> list = new ArrayList<>();
        
//         for (int i = 1; i <= n; i++) {
//             if (i % 2 == 1)
//                 list.add(i);
//         }
        
//         int[] answer = list.stream().mapToInt(i->i).toArray();
//         return answer;
        return IntStream.rangeClosed(0, n)
            .filter(value -> value % 2 == 1).toArray();
    }
}