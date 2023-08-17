class Solution {
    public int[] solution(String my_string) {
        int[] ans = new int[52];
        
        // for (int i = 0; i < my_string.length(); i++) {
        //     int num = my_string.charAt(i) - '0';
        //     num = num <= 42 ? num - 17 : num - 23;
        //     ans[num]++;
        // }
        
        for(char ch: my_string.toCharArray()) {
            ans[ch - 'A' - (Character.isLowerCase(ch) ? 6 : 0)]++;
        }
        
        return ans;
    }
}