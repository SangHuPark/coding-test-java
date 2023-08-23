class Solution {
    public int[] solution(int[] arr) {
        int temp = 50;
        int[] ans = new int[arr.length];
        int idx = 0;
        
        for (int num : arr) {
            if (num % 2 == 0 && num >= temp)
                ans[idx] = num / 2;
            else if (num % 2 == 1 && num < temp)
                ans[idx] = num * 2;
            else
                ans[idx] = num;
            
            temp = 50;
            idx++;
        }
        
        
        return ans;
    }
}