import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] str_list) {
        ArrayList<String> list = new ArrayList<>();
        boolean bool = false;
        int idx = 0;
        
        for (idx = 0; idx < str_list.length; idx++) {
            if (str_list[idx].equals("l")) {
                bool = true;
                break;
            } else if (str_list[idx].equals("r"))
                break;
        }
        
        if (bool) {
            for (int i = 0; i < idx; i++)
                list.add(str_list[i]);
        } else {
            for (int i = idx + 1; i < str_list.length; i++)
                list.add(str_list[i]);
        }
        
        return list;
    }
}