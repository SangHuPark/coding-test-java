class Solution {
    public int[] solution(String my_string) {
        int[] ans = new int[52];
        
        for (int i = 0; i < my_string.length(); i++) {
            int num = my_string.charAt(i) - '0';
            if (num <= 42)
                num = num - 17;
            else
                num = num - 23;
            
            ans[num]++;
        }
        
        return ans;
    }
}