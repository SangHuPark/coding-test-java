import java.io.*;
import java.util.*;

/**
 * 1. BFS로 각 섬 넘버링.
 * 2. 각 섬에서 4방향으로 탐색하여 유효한 다리(길이 ≥ 2)를 간선으로 저장.
 * 3. 간선들을 가중치 오름차순 정렬 후 유니온 파인드로 MST 구성.
 * 4. 선택된 간선의 가중치 누적 후 출력, 모든 섬이 연결되지 않으면 -1 출력.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int cost;

        Node (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    static int N, M;
    static int[][] map;
    static List<Node> graph;

    static Deque<int[]> q;
    static boolean[][] visited;

    static int[] parents;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken()) == 1 ? -1 : 0;
            }
        }

        // 섬 체크
        int vertex = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                // 아직 확인 안 한 섬이면 BFS 진행
                if (map[row][col] == -1)
                    BFS(new int[] {row, col}, vertex++);
            }
        }

        // 다리 체크
        graph = new LinkedList<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                // 섬이면 다리 놓기
                if (map[row][col] > 0) {
                    int curVertex = map[row][col];
                    for (int idx = 0; idx < 4; idx++) {
                        int cost = 0;
                        int nextRow = row + deltaRow[idx];
                        int nextCol = col + deltaCol[idx];
                        while (!isArrange(nextRow, nextCol) && map[nextRow][nextCol] != curVertex) {
                            if (map[nextRow][nextCol] > 0 && map[nextRow][nextCol] != curVertex) {
                                if (cost <= 1)
                                    break;

                                // 오름차순의 정점 연결을 위한 조건문
                                if (curVertex < map[nextRow][nextCol]) {
                                    graph.add(new Node(curVertex, map[nextRow][nextCol], cost));
                                }

                                break;
                            }

                            cost++;
                            nextRow += deltaRow[idx];
                            nextCol += deltaCol[idx];
                        }
                    }
                }
            }
        }

        Collections.sort(graph);
        set(vertex);

        int answer = 0;
        for (Node node : graph) {
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);
                answer += node.cost;
            }
        }
        
        // 모든 섬이 연결 됐는지 확인
        int root = find(1);
        for (int idx = 2; idx < vertex; idx++) {
            if (find(idx) != root) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);

    }

    public static void BFS(int[] start, int vertex) {
        q = new ArrayDeque<>();
        visited = new boolean[N][M];

        q.add(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            map[row][col] = vertex;

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = row + deltaRow[idx];
                int nextCol = col + deltaCol[idx];

                if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol] || map[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                q.add(new int[] {nextRow, nextCol});
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    public static void set(int nodeCnt) {
        parents = new int[nodeCnt];
        for (int idx = 0; idx < nodeCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static int find(int x) {
        if (parents[x] == x)
            return x;

        return parents[x] = find(parents[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b)
            parents[b] = a;
        else
            parents[a] = b;
    }

}