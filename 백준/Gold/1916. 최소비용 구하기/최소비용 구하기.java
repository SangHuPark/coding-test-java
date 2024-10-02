import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static List<Node>[] graph;
    static int[] dist;
    static int start, destination;

    static PriorityQueue<Node> pq;

    public static void solution(Node start) {
        pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        pq.add(start);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.to == destination) {
                break;
            }

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;

            for (Node next : graph[cur.to]) {
                if (dist[cur.to] + next.cost < dist[next.to]) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        solution(new Node(start, 0));

//        for (int idx = 1; idx <= N; idx++) {
//            System.out.println(dist[idx]);
//        }

        System.out.println(dist[destination]);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
    }
}