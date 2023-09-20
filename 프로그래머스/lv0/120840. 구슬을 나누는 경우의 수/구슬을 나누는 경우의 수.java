import java.math.*;

class Solution {
    
    public long solution(int balls, int share) {
        long answer = 1;
        share = balls - share > share ? balls - share : share;
        
        for (int i = balls; i > share; i--) {
            answer *= i;
            answer /= balls-i+1;
            // System.out.println(i);
        }
//         long n = Factorial(share, balls);
//         long m = Factorial(0, balls - share);
        
//         answer = n / m;
        
        return answer;
    }
}