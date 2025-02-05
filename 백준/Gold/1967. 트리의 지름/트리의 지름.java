import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 루트에서 가장 먼 노드 찾기
 * 2. 해당 노드 부터 가장 먼 노드 찾아 출력
 */
public class Main {
    static class Node {
        int to;
        int cost;

        Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCnt;
    static List<Node>[] graph;
    static boolean[] visited;
    static int maxNode;
    static int maxPrefixSum;

    public static void dfs(int node, int prefixCostSum) {

        for (Node next : graph[node]) {
            if (visited[next.to])
                continue;

            int curPrefixSum = prefixCostSum + next.cost;
            visited[next.to] = true;
            dfs(next.to, curPrefixSum);
            if (maxPrefixSum < curPrefixSum) {
                maxPrefixSum = curPrefixSum;
                maxNode = next.to;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        // 1. 루트에서 가장 먼 노드 찾기
        maxPrefixSum = Integer.MIN_VALUE;
        visited[1] = true;
        dfs(1, 0);

//        System.out.println(maxNode);

        // 2. 해당 노드 부터 가장 먼 노드 찾아 출력
        // 방문 배열 초기화
        visited = new boolean[nodeCnt + 1];
        visited[maxNode] = true;
        maxPrefixSum = Integer.MIN_VALUE;
        dfs(maxNode, 0);

//        System.out.println(maxNode);
        if (maxPrefixSum == Integer.MIN_VALUE)
            maxPrefixSum = 0;
        
        System.out.println(maxPrefixSum);
    }

    public static void init() throws IOException {
        nodeCnt = Integer.parseInt(br.readLine().trim());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 1; idx < nodeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        visited = new boolean[nodeCnt + 1];
    }

}