import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 2. 필름의 두께, 너비, 합격 기준을 입력받는다.
 * 3. 필름의 정보를 입력받는다.
 *
 * [ filmPowerSet() ]
 * 4. 약품을 주입할 행을 선택하는 경우의 수를 부분집합으로 구한다.
 *  4-1. 선택이 끝나면 해당 행에 약품을 주입한다.
 *
 * [ injectToFilm() ]
 * 5. 현재 행과 주입한 횟수를 전달받는다.
 *  5-1. 현재 행이 부분집합의 원소라면
 *      5-1-1. 현재 행에 A, B, (주입안함) 의 경우를 세팅 후 주입했다면 주입한 횟수를 +1 하여 재귀호출한다.
 *  5-2. 모든 행의 주입을 결정했다면 합격 기준을 검사한다.
 *
 * [ testFilm() ]
 * 6. 합격 기준을 검사한다.
 *  6-1. 모든 열 -> 모든 행을 반복하며 해당 행의 합격 기준 만큼 약품이 연속됐는지 검사한다.
 *      6-1-1. 현재 열의 첫번째 행 약품을 저장한다.
 *      6-1-2. 현재 행이 같다면 카운팅한다.
 *      6-1-3. 다르다면 해당 행의 약품으로 바꿔주고 카운트를 1로 초기화한다.
 *      6-1-4. 합격 기준에 만족하지 않았다면 false 를 반환한다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static final int medicineA = 0;
    static final int medicineB = 1;

    public static int filmDepth, filmWidth, successNum;
    public static int[][] originFilmMap;
    public static int[][] newFilmMap;

    // 부분집합 체크용 배열
    public static boolean[] rowUsedCheck;

    public static int minInjectCount;

    public static void filmPowerSet(int selectIdx) {
        // 모든 행의 선택을 결정했다면 종료
        if (selectIdx == filmDepth) {
            newFilmMap = new int[filmDepth][filmWidth];
            arrayCopy();

            // 선택한 정보를 바탕으로 약품 주입해보기
            injectMedicine(0, 0);

            return;
        }

        rowUsedCheck[selectIdx] = true;
        filmPowerSet(selectIdx + 1);

        rowUsedCheck[selectIdx] = false;
        filmPowerSet(selectIdx + 1);
    }

    public static void injectMedicine(int injectRow, int injectCount) {
        // 백트래킹: 이미 현재 최소 횟수보다 약품 주입을 많이 했다면 X
        if (injectCount > minInjectCount) {
            return;
        }

        // 모든 행의 약품 주입을 선택했다면 종료
        if (injectRow == filmDepth) {
            // 합격 기준을 부합했다면 최소값 갱신
            if(testFilm()) {
                minInjectCount = Math.min(minInjectCount, injectCount);
            }

            return;
        }

        // 부분집합의 원소라면 주입 결정
        if (rowUsedCheck[injectRow]) {
            // A 약품 주입
            Arrays.fill(newFilmMap[injectRow], medicineA);
            injectMedicine(injectRow + 1, injectCount + 1);

            // B 약품 주입
            Arrays.fill(newFilmMap[injectRow], medicineB);
            injectMedicine(injectRow + 1, injectCount + 1);
        } // 원소가 아니라면 주입하지 않고 넘긴다.
        else {
            injectMedicine(injectRow + 1, injectCount);
        }
    }

    public static boolean testFilm() {
        for (int col = 0; col < filmWidth; col++) {
            int cell = newFilmMap[0][col];
            int testCount = 0;
            boolean testResult = false;
            for (int row = 0; row < filmDepth; row++) {
                if(cell == newFilmMap[row][col]) {
                    testCount++;
                } else {
                    cell = newFilmMap[row][col];
                    testCount = 1;
                }

                // 중간에 합격 기준을 부합하면 더 볼 필요가 없음
                if (testCount == successNum) {
                    testResult = true;
                    break;
                }
            }

            // 합격 기준을 부합하지 못한 열이라면 통과를 못한 것이니 더 볼 필요가 없음
            if (!testResult) {
                return false;
            }
        }

        return true;
    }

    public static void arrayCopy() {
        for (int row = 0; row < filmDepth; row++) {
            for (int col = 0; col < filmWidth; col++) {
                newFilmMap[row][col] = originFilmMap[row][col];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            rowUsedCheck = new boolean[filmDepth];
            minInjectCount = Integer.MAX_VALUE;
            filmPowerSet(0);

            sb.append("#").append(tc).append(" ").append(minInjectCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        filmDepth = Integer.parseInt(st.nextToken());
        filmWidth = Integer.parseInt(st.nextToken());
        successNum = Integer.parseInt(st.nextToken());

        originFilmMap = new int[filmDepth][filmWidth];
        for (int row = 0; row < filmDepth; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < filmWidth; col++) {
                originFilmMap[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}