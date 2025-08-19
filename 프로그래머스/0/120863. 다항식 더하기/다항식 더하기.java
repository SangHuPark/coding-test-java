import java.util.*;

class Solution {
    public String solution(String polynomial) {
        String[] polys = polynomial.replace("+ ", "").split(" ");
        
        int xCnt = 0, numCnt = 0;
        for(String poly : polys) {
            if(poly.contains("x")) {
                String num = poly.replace("x", "");
                xCnt += "".equals(num) ? 1 : Integer.parseInt(num);
            }
            else
                numCnt += Integer.parseInt(poly);
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (xCnt != 0) {
            if (xCnt == 1)
                sb.append("x");
            else if (xCnt > 0)
                sb.append(String.format("%dx", xCnt));  
            
            if (numCnt != 0)
                sb.append(" + ");
        }
        
        if (numCnt > 0)
            sb.append(numCnt);
        
        return sb.toString();
    }
}