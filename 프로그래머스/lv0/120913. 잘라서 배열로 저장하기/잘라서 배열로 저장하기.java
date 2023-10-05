import java.util.*;

class Solution {
    public String[] solution(String my_str, int n) {
        ArrayList<String> list = new ArrayList<>();
        int leng = my_str.length();
        
        for (int i = 0; i < leng / n; i++) {
            System.out.println(i);
            list.add(my_str.substring(i*n, (i*n)+n));
        }
        
        if (leng % n != 0) {
            list.add(my_str.substring(leng-(leng % n)));
        }
        
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }
}