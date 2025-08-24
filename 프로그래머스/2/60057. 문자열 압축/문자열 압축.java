import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        // aabbaccc
        int standLen = s.length();
        for(int cut = 1; cut <= (standLen >> 1); cut++) {
            StringBuilder compSb = new StringBuilder();
            
            // a
            String prevStr = s.substring(0, cut);
            // System.out.println("prevStr: " + prevStr);
            int matchCnt = 1;
            
            for(int comp = cut; comp < standLen; comp+=cut) {
                String currStr;
                if(cut + comp >= standLen)
                    currStr = s.substring(comp);
                else
                    currStr = s.substring(comp, cut + comp);
                
                if(prevStr.equals(currStr))
                    matchCnt++;
                else {
                    if(matchCnt > 1)
                        compSb.append(matchCnt);
                    compSb.append(prevStr);
                    // System.out.println("matchCnt: " + matchCnt + " currStr: " + currStr);
                    matchCnt = 1;
                    prevStr = new String(currStr);
                }
            }
            if(matchCnt > 1)
                compSb.append(matchCnt);
            compSb.append(prevStr);
            
            answer = Math.min(answer, compSb.length());
            // System.out.println("compressed: " + compSb.toString());
        }
        
        return answer;
    }
}