class Solution {
    public int solution(int hp) {
        int answer = 0;
        
        for (int num = 5; num > 0; num-=2) {
            answer += hp / num;
            hp = hp % num;
        }
        
        return answer;
    }
}