import java.io.*;
import java.util.*;

/**
 * BOJ_24042_횡단보도
 * @author tkdgn407
 *
 * 1. PQ에는 (현재 위치한 노드, 해당 노드에 도달한 시간)을 저장
 * 2. dist는 각 노드에 최소 도착 시간을 저장
 * 3. 현재 위치에서 연결된 다음 노드를 확인할 때
 *  3-1. 현재 노드까지의 총 시간 % M과 다음 노드까지의 주기를 비교
 *  3-2. mod > next.cycle라면 다음 횡단보도가 열릴 때까지 기다려야 하므로
 *      3-2-1. 도착 시간 = 현재 시간 + (K - (현재 시간 % K)) + 1
 *  3-3. 아니라면 바로 통과 가능하므로
 *      3-3-1. 도착 시간 = 현재 시간 + 1
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
                long curCycle = cur.totalCost % M; // 현재 노드까지의 도착 시간에 어떤 횡단보도가 켜지는지
                // 이미 다음 노드의 횡단보도가 지나갔다면
                if (curCycle > next.cycle) {
                    // 현재 횡단보도 이후에 다음 횡단보도가 켜지기 까지 기다려야 하는 시간
                    wait = M - curCycle + next.cycle;
                } // 지나치지 않았다면 남은 시간만큼만 대기
                else {
                    wait = next.cycle - curCycle;
                }
                long newTotalCost = cur.totalCost + wait + 1; // 현재까지 전체 시간 + 기다리는 시간 + 도착 시간

                if (dist[next.idx] > newTotalCost) {
                    dist[next.idx] = newTotalCost;
                    pq.add(new Dij(next.idx, newTotalCost));
                }
            }
        }

        return -1;
    }
}