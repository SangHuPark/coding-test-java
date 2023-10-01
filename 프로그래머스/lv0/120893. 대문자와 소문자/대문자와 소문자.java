class Solution {
    public String solution(String my_string) {
        String answer = "";
        
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            if (ch >= 'a')
                ch = (char)(ch - 32);
            else 
                ch = (char)(ch + 32);
            
            answer += Character.toString(ch);
        }
        return answer;
    }
}