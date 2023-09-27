import java.util.*;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int prime = 2;
        
        while (n != 1) {
            if (n % prime == 0) {
                n = n / prime;
                list.add(prime);
            } else {
                prime++;
            }
        }
        
        int[] answer = list.stream().mapToInt(i->i).distinct().toArray();
        
        return answer;
    }
}