import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        ArrayList<String> list = new ArrayList<>();
        String[] strArr = my_string.split(" ");
        
        for (int i = 0; i < strArr.length; i++) {
            if (!strArr[i].equals("")) {
                list.add(strArr[i]);
            }
        }
        
        String[] ans = new String[list.size()];
        list.toArray(ans);
        
        return ans;
    }
}