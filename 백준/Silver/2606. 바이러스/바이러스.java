import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCnt, edgeCnt;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int answer;

    public static void dfs(int node) {
        for (int next : graph[node]) {
            if (visited[next])
                continue;

            visited[next] = true;
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        visited[1] = true;
        dfs(1);
        visited[1] = false;

        for (int idx = 1; idx <= nodeCnt; idx++) {
            if (visited[idx])
                answer++;
        }

        System.out.println(answer);
    }

    public static void init() throws IOException {
        nodeCnt = Integer.parseInt(br.readLine().trim());
        edgeCnt = Integer.parseInt(br.readLine().trim());

        graph = new List[nodeCnt+1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        visited = new boolean[nodeCnt+1];

        answer = 0;
    }

}