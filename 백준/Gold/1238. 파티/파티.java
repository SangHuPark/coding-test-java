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
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int nodeCnt, edgeCnt, target;
    static List<Node>[] graph;

    static int[] dist;
    static PriorityQueue<Node> pq;

    static int maxCost;

    public static int dijkstra(Node start, int curTarget) {
        Arrays.fill(dist, INF);
        dist[start.to] = 0;

        pq = new PriorityQueue<>();

        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.to] < curNode.cost)
                continue;

            for (Node next : graph[curNode.to]) {
                int newCost = curNode.cost + next.cost;
                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }

        return dist[curTarget];
    }

    public static void main(String[] args) throws IOException {
        init();

        maxCost = -1;
        for (int start = 1; start <= nodeCnt; start++) {
            int targetCost = dijkstra(new Node(start, 0), target) + dijkstra(new Node(target, 0), start);
            maxCost = Math.max(maxCost, targetCost);
        }

        System.out.println(maxCost);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        dist = new int[nodeCnt + 1];
    }
}