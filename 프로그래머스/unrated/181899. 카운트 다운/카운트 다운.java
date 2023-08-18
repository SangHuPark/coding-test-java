class Solution {
    public int[] solution(int start, int end) {
        int[] ans = new int[start - end + 1];
        int idx = 0;
        
        for (int i = start; i >= end; i--) {
            ans[idx] = i;
            idx++;
        }
        
        return ans;
    }
}