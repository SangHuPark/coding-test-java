class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] checked = new int[201];
        
        for (int idx = 0; idx < 3; idx++) {
            lines[idx][0] += 100;
            lines[idx][1] += 100;
        }
        
        // 끝점 처리
        for (int curr = 0; curr < 3; curr++) {
            int currStart = lines[curr][0];
            int cuurEnd = lines[curr][1];
            for (int next = curr+1; next < 3; next++) {
                int nextStart = lines[next][0];
                int nextEnd = lines[next][1];
                
                
            }
        }
        
        for (int idx = 0; idx < 3; idx++) {
            int start = lines[idx][0];
            int end = lines[idx][1];
            for (int pos = start; pos < end; pos++) {
                checked[pos]++;
            }
        }
    
        for (int idx = 0; idx <= 200; idx++) {
            if (checked[idx] > 1)
                answer++;
        }
        
        return answer;
    }
}