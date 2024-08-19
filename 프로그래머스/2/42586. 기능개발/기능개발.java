import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Deque<Integer> queue = new ArrayDeque<>();

        for (int idx = 0; idx < progresses.length; idx++) {
//            queue.push(progresses[idx]);
            if ((100 - progresses[idx]) % speeds[idx] == 0) {
                queue.add((100 - progresses[idx]) / speeds[idx]);
            } else {
                queue.add((100 - progresses[idx]) / speeds[idx] + 1);
            }
        }

        List<Integer> answerList = new ArrayList<>();

        int completeCnt = 1;
        int target = queue.poll();

        while (!queue.isEmpty()) {
            if (queue.peek() <= target) {
                completeCnt++;
                queue.poll();
            } else {
                answerList.add(completeCnt);
                completeCnt = 1;
                target = queue.poll();
            }
        }
        answerList.add(completeCnt);


        int[] answer = new int[answerList.size()];
        for (int idx = 0; idx < answer.length; idx++) {
            answer[idx] = answerList.get(idx);
        }

        return answer;
    }
}