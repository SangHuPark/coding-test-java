import java.util.*;

class Solution {
    public String solution(String my_string) {
        char[] chArr = my_string.toLowerCase().toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }
}