import java.util.*;

class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 0;
        
        boolean[] checked = new boolean[27];
        for(String alpha : spell) {
            int idx = alpha.charAt(0) - 'a';
            checked[idx] = true;
        }
        
        for(String word : dic) {
            boolean flag = true;
            Set<Integer> set = new HashSet<>();
            for(int ch = 0; ch < word.length(); ch++) {
                int idx = word.charAt(ch) - 'a';
                if(!checked[idx]) {
                    flag = false;
                    break;
                } else {
                    set.add(idx);
                }
            }
            if(flag && set.size() == spell.length)
                return 1;
        }
        
        return 2;
    }
}