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
                if (hasVisited[idx]) continue;
                DFS(idx);
            }

            sb.append(nonTeamCnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void DFS(int cur) {
        checked[cur] = true; // 방문 체크

        int next = students[cur];

        // 방문하지 않은 노드라면 이동
        if (!checked[next]) {
            DFS(next);
        } // 방문한 노드인데, 탐색이 끝나지 않았다면 싸이클 발생
        else if (!hasVisited[next]) {
            nonTeamCnt--;
            while (next != cur) {
                nonTeamCnt--;
                next = students[next];
            }
        }

        // 현재 노드와 연결된 경로의 탐색이 끝났음을 체크
        hasVisited[cur] = true;
    }
}