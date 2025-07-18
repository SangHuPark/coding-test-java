import java.io.*;
import java.util.*;

/**
 * BOJ_11779_최소비용구하기2
 * @author tkdgn407
 *
 * 1. 출발 도시부터 다익스트라 알고리즘을 수행하여, 각 도시까지의 최소 비용을 구한다.
 * 2. 비용 갱신 시, 해당 도시에 도달하기 직전의 도시를 별도 배열(prev[])에 저장한다.
 * 3. 도착 도시에서 출발 도시까지 prev[]를 따라가며 역순으로 경로를 추적한다.
 * 4. 추적한 경로를 뒤집어 실제 이동 경로로 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int MAX = 200_000_000;

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M, start, end;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;
    static int[] dist, prev;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[end]);
        List<Integer> list = new LinkedList<>();
        list.add(end);
        int cnt = 1;
        while (true) {
            if (prev[end] == 0) break;
            list.add(prev[end]);
            end = prev[end];
            cnt++;
        }
        Collections.reverse(list);
        // 이러면 십의 자리 수 "10" 이 들어갔을 때 "01" 로 출력됨
//        System.out.println(sb.reverse());

        for (int vertex : list) {
            sb.append(vertex).append(" ");
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    public static void dijkstra() {
        pq = new PriorityQueue<>();
        dist = new int[N + 1];
        prev = new int[N + 1];

        Arrays.fill(dist, MAX);
        dist[start] = 0;

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (dist[node.to] < node.cost) continue;

            for (Node next : graph[node.to]) {
                int newCost = node.cost + next.cost;
                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    prev[next.to] = node.to;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }

    }
}