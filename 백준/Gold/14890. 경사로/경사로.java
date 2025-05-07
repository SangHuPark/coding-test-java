import java.io.*;
import java.util.*;

/**
 * 1. 입력된 지도를 roads[][]에 저장하고, 가로/세로 탐색을 나눈다.
 * 2. 각 길에서 높이 차가 `1`을 넘으면 경사로를 놓을 수 없으므로 탐색 중단한다.
 * 3. 높이 차가 `1`일 경우, 경사로를 설치할 공간(L개, 오르막/내리막 구분)가 확보되었는지 체크한다.
 * 4. 중복 설치를 막기 위해 boolean[]를 사용하여 경사로 위치를 관리한다.
 * 5. 조건을 만족하는 길의 개수를 최종적으로 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, L;
    static int[][] roads;

    public static int set() {
        int cnt = 0;

        // 가로
        for (int row = 0; row < N; row++) {
            boolean flag = true;
            boolean[] used = new boolean[N];
            int before = roads[row][0];
            for (int col = 1; col < N; col++) {
                int cur = roads[row][col];

                int height = cur - before;
                // 높이 차가 없으면 패스
                if (height == 0)
                    continue;

                // 높이 차가 2 이상이면 경사로 설치 불가
                if (height > 1 || height < -1) {
                    flag = false;
                    break;
                } // 오르막 경사면서 아직 경사로를 설치한 적 없다면
                else if (height == 1 && !used[col]) {
                    // 앞에 L 칸이 없다면 불가
                    if (col < L) {
                        flag = false;
                        break;
                    } // 앞 칸 검사
                    else {
                        for (int idx = col - L; idx < col; idx++) {
                            // 중간에 다른 높이가 있거나 경사로가 있다면 불가
                            if (roads[row][idx] != before || used[idx]) {
                                flag = false;
                                for (int usedIdx = col - L; usedIdx < idx; usedIdx++) {
                                    used[usedIdx] = !used[usedIdx];
                                }
                                break;
                            }
                            used[idx] = true;
                        }
                    }
                } // 내리막 경사면서 아직 경사로를 설치한 적 없다면
                else if (height == -1 && !used[col]) {
                    // 뒤에 L 칸이 없다면 불가
                    if (N - col < L) {
                        flag = false;
                        break;
                    } // 뒤 칸 검사
                    else {
                        for (int idx = col; idx < col + L; idx++) {
                            // 중간에 다른 높이가 있거나 경사로가 있다면 불가
                            if (roads[row][idx] != cur || used[idx]) {
                                flag = false;
                                for (int usedIdx = col; usedIdx < idx; usedIdx++) {
                                    used[usedIdx] = !used[usedIdx];
                                }
                                break;
                            }
                            used[idx] = true;
                        }
                    }
                }
//                else if (col > 1 || col < N-1) {
//                    flag = false;
//                }
                if (!flag)
                    break;

                before = cur;
            }
            if (flag)
                cnt++;
        }

        // 세로
        for (int col = 0; col < N; col++) {
            boolean flag = true;
            boolean[] used = new boolean[N];
            int before = roads[0][col];
            for (int row = 1; row < N; row++) {
                int cur = roads[row][col];

                int height = cur - before;
                // 높이 차가 없으면 패스
                if (height == 0)
                    continue;

                // 높이 차가 2 이상이면 경사로 설치 불가
                if (height > 1 || height < -1) {
                    flag = false;
                    break;
                } // 오르막 경사면서 아직 경사로를 설치한 적 없다면
                else if (height == 1 && !used[row]) {
                    // 앞에 L 칸이 없다면 불가
                    if (row < L) {
                        flag = false;
                        break;
                    } // 앞 칸 검사
                    else {
                        for (int idx = row - L; idx < row; idx++) {
                            // 중간에 다른 높이가 있거나 경사로가 있다면 불가
                            if (roads[idx][col] != before || used[idx]) {
                                flag = false;
                                for (int usedIdx = row - L; usedIdx < idx; usedIdx++) {
                                    used[usedIdx] = !used[usedIdx];
                                }
                                break;
                            }
                            used[idx] = true;
                        }
                    }
                } // 내리막 경사면서 아직 경사로를 설치한 적 없다면
                else if (height == -1 && !used[row]) {
                    // 뒤에 L 칸이 없다면 불가
                    if (N - row < L) {
                        flag = false;
                        break;
                    } // 뒤 칸 검사
                    else {
                        for (int idx = row; idx < row + L; idx++) {
                            // 중간에 다른 높이가 있거나 경사로가 있다면 불가
                            if (roads[idx][col] != cur || used[idx]) {
                                flag = false;
                                for (int usedIdx = row; usedIdx < idx; usedIdx++) {
                                    used[usedIdx] = !used[usedIdx];
                                }
                                break;
                            }
                            used[idx] = true;
                        }
                    }
                }
//                else if (row > 1 || row < N-1) {
//                    flag = false;
//                }
                if (!flag)
                    break;

                before = cur;
            }
            if (flag)
                cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(set());
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        roads = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                roads[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}