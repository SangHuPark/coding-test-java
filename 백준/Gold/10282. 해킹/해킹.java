import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o1) {
            return Integer.compare(this.cost, o1.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int nodeCnt, edgeCnt, start;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;
    static int[] dist;

    public static void dijkstra(Node start) {
        Arrays.fill(dist, INF);
        dist[start.to] = 0;

        pq = new PriorityQueue<>();

        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.to] < curNode.cost)
                continue;

            for (Node nextNode : graph[curNode.to]) {
                int newCost = curNode.cost + nextNode.cost;
                if (dist[nextNode.to] > newCost) {
                    dist[nextNode.to] = newCost;
                    pq.add(new Node(nextNode.to, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            dijkstra(new Node(start, 0));

            int zombieCnt = 0;
            int maxWeight = Integer.MIN_VALUE;
            for (int idx = 1; idx <= nodeCnt; idx++) {
                if (dist[idx] != INF) {
                    zombieCnt++;
                    maxWeight = Math.max(maxWeight, dist[idx]);
                }
            }

            sb.append(zombieCnt).append(" ").append(maxWeight).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        dist = new int[nodeCnt + 1];
    }
}