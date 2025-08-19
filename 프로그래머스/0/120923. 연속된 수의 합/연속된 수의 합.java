class Solution {
    public int[] solution(int num, int total) {        
        int[] answer = new int[num];
        
        int start = -100;
        int end = -101 + num;
        for(int n = 0; n <= 200; n++) {
            int nextStart = start + n;
            int nextEnd = end + n;
            int currSum = 0;
            for(int x = nextStart; x <= nextEnd; x++) {
                currSum += x;
            }
            if(currSum == total) {
                int idx = 0;
                for(int x = nextStart; x <= nextEnd; x++) {
                    answer[idx++] = x;
                }
                break;
            }
        }
        
        return answer;
    }
}