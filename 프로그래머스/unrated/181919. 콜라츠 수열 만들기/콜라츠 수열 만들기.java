import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; ; i++) {
            list.add(n);
            if (n == 1)
                break;
            n = n % 2 == 0 ? n / 2 : 3 * n + 1;
        }
        
        int[] result = list.stream().mapToInt(i->i).toArray();
        return result;
    }
}