import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        for (int idx = 0; idx < phone_book.length-1; idx++) {
            if (phone_book[idx+1].startsWith(phone_book[idx])) {
                return false;
            }
        }
        
        return true;
    }
}