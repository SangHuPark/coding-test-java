import java.io.*;
import java.util.*;

/**
 * BOJ_2098_외판원순회
 * @author tkdgn407
 *
 * 1. dist[i][j] 배열에 i번 도시에서 j번 도시로 가는 비용을 저장한다.
 * 2. tsp(cur, visited)를 통해 현재 도시 cur에서, 방문한 도시 집합 visited 상태로 최소 비용을 구한다.
 * 3. 모든 도시를 방문한 경우, 시작 도시(0번)으로 돌아갈 수 있다면 그 비용을 반환한다.
 * 4. 그렇지 않다면, 현재 도시 cur에서 방문하지 않은 next 도시들 중
 *  4-1. 경로가 존재하고 아직 방문하지 않은 도시라면
 *  4-2. dp[cur][visited] = min(...) 형식으로 최소 비용을 갱신한다.
 * 5. 시작점 tsp(0, 1 << 0) 호출을 통해 전체 최단 순회 경로 비용을 구한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static final int MAX_VALUE = 16_000_001;

    static int N, ALL_VISITED;
    static int[][] dist, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        dist = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                dist[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];
        for (int idx = 0; idx < N; idx++) {
            Arrays.fill(dp[idx], -1);
        }

        ALL_VISITED = (1 << N) - 1;
        System.out.println(tsp(0, 1));
    }

    public static int tsp(int cur, int visited) {
        if (visited == ALL_VISITED) {
            return (dist[cur][0] == 0) ? MAX_VALUE : dist[cur][0];
        }

        if (dp[cur][visited] != -1) return dp[cur][visited];

        dp[cur][visited] = MAX_VALUE;

        for (int next = 0; next < N; next++) {
            // 방문한 도시거나 경로가 없는 도시면 패스
            if ((visited & (1 << next)) != 0 || dist[cur][next] == 0) continue;

            // 미방문 도시이면서 경로가 있다면
            int nextVisited = visited | (1 << next);
            // 현재 도시에서 next 도시까지의 경로 합 + tsp() 호출로 next 도시에서 가는 새로운 경로 탐색 시작
            dp[cur][visited] = Math.min(dp[cur][visited], tsp(next, nextVisited) + dist[cur][next]);
        }

        return dp[cur][visited];
    }
}