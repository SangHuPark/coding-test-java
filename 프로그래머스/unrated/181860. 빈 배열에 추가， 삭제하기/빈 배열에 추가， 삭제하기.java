import java.util.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        ArrayList<Integer> list = new ArrayList<>();
        int idx = 0;
        
        for (boolean tmp : flag) {
            int num = arr[idx++];
            if (tmp)
                for (int i = 0; i < num*2; i++)
                    list.add(num);
            else if (!tmp)
                for (int i = 0; i < num; i++)
                    list.remove(list.size() - 1);
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
}