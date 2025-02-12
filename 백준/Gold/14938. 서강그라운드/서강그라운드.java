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

        public int compareTo(Node o1) {
            return Integer.compare(this.cost, o1.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int nodeCnt, limitRange, edgeCnt;
    static int[] items;
    static List<Node>[] edgeList;
    static int maxItemCnt;

    static int[] dist;
    static PriorityQueue<Node> pq;

    public static void dijkstra(Node start) {
        pq = new PriorityQueue<>();

        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.to] < curNode.cost)
                continue;

            for (Node next : edgeList[curNode.to]) {
                int newCost = curNode.cost + next.cost;

                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        maxItemCnt = Integer.MIN_VALUE;
        for (int start = 0; start < nodeCnt; start++) {
            Arrays.fill(dist, INF);
            dist[start] = 0;
            dijkstra(new Node(start, 0));

            int curItemCnt = 0;
            for (int idx = 0; idx < nodeCnt; idx++) {
                if (dist[idx] <= limitRange)
                    curItemCnt += items[idx];
            }

            maxItemCnt = Math.max(maxItemCnt, curItemCnt);
        }

        System.out.println(maxItemCnt);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        limitRange = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        items = new int[nodeCnt];
        for (int idx = 0; idx < nodeCnt; idx++) {
            items[idx] = Integer.parseInt(st.nextToken());
        }

        edgeList = new List[nodeCnt];
        for (int idx = 0; idx < nodeCnt; idx++) {
            edgeList[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edgeList[from].add(new Node(to, cost));
            edgeList[to].add(new Node(from, cost));
        }

        dist = new int[nodeCnt];
    }
}