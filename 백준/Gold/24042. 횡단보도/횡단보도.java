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

    static class Node  {
        int idx;
        int cycle;
        Node next;

        Node(int idx, int cycle, Node next) {
            this.idx = idx;
            this.cycle = cycle;
            this.next = next;
        }

    }

    static class Dij implements Comparable<Dij> {
        int idx;
        long totalCost;

        Dij(int idx, long totalCost) {
            this.idx = idx;
            this.totalCost = totalCost;
        }

        public int compareTo(Dij o) {
            return Long.compare(this.totalCost, o.totalCost);
        }
    }

    static int N, M;
    static Node[] graph;
    static PriorityQueue<Dij> pq;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new Node[N + 1];
        int cycle = 0;
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, cycle, graph[from]);
            graph[to] = new Node(from, cycle, graph[to]);
            cycle++;
        }

        System.out.println(dijkstra());
    }

    public static long dijkstra() {
        pq = new PriorityQueue<>();
        dist = new long[N + 1];

        pq.add(new Dij(1, 0));
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Dij cur = pq.poll();

            if (cur.idx == N) return dist[cur.idx];

            if (dist[cur.idx] < cur.totalCost) continue;

            for (Node next = graph[cur.idx]; next != null; next = next.next) {
                long wait = 0;
                long mod = cur.totalCost % M;
                if (mod > next.cycle) {
                    wait = M - mod + next.cycle;
                } else {
                    wait = next.cycle - mod;
                }
                long arrive = cur.totalCost + wait + 1;

                if (dist[next.idx] > arrive) {
                    dist[next.idx] = arrive;
                    pq.add(new Dij(next.idx, arrive));
                }
            }
        }

        return -1;
    }
}