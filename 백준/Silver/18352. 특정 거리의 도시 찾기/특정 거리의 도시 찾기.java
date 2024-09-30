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
            // cost 기준 오름차순 정렬
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K, X;
    static List<Node>[] graph;
    static int[] dist;

    static PriorityQueue<Node> pq;

    static int result;

    public static void solution(Node start) {
        pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        pq.add(start);
        visited[start.to] = true;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

//            System.out.println(curNode.to);
            for (Node next : graph[curNode.to]) {
//                System.out.println(next.to + " " + next.cost);
                if (visited[next.to]) {
                    continue;
                }

//                System.out.println(dist[curNode.to] + " " + next.to + " " + dist[next.to]);
                if (dist[curNode.to] + next.cost < dist[next.to]) {
                    dist[next.to] = dist[curNode.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                    visited[next.to] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        solution(new Node(X, 0));

        result = Integer.MAX_VALUE;
//        System.out.println();
        for (int idx = 1; idx <= N; idx++) {
//            System.out.println(dist[idx]);
            if (dist[idx] == K) sb.append(idx).append("\n");
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, 1));
        }

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;
    }
}