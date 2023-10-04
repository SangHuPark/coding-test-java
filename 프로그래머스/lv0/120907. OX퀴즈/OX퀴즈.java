import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] quiz) {
        ArrayList<String> list = new ArrayList<>();
        
        for (String str : quiz) {
            String[] strArr = str.split(" ");
            int num1 = Integer.parseInt(strArr[0]);
            int num2 = Integer.parseInt(strArr[2]);
            String oper = strArr[1];
            int result = Integer.parseInt(strArr[4]);
            
            if (oper.equals("+")) {
                if (num1 + num2 == result) {
                    list.add("O");
                } else {
                    list.add("X");
                }
            } else {
                if (num1 - num2 == result) {
                    list.add("O");
                } else {
                    list.add("X");
                }
            }
        }
        
        return list;
    }
}