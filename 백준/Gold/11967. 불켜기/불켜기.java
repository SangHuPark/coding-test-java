import java.io.*;
import java.util.*;

/**
 * 1. 시작 지점 (1,1)에서 출발 → 불 켬 → BFS 시작.
 * 2. BFS 진행 → 불 켜진 방만 이동 가능 → 각 방에 도착하면 스위치 작동 → 다른 방의 불을 켠다.
 * 3. 새로운 방의 불이 켜졌으면 → **BFS 다시 이어서 진행 (그 방들도 이제 탐색 가능)**.
 * 4. **새로 켜지는 불이 없으면 탐색 종료**
 * 5. **onChecked == true인 방 개수 출력**
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node {
        int row;
        int col;

        Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    static int N, M, size;
    static boolean[][] onChecked;

    static List<Node>[] graph;

    static Deque<Node> q;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        size = N * N;

        graph = new List[size];
        for (int idx = 0; idx < size; idx++) {
            graph[idx] = new LinkedList<>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row1 = Integer.parseInt(st.nextToken()) - 1;
            int col1 = Integer.parseInt(st.nextToken()) - 1;
            int row2 = Integer.parseInt(st.nextToken()) - 1;
            int col2 = Integer.parseInt(st.nextToken()) - 1;

            int graphIdx = row1 * N + col1;
            Node node2 = new Node(row2, col2);

            graph[graphIdx].add(node2);
        }

        onChecked = new boolean[N][N];
        onChecked[0][0] = true;
        BFS();

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (onChecked[row][col]) answer++;
            }
        }
        System.out.println(answer);
    }

    public static void BFS() {
        boolean newSwitchFlag = true;
        // 새로 켜진 불이 없을 때 까지 반복
        while (newSwitchFlag) {
            newSwitchFlag = false; // 새로 켜진 불이 있다면 다시 체크
            q = new ArrayDeque<>();
            visited = new boolean[N][N];

            // 처음 시작이라면
            if (onChecked[0][0] && !visited[0][0]) {
                q.add(new Node(0, 0));
                visited[0][0] = true;
            }

            while (!q.isEmpty()) {
                Node cur = q.poll();
                int idx = cur.row * N + cur.col;

                // 연결된 스위치 켜기
                for (Node next : graph[idx]) {
                    // 이미 켠 스위치라면 패스
                    if (onChecked[next.row][next.col]) continue;

                    // 불켜기
                    onChecked[next.row][next.col] = true;
                    newSwitchFlag = true; // 새로 켜진 불이 있음을 표시
                }

                for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                    int nextRow = cur.row + deltaRow[deltaIdx];
                    int nextCol = cur.col + deltaCol[deltaIdx];

                    // 맵을 벗어나거나, 이미 방문했거나, 스위치가 꺼져있으면 패스
                    if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol] || !onChecked[nextRow][nextCol]) continue;

                    visited[nextRow][nextCol] = true;
                    q.add(new Node(nextRow, nextCol));
                }
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}