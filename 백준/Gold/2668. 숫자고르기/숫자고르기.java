import java.io.*;
import java.util.*;

/**
 * DFS
 * 1. 1 ~ N 까지 각 출발점으로 DFS 진행
 * 2. 싸이클이 발생 시 종료
 * 3. 출발점으로 돌아왔다면 해당 경로의 숫자들까지 전역 배열에 체크
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, curStart, answerCnt;
    static int[] nums;
    static boolean[] cycleChecked;

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        nums = new int[N+1];
        for (int idx = 1; idx <= N; idx++) {
            nums[idx] = Integer.parseInt(br.readLine().trim());
        }

        cycleChecked = new boolean[N + 1];
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int start = 1; start <= N; start++) {
            if (cycleChecked[start])
                continue;

            curStart = start;
            DFS(start, new boolean[N+1]);
        }

        sb.append(answerCnt).append("\n");
        for (int idx = 1; idx <= N; idx++) {
            if (cycleChecked[idx])
                sb.append(idx).append("\n");
        }
        System.out.println(sb);
    }

    public static void DFS(int idx, boolean[] checked) {
        // 싸이클 발생 시 종료
        if (checked[idx]) {
            // 출발점으로 돌아왔다면 ok
            if (idx == curStart) {
                for (int checkIdx = 1; checkIdx <= N; checkIdx++) {
                    if (checked[checkIdx]) {
                        cycleChecked[checkIdx] = true;
                        answerCnt++;
                    }
                }
            }

            return;
        }

        checked[idx] = true;
        DFS(nums[idx], checked);
    }

}