import java.io.*;
import java.util.*;

/**
 * 1. left = 0, right = 최대 간선 비용
 * 2. mid = (left + right) / 2
 * 3. mid를 비용 상한으로 잡고,
 *     3-1. 다익스트라로 1→N 갈 수 있는지 확인
 *     3-2. 비용 초과 간선 수 ≤ K면 가능
 * 4. 가능하면 더 낮은 상한을 찾기 위해 right = mid - 1
 * 5. 불가능하면 left = mid + 1
 * 6. 최종적으로 최소 상한값 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int idx;
        int cost;
        int pass;

        Node(int idx, int cost, int pass) {
            this.idx = idx;
            this.cost = cost;
            this.pass = pass;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.pass, o.pass);
        }
    }

    static int N, P, K;
    static List<Node>[] graph;

    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        // 1. left = 0, right = 최대 간선 비용
        int right = -1;
        for (int idx = 0; idx < P; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost, K));
            graph[to].add(new Node(from, cost, K));
            right = Math.max(right, cost);
        }

        int left = 0;
        int answer = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (isReach(mid)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean isReach(int mid) {
        pq = new PriorityQueue<>();
        int[] usedPass = new int[N + 1];

        pq.add(new Node(1, 0, 0));
        Arrays.fill(usedPass, Integer.MAX_VALUE);
        usedPass[1] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.idx == N) return node.pass <= K;

            if (node.pass > usedPass[node.idx]) continue;

            for (Node next : graph[node.idx]) {
                int nextPass = node.pass + (next.cost > mid ? 1 : 0);
                if (usedPass[next.idx] > nextPass) {
                    usedPass[next.idx] = nextPass;
                    pq.add(new Node(next.idx, 0, nextPass));
                }
            }

        }

        return false;
    }

}