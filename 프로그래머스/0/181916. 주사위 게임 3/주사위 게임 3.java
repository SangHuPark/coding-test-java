import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int[] arr = new int[7];
        arr[a]++;
        arr[b]++;
        arr[c]++;
        arr[d]++;
        
        int min = 10;
        List<Integer> sames = new ArrayList<>();
        
        for (int idx = 1; idx < 7; idx++) {
            if(arr[idx] == 0) continue;
            
            if(arr[idx] == 4) {
                sames.add(idx);
                break;
            }
            else if(arr[idx] == 3) {
                sames.add(idx);
            }
            else if(arr[idx] == 2) {
                sames.add(idx);
            }
            else if(arr[idx] == 1) {
                sames.add(idx);
            }
            
            min = Math.min(min, idx);
        }
        
        // 네 주사위가 모두 같다면
        if(sames.size() == 1) {
            answer = 1111 * a;
        } // 세 주사위가 같거나 두 주사위가 두 세트
        else if(sames.size() == 2) {
            int p = sames.get(0);
            int q = sames.get(1);
            
            // 세 주사위가 같음
            if (arr[p] == 3)
                answer = (10 * p + q) * (10 * p + q);
            else if(arr[q] == 3)
                answer = (10 * q + p) * (10 * q + p);
            // 두 주사위 두 세트
            else
                answer = (p + q) * Math.abs(p - q);
        } // 두 주사위가 같고 나머지는 서로 다름
        else if(sames.size() == 3) {
            int p = sames.get(0);
            int q = sames.get(1);
            int r = sames.get(2);
            
            if (arr[p] == 2)
                answer = q * r;
            else if(arr[q] == 2)
                answer = p * r;
            else if(arr[r] == 2)
                answer = p * q;
        } // 모두 다르면
        else
            answer = min;
        
        return answer;
    }
}