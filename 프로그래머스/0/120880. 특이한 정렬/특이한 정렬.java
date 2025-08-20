class Solution {
    public int[] solution(int[] numlist, int n) {
        int[] answer = new int[numlist.length];
        boolean[] checked = new boolean[numlist.length];
        
        int idx = 0;
        for (int row = 0; row < numlist.length; row++) {
            int minDis = 10001, minNum = 0, minIdx = 0;
            for (int col = 0; col < numlist.length; col++) {
                if(checked[col]) continue;
                
                int curDis = Math.abs(numlist[col] - n);
                if (curDis < minDis) {
                    minDis = curDis;
                    minNum = numlist[col];
                    minIdx = col;
                } else if (curDis == minDis && numlist[col] > minNum) {
                    minNum = numlist[col];
                    minIdx = col;
                }
            }
            answer[idx++] = minNum;
            checked[minIdx] = true;
            
        }
        
        return answer;
    }
}