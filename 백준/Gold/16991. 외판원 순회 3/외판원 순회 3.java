import java.io.*;
import java.util.*;

/**
 * BOJ_16991_외판원순회3
 * @author tkdgn407
 *
 * 1. 모든 도시 간 거리 계산
 *  1-1. 각 도시에는 (x, y) 좌표가 주어지므로, 두 도시 사이의 거리를 계산
 *  1-2. dist[i][j] 배열에 i번 도시에서 j번 도시로 가는 거리 저장
 *  1-3. 양방향이므로 dist[i][j] = dist[j][i]
 * 2. 현재 위치와 방문한 도시 상태를 기준으로 최소 거리를 저장하는 dp[cur][visited]
 *  2-1. 도시를 방문한 상태를 비트마스크로 관리
 *  2-2. dp[cur][visited]은 cur 도시에서 시작해서 남은 도시를 모두 방문하고 시작점으로 돌아갈 때까지의 최소 거리
 * 3. 현재 도시에서 아직 방문하지 않은 다른 도시들로 이동
 *  3-1. dp[next][visited | (1 << next)] + dist[cur][next]로 재귀 호출
 *  3-2. 모든 도시를 방문한 경우에는 시작점으로 돌아가는 거리를 포함
 * 4. 최소 거리 출력
 *  4-1. 초기에는 도시 0에서 출발한다고 가정하고, dfs(0, 1 << 0) 결과를 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDist(Pos o) {
            int dx = o.x - this.x;
            int dy = o.y - this.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    static int N, ALL_VISITED;
    static Pos[] nodes;
    static double[][] dist, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        nodes = new Pos[N];
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes[idx] = new Pos(x, y);
        }

        dist = new double[N][N];
        for (int curIdx = 0; curIdx < N; curIdx++) {
            Pos cur = nodes[curIdx];
            for (int otherIdx = curIdx + 1; otherIdx < N; otherIdx++) {
                Pos other = nodes[otherIdx];

                double distance = cur.getDist(other);
                dist[curIdx][otherIdx] = distance;
                dist[otherIdx][curIdx] = distance;
            }
        }

        // dp 배열 초기화
        dp = new double[N][1 << N];
        for (int idx = 0; idx < N; idx++) {
            // -1.0은 아직 초기화되지 않음을 의미
            Arrays.fill(dp[idx], -1.0);
        }

        ALL_VISITED = (1 << N) - 1;
//        for (int idx = 0; idx < N; idx++) {
//            // 출발 도시만 방문 체크 후 dfs
//            tsp(idx, 1 << idx);
//        }
        System.out.println(tsp(0, 1));
    }

    public static double tsp(int cur, int visited) {
        // 모두 방문했다면 출발 도시로 돌아오는 거리를 반환
        if (visited == ALL_VISITED) {
            return dist[cur][0];
        }

        // 이미 방문하여 최소 거리가 저장되어 있다면 백트래킹
        if (dp[cur][visited] != -1.0) return dp[cur][visited];

        // 초기 방문 표시
        dp[cur][visited] = Double.MAX_VALUE;

        // 미방문 도시 탐색
        for (int next = 0; next < N; next++) {
            // 방문한 도시면 패스
            if ((visited & (1 << next)) != 0) continue;

            // 미방문 도시인 경우
            int nextVisited = visited | (1 << next); // 다음 도시 방문 체크
            // tsp(next, newVisited)로 새로운 경로 탐색
            dp[cur][visited] = Math.min(dp[cur][visited], tsp(next, nextVisited) + dist[cur][next]);
        }

        return dp[cur][visited];
    }
}