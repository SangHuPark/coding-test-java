import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        myStr = myStr.replaceAll("a|b|c", " ");
        String[] arr = myStr.split(" ");
        ArrayList<String> list = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].isBlank())
                list.add(arr[i]);
        }
        
        String[] ans = new String[list.size()];
        if (list.size() == 0) {
            list.add("EMPTY");
        }
        
        list.toArray(ans);
        
        return ans.length == 0 ? new String[] { "EMPTY" } : ans;
    }
}