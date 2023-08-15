import java.util.*;

class Solution {
    public int solution(String my_string, String is_suffix) {
        ArrayList<String> list = new ArrayList<>();
        int result = 0;
        
        for (int i = my_string.length() - 1; i >= 0; i--) {
            String str = my_string.substring(i);
            list.add(str);
        }
        
        String[] strArr = new String[list.size()];
        strArr = list.toArray(strArr);
        
        for (int j = 0; j < strArr.length; j++) {
            if (strArr[j].equals(is_suffix)) {
                result = 1;
                break;
            }
        }
        
        return result;
    }
}