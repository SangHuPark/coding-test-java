import java.io.*;
import java.util.*;

/**
 * 1. 가장자리와 닿아 있어 물이 들어갈 수 없는 최소 높이와 위치를 구한다.
 *     1-1. 해당 위치에서 BFS 를 진행해 물이 고일 수 없는 가장자리를 모두 체크한다.
 * 2. 남은 영역 중 최대 높이와 최소 높이, 최소 높이 시작 위치를 구한다.
 * 3. 구한 위치에서 해당 높이와 같은 영역을 BFS 로 체크한다.
 *  3-1. 해당 높이보다 큰 높이를 만나면 큰 높이들 중 최소값을 갱신한다.
 * 4. 체크한 부분의 높이를 큰 높이들 중 최솟값으로 바꾸고 카운팅
 * 5. 카운트 * (큰 높이들 중 최솟값 - 최소 높이) 를 누적한다.
 * 6. 2. 반복
 * 7. 더 이상 최소 높이가 없다면 종료한다.
 *  7-1. 초기의 최대 높이가 최소 높이와 같다면 종료한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Pos {
        int row;
        int col;

        Pos (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int minHeight = Integer.MAX_VALUE; int maxHeight = Integer.MIN_VALUE;
        for (int row = 0; row < N; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < M; col++) {
                map[row][col] = input.charAt(col) - '0';
                minHeight = Math.min(minHeight, map[row][col]);
                maxHeight = Math.max(maxHeight, map[row][col]);
            }
        }

        int answer = 0;
        for (int height = minHeight; height < maxHeight; height++) {
            for (int row = 1; row < N - 1; row++) {
                for (int col = 1; col < M - 1; col++) {
                    if (map[row][col] == height) {
                        answer += BFS(new Pos(row, col), height);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static int BFS(Pos start, int height) {
        Deque<Pos> q = new ArrayDeque<>();
        int cnt = 1;
        boolean isPossible = false;

        q.add(start);
        map[start.row][start.col] = height + 1;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = cur.row + deltaRow[idx];
                int nextCol = cur.col + deltaCol[idx];

                // 물이 밖으로 빠져 나가는 위치면 패스
                if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] < height) {
                    isPossible = true;
                    continue;
                }

                // 같은 높이만 갱신
                if (map[nextRow][nextCol] > height) continue;

                map[nextRow][nextCol] = height + 1;
                cnt++;
                q.add(new Pos(nextRow, nextCol));
            }
        }
        if (isPossible)
            return 0;

        return cnt;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }


}