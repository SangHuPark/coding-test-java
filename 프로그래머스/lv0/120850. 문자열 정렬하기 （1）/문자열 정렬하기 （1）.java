import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        my_string = my_string.replaceAll("[^0-9]", "");
        char[] arr = my_string.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        
        for (char ch : arr) {
            list.add((int)(ch - '0'));
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        
        Arrays.sort(answer);
        
        return answer;
    }
}