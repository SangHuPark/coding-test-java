import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        /* ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < intStrs.length; i++) {
            char[] ch = intStrs[i].toCharArray();
            String str = new String();
            for (int j = s; j < l; j++)
                str += ch[j] + "";
            int res = Integer.parseInt(str);
            if (res > k)
                list.add(res);
        }
        
        int[] ans = list.stream().mapToInt(j->j).toArray();
        return ans; */
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (String str : intStrs) {
            int i = Integer.parseInt(str.substring(s, s + l));
            if (i > k) {
                list.add(i);
            }
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }
}