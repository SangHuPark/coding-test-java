class Solution {
    public int solution(int n) {
        int answer = 0;
        String strN = String.valueOf(n);
        
        for (char ch : strN.toCharArray()) {
            answer += ch - '0';
        }
        
        return answer;
    }
}