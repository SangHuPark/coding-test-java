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

    static final int START = 1;
    static final int MAX = 200000000;

    static int N, E;
    static List<Node>[] graph;
    static int[] dist;
    static int n1, n2;

    static PriorityQueue<Node> pq;
    static int minDist;

    public static int solution(Node start, int dest) {
        pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        Arrays.fill(dist, MAX);
        dist[start.to] = 0;

        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (visited[curNode.to]) {
                continue;
            }

            visited[curNode.to] = true;

            for (Node next : graph[curNode.to]) {
                if (!visited[next.to] && dist[curNode.to] + next.cost < dist[next.to]) {
                    dist[next.to] = dist[curNode.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist[dest];
    }

    public static void main(String[] args) throws IOException {

        init();

        int result1 = 0;
        result1 += solution(new Node(START, 0), n1);
        result1 += solution(new Node(n1, 0), n2);
        result1 += solution(new Node(n2, 0), N);

        int result2 = 0;
        result2 += solution(new Node(START, 0), n2);
        result2 += solution(new Node(n2, 0), n1);
        result2 += solution(new Node(n1, 0), N);

        if (result1 >= MAX && result2 >= MAX) {
            System.out.println(-1);
        } else {
            int result = Math.min(result1, result2);
            System.out.println(result);
        }

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < E; idx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];


    }
}