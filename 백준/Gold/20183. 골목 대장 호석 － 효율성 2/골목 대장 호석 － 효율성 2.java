import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 *
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, M, start, end;
    static long C;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        long max = -1;
        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
            max = Math.max(max, cost);
        }

        binarySearch(max);
    }

    public static void binarySearch(long right) {
        long left = 1;
        long answer = -1;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);

            if (dijkstra(mid)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean dijkstra(long mid) {
        pq = new PriorityQueue<>();
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.idx] < cur.cost) continue;

            for (Node next : graph[cur.idx]) {
                if (next.cost > mid) continue;

                long newCost = cur.cost + next.cost;
                if (dist[next.idx] > newCost) {
                    dist[next.idx] = newCost;
                    pq.add(new Node(next.idx, newCost));
                }
            }
        }

        if (dist[end] == Long.MAX_VALUE || dist[end] > C)
            return false;

        return true;
    }
}