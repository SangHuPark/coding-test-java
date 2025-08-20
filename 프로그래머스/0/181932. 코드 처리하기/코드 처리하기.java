import java.util.*;

class Solution {
    public String solution(String code) {
        StringBuilder sb = new StringBuilder();
        
        int mode = 0;
        for(int idx = 0; idx < code.length(); idx++) {
            char cd = code.charAt(idx);
            
            if (mode == 0) {
                if(cd == '1')
                    mode = 1;
                else if(idx % 2 == 0)
                    sb.append(cd);
            }
            else if (mode == 1) {
                if(cd == '1')
                    mode = 0;
                else if(idx % 2 == 1)
                    sb.append(cd);
            }
        }
        
        if(sb.length() == 0)
            return "EMPTY";
        
        return sb.toString();
    }
}