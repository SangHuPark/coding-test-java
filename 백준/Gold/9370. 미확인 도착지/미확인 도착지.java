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

    static final int INF = 100_000_000;

    static int nodeCnt, edgeCnt, candidateCnt;
    static int start, doteStart, doteEnd;
    static boolean[] candidateList;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;
    static int[] dist;

    public static void dijkstra() {
        pq = new PriorityQueue<>();
        dist = new int[nodeCnt + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        pq.add(new Node(start, 0));

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
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            dijkstra();

            for (int idx = 1; idx <= nodeCnt; idx++) {
                if (!candidateList[idx])
                    continue;

                if (dist[idx] % 2 == 1)
                    sb.append(idx).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());
        candidateCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        doteStart = Integer.parseInt(st.nextToken());
        doteEnd = Integer.parseInt(st.nextToken());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if ((from == doteStart && to == doteEnd) || (from == doteEnd && to == doteStart))
                cost = (cost << 1) - 1;
            else
                cost = cost << 1;

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        candidateList = new boolean[nodeCnt + 1];
        for (int idx = 0; idx < candidateCnt; idx++) {
            candidateList[Integer.parseInt(br.readLine().trim())] = true;
        }
    }
}