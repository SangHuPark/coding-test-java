import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int[] asc = Arrays.stream(emergency).sorted().toArray();
        int[] answer = new int[emergency.length];
        
        for(int i=0; i<=emergency.length-1; i++) {
            for(int j=0; j<=emergency.length-1; j++) {
                if(asc[i]==emergency[j]) {
                    answer[j]=emergency.length-i;
                }
            }
        }
        return answer;
    }
}