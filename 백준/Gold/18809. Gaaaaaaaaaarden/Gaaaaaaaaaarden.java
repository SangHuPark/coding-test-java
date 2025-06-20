import java.io.*;
import java.util.*;

/**
 * 1. 2(배양액 가능)인 땅의 개수 중 G개의 중복 없는 조합을 생성한다.
 * 2. G개를 제외한 위치 중 R개의 중복 없는 조합을 생성한다.
 * 3. G, R의 위치를 큐에 담아 BFS를 수행한다.
 * 4. G = 1, R = 2, 꽃 = 3 을 저장한다.
 * 5. 0(호수)이거나 방문했거나 같은 배양액이 이미 퍼진 곳이라면 패스한다.
 * 6. 그렇지 않다면 R or G 인지 검사한다.
 *  6-1. 현재와 반대되는 배양액 종류라면 별도의 boolean 배열에 체크한다.
 * 7. BFS가 끝나면 boolean 배열의 개수를 계산해 최대값을 갱신한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Pos {
        int row;
        int col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Medicine {
        int type;
        int time;

        Medicine(int type, int time) {
            this.type = type;
            this.time = time;
        }
    }

    static final int GREEN = 1, RED = 2, FLOWER = 3;
    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    static int N, M, greenCnt, redCnt;
    static int[][] map;

    static boolean[] selectedGreen, selectedRed;
    static int elementCnt;
    static List<Pos> elements;
    static Medicine[][] medicineMap;

    static Deque<Pos> q;

    static int maxFlowerCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        greenCnt = Integer.parseInt(st.nextToken());
        redCnt = Integer.parseInt(st.nextToken());

        elements = new ArrayList<>();

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 2)
                    elements.add(new Pos(row, col));
            }
        }
        elementCnt = elements.size();

        maxFlowerCnt = 0;
        selectedGreen = new boolean[elementCnt];
        combGreen(0, 0);

        System.out.println(maxFlowerCnt);
    }

    public static void combGreen(int depth, int elementIdx) {
        if (depth == greenCnt) {
            selectedRed = new boolean[elementCnt];
            combRed(greenCnt, 0);

            return;
        }

        for (int idx = elementIdx; idx < elementCnt; idx++) {
            if (selectedGreen[idx]) continue;

            selectedGreen[idx] = true;
            combGreen(depth + 1, idx + 1);
            selectedGreen[idx] = false;
        }
    }

    public static void combRed(int depth, int elementIdx) {
        if (depth == greenCnt + redCnt) {
            medicineMap = new Medicine[N][M];
            q = new ArrayDeque<>();
            for (int idx = 0; idx < elementCnt; idx++) {
                Pos pos = elements.get(idx);
                if (selectedGreen[idx]) {
                    medicineMap[pos.row][pos.col] = new Medicine(GREEN, 0);
                    q.add(new Pos(pos.row, pos.col));
                } else if (selectedRed[idx]) {
                    medicineMap[pos.row][pos.col] = new Medicine(RED, 0);
                    q.add(new Pos(pos.row, pos.col));
                }
            }

            BFS();

            return;
        }

        for (int idx = elementIdx; idx < elementCnt; idx++) {
            if (selectedGreen[idx] || selectedRed[idx]) continue;

            selectedRed[idx] = true;
            combRed(depth + 1, idx + 1);
            selectedRed[idx] = false;
        }
    }

    public static void BFS() {
        int flowerCnt = 0;

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            Medicine medicine = medicineMap[pos.row][pos.col];

            // Green 을 뿌리고 큐에 담았는데 다음 큐에서 나온 Red 가 같은 시간에 만나 꽃이 될 경우, 아직 큐에 Green 이 남아있으므로 패스해줘야 한다.
            if (!Objects.isNull(medicine) && medicine.type == FLOWER) continue;

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = pos.row + deltaRow[delta];
                int nextCol = pos.col + deltaCol[delta];

                if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == 0)
                    continue;

                // 배양액을 처음 전파하는 곳
                if (Objects.isNull(medicineMap[nextRow][nextCol])) {
                    medicineMap[nextRow][nextCol] = new Medicine(medicine.type, medicine.time + 1);
                    q.add(new Pos(nextRow, nextCol));
                } // 다른 배양액이 있다면
                else {
                    Medicine other = medicineMap[nextRow][nextCol];

                    // 꽃이면 패스
                    if (other.type == FLOWER) continue;

                    // 다른 종류일 때
                    if (medicine.type != other.type) {
                        // 같은 시간일 때만 꽃
                        if (medicine.time + 1 == other.time) {
                            medicineMap[nextRow][nextCol] = new Medicine(FLOWER, -1);
                            flowerCnt++;
                        }
                    }
                }
            }
        }

        maxFlowerCnt = Math.max(maxFlowerCnt, flowerCnt);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

}