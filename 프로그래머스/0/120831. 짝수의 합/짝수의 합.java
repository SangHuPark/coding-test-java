class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int num = 2; num <= n; num+=2) {
            answer += num;
        }
        
        return answer;
    }
}