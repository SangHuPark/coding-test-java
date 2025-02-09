import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCnt;
    static List<Node>[] graph;
    static boolean[] visited;
    static int maxNode, maxPrefixSum;

    public static void dfs(int node, int prefixSum) {
        if (maxPrefixSum < prefixSum) {
            maxNode = node;
            maxPrefixSum = prefixSum;
        }

        for (Node next : graph[node]) {
            if (visited[next.to])
                continue;

            visited[next.to] = true;
            dfs(next.to, prefixSum + next.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        maxNode = 1;
        maxPrefixSum = 0;

        visited[1] = true;
        dfs(1, 0);

        Arrays.fill(visited, false);
        visited[maxNode] = true;
        maxPrefixSum = 0;
        dfs(maxNode, 0);

        System.out.println(maxPrefixSum);
    }

    public static void init() throws IOException {
        nodeCnt = Integer.parseInt(br.readLine().trim());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < nodeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1)
                    break;
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost));
            }
        }

        visited = new boolean[nodeCnt + 1];
    }

}