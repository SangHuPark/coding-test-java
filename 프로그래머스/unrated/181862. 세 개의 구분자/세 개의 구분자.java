import java.util.*;

class Solution {
    public ArrayList<String> solution(String myStr) {
        myStr = myStr.replaceAll("b", "a").replaceAll("c", "a");
        String[] arr = myStr.split("a");
        ArrayList<String> list = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].isBlank())
                list.add(arr[i]);
        }
        
        // String[] ans = new String[list.size()];
        if (list.size() == 0) {
            list.add("EMPTY");
        }
        
//         list.toArray(ans);
        
//         System.out.println(list + ", " + ans);
        
        return list;
    }
}