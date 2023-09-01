import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String myString) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] strArr = myString.split("x");
        int num = 0;
        
        for (int i = 0; i < strArr.length; i++) {
            list.add(strArr[i].length());
            num++;
        }
        
        if (myString.substring(myString.length() - 1).equals("x")) {
            list.add(0);
        }
        
        return list;
    }
}