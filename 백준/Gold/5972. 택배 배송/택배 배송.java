import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int START = 1;

    static int dest, edgeCnt;
    static List<Node>[] edgeList;

    static boolean[] visited;
    static int[] dist;

    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[1] = 0; //시작 지점은 1
        q.offer(new Node(1, 0));

        while(!q.isEmpty()) {
            Node current = q.poll();

            if(!visited[current.to]) visited[current.to] = true;
            else continue;

            for(int i = 0; i < edgeList[current.to].size(); i++) {
                Node next = edgeList[current.to].get(i);
                if(dist[next.to] > dist[current.to] + next.weight) {
                    dist[next.to] = dist[current.to] + next.weight;
                    q.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        dijkstra();

        System.out.println(dist[dest]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        dest = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        edgeList = new List[dest + 1];
        for (int idx = 0; idx < dest + 1; idx++) {
            edgeList[idx] = new ArrayList<>();
        }

        for (int edgeIdx = 0; edgeIdx < edgeCnt; edgeIdx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[from].add(new Node(to, weight));
            edgeList[to].add(new Node(from, weight));
        }

        visited = new boolean[dest + 1];
        dist = new int[dest + 1];
        for (int idx = 0; idx < dest + 1; idx++) {
            dist[idx] = Integer.MAX_VALUE;
        }

        dist[START] = 0;
    }
}