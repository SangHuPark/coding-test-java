import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ init ]
 * 1. 지름길의 개수, 고속도로의 길이 입력
 * 2. 지름길의 정보 입력
 *
 * [ dijkstra(start) ]
 * 3. 출발 위치(0)부터 다익스트라
 *  3-1. 길이를 기준으로 정렬하는 pq
 *  3-2.
 */
public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX_LENG = 10001;

    static int shortCutCount, totalRoute;
    static List<Node>[] graph;
    static int[] dist;

    public static void dijkstra() {
        for (int idx = 0; idx < MAX_LENG; idx++) {
            if (idx != 0)
                dist[idx] = Math.min(dist[idx-1] + 1, dist[idx]);

            if (!graph[idx].isEmpty()) {
                for (Node node : graph[idx]) {
                    if (dist[idx] + node.cost < dist[node.to]) {
                        dist[node.to] = dist[idx] + node.cost;
                    }
                }
            }
        }

        System.out.println(dist[totalRoute]);
    }

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        shortCutCount = Integer.parseInt(st.nextToken());
        totalRoute = Integer.parseInt(st.nextToken());

        graph = new ArrayList[MAX_LENG];
        for (int idx = 0; idx < MAX_LENG; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < shortCutCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, cost));
        }

        dist = new int[MAX_LENG];
        for (int idx = 0; idx < MAX_LENG; idx++) {
            dist[idx] = idx;
        }
    }
}