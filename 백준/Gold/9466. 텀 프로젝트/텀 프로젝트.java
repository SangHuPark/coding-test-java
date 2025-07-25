import java.io.*;
import java.util.*;

/**
 * BOJ_9466_텀프로젝트
 * @author tkdgn407
 *
 * 1. 각 학생이 선택한 팀원을 저장한다.
 * 2. 1번 학생부터 DFS를 통해 선택한 팀원을 따라 이동한다.
 *  2-1. 자기 자신이 나온다면 체크 후 종료
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, nonTeamCnt;
    static int[] students;
    static boolean[] checked, hasVisited;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            N = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim());
            students = new int[N+1];
            for (int idx = 1; idx <= N; idx++) {
                students[idx] = Integer.parseInt(st.nextToken());
            }

            checked = new boolean[N + 1];
            hasVisited = new boolean[N + 1];
            nonTeamCnt = N;
            for (int idx = 1; idx <= N; idx++) {
                DFS(idx);
            }

            sb.append(nonTeamCnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void DFS(int cur) {
        checked[cur] = true;

        int next = students[cur];

        if (!checked[next]) {
            DFS(next);
        } else if (!hasVisited[next]) {
            nonTeamCnt--;
            while (next != cur) {
                nonTeamCnt--;
                next = students[next];
            }
        }

        hasVisited[cur] = true;

    }
}