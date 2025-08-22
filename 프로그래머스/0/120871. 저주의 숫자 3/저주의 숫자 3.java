class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int num = 1; num <= n; num++) {
            answer++;
            // 3의 배수이거나 3을 포함하면 +1
            while(answer % 3 == 0 || String.valueOf(answer).contains("3"))
                answer++;
        }
        
        return answer;
    }
}