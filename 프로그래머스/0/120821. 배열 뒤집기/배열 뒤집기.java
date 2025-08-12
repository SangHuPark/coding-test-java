class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = new int[num_list.length];
        
        int ansIdx = 0;
        for(int idx = num_list.length-1; idx >= 0; idx--) {
            answer[ansIdx++] = num_list[idx];
        }
        
        return answer;
    }
}