class Solution {
    public String solution(String my_string, int n) {
        String ans = new String();
        int leng = my_string.length();
        
        ans += my_string.substring(leng - n);
        
        return ans;
    }
}