class Solution {
    public int solution(int n) {
        int answer = 0;
        int num = 1;
        int max = 0;
        
        for (int i = 1; i <= 10; i++) {
            num *= i;
            if (num <= n) {                
                max = i;
            }
        }
        
        answer = max;
        
        return answer;
    }
}