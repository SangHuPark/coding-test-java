import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 점원의 수와 선반의 높이를 입력받는다.
 * 2. 점원의 키를 입력받는다.
 * 3. 점원을 선택하는 순서는 의미가 없으므로 조합을 생성한다.
 *  3-1. 선택 시 키를 더해 넘겨준다.
 *  3-2. 선택하지 않는다면 다음 점원의 인덱스로만 넘겨준다.
 *  3-3. 선반의 높이를 넘었다면 최소값을 갱신 한 뒤 백트래킹한다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int humanCount, limitHeight;
    public static int[] heightList;
    public static boolean[] usedHeightCheck;

    public static int minHeight;

    public static void countHeight(int selectCount, int human, int curTotalHeight) {
        // 이미 선반의 높이를 넘었다면 종료
        if (curTotalHeight > limitHeight) {
            minHeight = Math.min(minHeight, curTotalHeight);
            return;
        }

        // 모든 선택을 끝내면 종료
        if (selectCount == humanCount) {
            if (curTotalHeight >= limitHeight) {
                minHeight = Math.min(minHeight, curTotalHeight);
            }
            return;
        }

        // 모든 점원 선택 시 종료
        if (human == humanCount) {
            if (curTotalHeight >= limitHeight) {
                minHeight = Math.min(minHeight, curTotalHeight);
            }
            return;
        }

        // 선택할 때
        countHeight(selectCount + 1, human + 1, curTotalHeight + heightList[human]);

        // 선택하지 않을 때
        countHeight(selectCount, human + 1, curTotalHeight);
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            input();

            usedHeightCheck = new boolean[humanCount];
            minHeight = Integer.MAX_VALUE;

            countHeight(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minHeight - limitHeight).append("\n");
        }

        System.out.println(sb);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        humanCount = Integer.parseInt(st.nextToken());
        limitHeight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        heightList = new int[humanCount];
        for (int idx = 0; idx < humanCount; idx++) {
            heightList[idx] = Integer.parseInt(st.nextToken());
        }
    }
}