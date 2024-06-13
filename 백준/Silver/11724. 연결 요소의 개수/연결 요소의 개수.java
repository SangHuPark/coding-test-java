import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 정점의 개수, 간선의 개수 입력
 * 2. 2차원 인접행렬 graph 초기화
 * 3. 간선 정보 입력
 * 4. BFS 시 방문한 모든 정점을 저장하는 checked 초기화
 *
 * [ solution ]
 * 5. 1 번 정점부터 BFS
 *  5-1. 방문한 적 있는 정점이면 패스
 *  5-2. 방문한 적 없는 정점이라면 다시 BFS
 *  5-3. 횟수 카운팅
 *
 * [ connectBFS(int start) ]
 * 5. 매개변수로 넘어온 정점부터 간선 정보를 따라 BFS
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCount, edgeCount;
    static boolean[][] graph;

    static boolean[] checked;
    static int connectCount;

    static Deque<Integer> queue;

    public static void connectBFS(int start) {
        queue = new ArrayDeque<>();

        queue.add(start);
        checked[start] = true;

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int nextNode = 1; nextNode <= nodeCount; nextNode++) {
                if (checked[nextNode] || !graph[curNode][nextNode])
                    continue;

                queue.add(nextNode);
                checked[nextNode] = true;
            }
        }
    }

    public static void solution() {
        connectCount = 0;

        for (int node = 1; node <= nodeCount; node++) {
            if (checked[node])
                continue;

            connectBFS(node);
            connectCount++;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        solution();

        System.out.println(connectCount);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        graph = new boolean[nodeCount+1][nodeCount+1];
        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = true;
            graph[to][from] = true;
        }

        checked = new boolean[nodeCount + 1];

//        for (int row = 1; row <= nodeCount; row++) {
//            for (int col = 1; col <= nodeCount; col++) {
//                System.out.print(graph[row][col] ? "T " : "F ");
//            }
//            System.out.println();
//        }
    }
}