import java.util.*;

class Solution {
    public int[] solution(String myString) {
//         ArrayList<Integer> list = new ArrayList<>();
//         String[] strArr = myString.split("x");
        
//         for (int i = 0; i < strArr.length; i++) {
//             list.add(strArr[i].length());
//         }
        
//         if (myString.substring(myString.length() - 1).equals("x")) {
//             list.add(0);
//         }
        
//         return list;
        
        return Arrays.stream(myString.split("x", myString.length()))
            .mapToInt(x -> x.length())
            .toArray();
    }
}