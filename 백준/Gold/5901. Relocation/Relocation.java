import java.io.*;
import java.util.*;

/**
 * BOJ_5901_Relocation
 * @author tkdgn407
 *
 * K 개의 시장을 제외한 나머지 마을에서 시장까지의 거리 * 2 의 값 중 최소값 구하기
 * 1. elements 배열에 시장이 있는 마을 번호 저장
 * 2. 모든 마을에서 다익스트라 수행
 *  2-1. dist[][]에 각 마을까지의 최단 경로 저장
 * 3. 시장 방문 순열 생성
 *  3-1. 각 비시장 마을에서 왕복 거리 계산
 *  3-2. 최소 왕복 거리 갱신
 * 4. 최소 왕복 거리 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M, K;
    static int[] elements, selected;
    static boolean[] isMarket, used;
    static List<Edge>[] graph;

    static PriorityQueue<Edge> pq;
    static int[][] dist;

    static int minDistance;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1. elements 배열에 시장이 있는 마을 번호 저장
        elements = new int[K];
        isMarket = new boolean[N];
        for (int idx = 0; idx < K; idx++) {
            int tmp = Integer.parseInt(br.readLine().trim()) - 1;
            elements[idx] = tmp;
            isMarket[tmp] = true;
        }

        graph = new List[N];
        for (int idx = 0; idx < N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        // 2. 모든 마을에서 다익스트라 수행
        // 2. 시장 K개만 다익스트라 수행
        dist = new int[K][N];
        for (int idx = 0; idx < K; idx++) {
            Arrays.fill(dist[idx], Integer.MAX_VALUE);
            dijkstra(idx);
        }

        // 3. 시장 방문 순열 생성
        minDistance = Integer.MAX_VALUE;
        selected = new int[K];
        used = new boolean[K];
        Permu(0);
        System.out.println(minDistance);
    }

    public static void dijkstra(int idx) {
        pq = new PriorityQueue<>();

        pq.add(new Edge(elements[idx], 0));
        dist[idx][elements[idx]] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (edge.cost > dist[idx][edge.idx]) continue;

            for (Edge next : graph[edge.idx]) {
                int newCost = edge.cost + next.cost;
                if (dist[idx][next.idx] > newCost) {
                    dist[idx][next.idx] = newCost;
                    pq.add(new Edge(next.idx, newCost));
                }
            }
        }
    }

    public static void Permu(int depth) {
        // 순열을 만들면
        if (depth == K) {
            int curDistance = Integer.MAX_VALUE;
            // 3-1. 각 비시장 마을에서 왕복 거리 계산
            for (int idx = 0; idx < N; idx++) {
                if (isMarket[idx]) continue;

                curDistance = dist[selected[0]][idx];
                for (int selectIdx = 0; selectIdx < K - 1; selectIdx++) {
                    curDistance += dist[selected[selectIdx]][elements[selected[selectIdx + 1]]];
                }
                curDistance += dist[selected[K - 1]][idx];

                // 3-2. 최소 왕복 거리 갱신
                minDistance = Math.min(minDistance, curDistance);
            }

            return;
        }

        for (int idx = 0; idx < K; idx++) {
            if (used[idx]) continue;

            selected[depth] = idx;
            used[idx] = true;
            Permu(depth + 1);
            used[idx] = false;
        }
    }
}