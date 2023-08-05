class Solution {
    public int solution(int n) {
        int sum = 0;
        
        /* for(int i = 0; i <= n; i++) {
            if(n % 2 == 1)
                sum = i % 2 == 1 ? (sum + i) : sum;
            else if(n % 2 == 0)
                sum = i % 2 == 0 ? (sum + i*i) : sum;
        } */
        
        if(n % 2 == 1) {
            for(int i = n; i > 0; i -= 2)
                sum += i;
        } else {
            for(int i = n; i > 0; i -= 2)
                sum += i*i;
        }
        
        return sum;
    }
}