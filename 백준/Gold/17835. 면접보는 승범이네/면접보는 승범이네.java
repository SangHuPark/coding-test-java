import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o1) {
            return Long.compare(this.cost, o1.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCount, edgeCount, interviewNodeCount;
    static List<Node>[] graph;
    static int[] interviewCities;
    static long[] dist;
    static long maxDist, maxNode;

    static PriorityQueue<Node> pq;

    public static void dijkstra() {
        pq = new PriorityQueue<>();

        for (int idx = 0; idx < interviewNodeCount; idx++) {
            dist[interviewCities[idx]] = 0;
            pq.add(new Node(interviewCities[idx], 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to] < cur.cost)
                continue;

            for (Node next : graph[cur.to]) {
                long curDist = cur.cost + next.cost;

                if (dist[next.to] > curDist) {
                    dist[next.to] = curDist;
                    pq.add(new Node(next.to, curDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        maxDist = 0;
        maxNode = 0;

        Arrays.fill(dist, Long.MAX_VALUE);
        dijkstra();

        for (int idx = 1; idx <= nodeCount; idx++) {
            if (dist[idx] == 0)
                continue;

            if (dist[idx] > maxDist) {
                maxDist = dist[idx];
                maxNode = idx;
            }
        }

        System.out.println(maxNode);
        System.out.print(maxDist);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        interviewNodeCount = Integer.parseInt(st.nextToken());

        graph = new List[nodeCount + 1];
        for (int idx = 0; idx <= nodeCount; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 면접장에서 출발하기 위해 역방향으로 전환
            graph[to].add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine().trim());
        interviewCities = new int[interviewNodeCount];
        for (int idx = 0; idx < interviewNodeCount; idx++) {
            interviewCities[idx] = Integer.parseInt(st.nextToken());
        }

        dist = new long[nodeCount + 1];
    }

}