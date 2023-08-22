class Solution {
    public int solution(int[] numbers, int n) {
        int temp = 0;
        int idx = 0;
        
        while(temp <= n) {
            temp += numbers[idx];
            idx++;
        }
        
        return temp;
    }
}