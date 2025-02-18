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
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int nodeCnt, edgeCnt;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;
    static int[] dist;
    static int[][] answer;

    public static void dijkstra(Node start) {
        Arrays.fill(dist, INF);
        dist[start.to] = 0;

        pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.to] < curNode.cost)
                continue;

            for (Node next : graph[curNode.to]) {
                int newCost = curNode.cost + next.cost;
                if (dist[next.to] > newCost) {
                    if (start.to == curNode.to)
                        answer[start.to][next.to] = next.to;
                    else
                        answer[start.to][next.to] = answer[start.to][curNode.to];
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int start = 1; start <= nodeCnt; start++) {
            dijkstra(new Node(start, 0));
        }

        for (int row = 1; row <= nodeCnt; row++) {
            for (int col = 1; col <= nodeCnt; col++) {
                if (row == col)
                    System.out.print("- ");
                else
                    System.out.print(answer[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        dist = new int[nodeCnt + 1];
        answer = new int[nodeCnt + 1][nodeCnt + 1];
    }
}