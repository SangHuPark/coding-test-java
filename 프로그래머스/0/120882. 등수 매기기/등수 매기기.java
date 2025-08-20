import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        double[] means = new double[score.length];
        for(int stu = 0; stu < score.length; stu++) {
            means[stu] = (score[stu][0] + score[stu][1]) / 2.0;
        }
        double[] origin = Arrays.copyOf(means, score.length);
        Arrays.sort(means);
        
        for(int idx = 0; idx < score.length; idx++) {
            System.out.print("origin: " + origin[idx]);
            System.out.println(" sorted: " + means[idx]);
        }
        
        int[] answer = new int[score.length];
        for(int curr = 0; curr < score.length; curr++) {
            for(int next = score.length-1; next >= 0; next--) {
                if(origin[curr] == means[next]) {
                    answer[curr] = score.length - next;
                    break;
                }
            }
        }
        
        return answer;
    }
}