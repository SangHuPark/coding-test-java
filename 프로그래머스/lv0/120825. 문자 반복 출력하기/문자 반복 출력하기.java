class Solution {
    public String solution(String my_string, int n) {
        String answer = "";
        
        for (char ch : my_string.toCharArray()) {
            answer += Character.toString(ch).repeat(n);
        }
        
        return answer;
    }
}