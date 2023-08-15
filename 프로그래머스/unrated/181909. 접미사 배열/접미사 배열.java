import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        ArrayList<String> list = new ArrayList<>();
        String str = new String();
        
        for (int i = my_string.length() - 1; i >= 0; i--) {
            str = my_string.substring(i);
            list.add(str);
        }

        Collections.sort(list);
        
        String[] strArr = new String[list.size()];
        strArr = list.toArray(strArr);
        
        return strArr;
    }
}