import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        ArrayList<String> list = new ArrayList<>();
        
        for (String str : quiz) {
            String[] strArr = str.split(" ");
            int num1 = Integer.parseInt(strArr[0]);
            int num2 = Integer.parseInt(strArr[2]);
            String oper = strArr[1];
            int result = Integer.parseInt(strArr[4]);
            String ans = new String();
            
            if (oper.equals("+")) {
                ans = num1 + num2 == result ? "O" : "X";
            } else {
                ans = num1 - num2 == result ? "O" : "X";
            }
            
            list.add(ans);
        }
        
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }
}