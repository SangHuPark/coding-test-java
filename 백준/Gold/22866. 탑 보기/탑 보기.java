import java.io.*;
import java.util.*;

/**
 * 1. buildings[] 에 건물 높이를 저장한다.
 * 2. 좌측부터 순차 탐색하며 좌측 스택에 index 저장:
 *     2-1. 현재 건물보다 작은 건물들은 pop 한다.
 *     2-2. 남아있는 top이 좌측에서 가장 가까운 높은 건물.
 *     2-3. 현재 건물을 push.
 * 3. 우측도 같은 방식으로 우측 스택에 작업
 * 4. 좌/우에서 찾은 건물의 개수와 가장 가까운 건물 번호 중 가장 작은 것을 출력한다.
 */
public class Main {
    static class Taller {
        int cnt;
        int idx;

        Taller(int cnt, int idx) {
            this.cnt = cnt;
            this.idx = idx;
        }

        public String toString() {
            return this.cnt + " " + this.idx;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int size;
    static int[] heights;
    static Stack<Integer> tallerLeft, tallerRight;
    static Taller[] tallers;

    public static void main(String[] args) throws IOException {
        size = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        heights = new int[size+1];
        for (int idx = 1; idx <= size; idx++) {
            heights[idx] = Integer.parseInt(st.nextToken());
        }
        tallers = new Taller[size+1];

        tallerLeft = new Stack<>();
        for (int idx = 1; idx <= size; idx++) {
            while (!tallerLeft.isEmpty() && heights[tallerLeft.peek()] <= heights[idx])
                tallerLeft.pop();

            // stack.peek() 가 idx번째 건물에서 볼 수 있는 "가장 가까운 좌측 건물"
            if (!tallerLeft.isEmpty()) {
                tallers[idx] = new Taller(tallerLeft.size(), tallerLeft.peek());
//                System.out.println(idx + " | " + tallers[idx]);
            }

            tallerLeft.push(idx);
        }
//        System.out.println("*********************************************");

        tallerRight = new Stack<>();
        for (int idx = size; idx >= 1; idx--) {
            while (!tallerRight.isEmpty() && heights[tallerRight.peek()] <= heights[idx])
                tallerRight.pop();

            // stack.peek() 가 idx번째 건물에서 볼 수 있는 "가장 가까운 우측 건물"
            if (!tallerRight.isEmpty()) {
                if (Objects.isNull(tallers[idx])) {
                    tallers[idx] = new Taller(tallerRight.size(), tallerRight.peek());
                } else {
                    int leftDistance = Math.abs(idx - tallers[idx].idx);
                    int rightDistance = Math.abs(idx - tallerRight.peek());

                    int closestIdx;
                    if (leftDistance < rightDistance)
                        closestIdx = tallers[idx].idx;
                    else if (leftDistance > rightDistance)
                        closestIdx = tallerRight.peek();
                    else
                        closestIdx = Math.min(tallers[idx].idx, tallerRight.peek());

                    tallers[idx] = new Taller(tallers[idx].cnt + tallerRight.size(), closestIdx);
                }
            }

            tallerRight.push(idx);
        }
//        System.out.println("*********************************************");

        for (int idx = 1; idx <= size; idx++) {
            if (Objects.isNull(tallers[idx]))
                sb.append(0).append("\n");
            else
               sb.append(tallers[idx].cnt).append(" ").append(tallers[idx].idx).append("\n");
        }
        System.out.println(sb);
    }

}