class Solution {
    public String solution(int q, int r, String code) {
        String ans = new String();
        
        for (int i = 0; i < code.length(); i++) {
            if (i % q == r)
                ans += code.charAt(i);
        }
        
        return ans;
    }
}