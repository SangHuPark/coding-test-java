class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        int[] counts = new int[1000];
        for(int num : array) {
            counts[num]++;
        }
        
        int max = -1;
        int maxNum = 0;
        for(int idx = 0; idx < 1000; idx++) {
            if(max < counts[idx]) {
                max = counts[idx];
                maxNum = idx;
            }
        }
        
        int sameCnt = 0;
        for (int idx = 0; idx < 1000; idx++) {
            if(max == counts[idx]) {
                sameCnt++;
            }
        }
        
        if(sameCnt > 1)
            return -1;
        
        return maxNum;
    }
}