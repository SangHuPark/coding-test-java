import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int GROUP_A = 1, EMPTY = 0;

    static int V, E;
    static List<Integer>[] graph;
    static int[] checked;
    static boolean flag;

    public static void dfs(int node) {
        for (int next : graph[node]) {
            if (checked[node] == checked[next]) {
                flag = true;
                return;
            }

            // 아직 집합에 속하지 않은 정점이라면
            if (checked[next] == EMPTY) {
                // 인접한 현재 기준 노드와 반대 집합 그룹으로 분류
                checked[next] = checked[node] * (-1);
                dfs(next);
//                if (flag)
//                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            flag = false;
            // 모든 정점에서 한 번씩 탐색 진행
            for (int node = 1; node <= V; node++) {
                // 아직 집합에 속하지 않은 정점이라면
                if (checked[node] == EMPTY) {
                    checked[node] = GROUP_A;
                    dfs(node);
                    if (flag) {
                        sb.append("NO\n");
                        break;
                    }
                }
            }

            if (flag)
                continue;

            sb.append("YES\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int idx = 0; idx <= V; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < E; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        checked = new int[V + 1];
    }

}