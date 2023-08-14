class Solution {
    public String solution(String my_string, int n) {
        String ans = new String();
        
        for (int i = my_string.length() - n; i < my_string.length(); i++)
            ans += my_string.charAt(i);
        
        return ans;
    }
}