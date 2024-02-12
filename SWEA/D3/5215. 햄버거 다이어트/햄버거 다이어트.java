import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * - 재료의 수 N, 1 <= N <= 20
 * - 제한 칼로리 L, 1 <= L <= 10^4
 * - 점수 T, 1 <= T <= 10^3
 * - 칼로리 K, 1 <= K <= 10^3
 *
 * 1. 테스트 케이스의 수를 저장하는 testCase
 * 2. 재료의 수 N 을 저장하는 elementCount
 * 3. 제한 칼로리 L 을 저장하는 limitCalories
 * 4. 점수 T 를 저장하는 scoreList
 * 5. 칼로리 K 를 저장하는 caloriesList
 * 6. 각 재료로 1 ~ N 개 조합 생성
 *  6-1. 선택할 1 ~ N 개를 저장하는 selectElementCount
 *  6-2. 선택한 원소를 저장하는 selectCaloriesList
 *  6-3. 완성된 조합이 limitCalories 를 넘지 않을 때 점수의 최대값을 저장하는 bestScore
 */
public class Solution {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int elementCount;
    public static int limitCalories;

    public static int[] scoreList;
    public static int[] caloriesList;
    public static boolean[] selectIndexList;

    public static int selectElementCount;
    public static int[] selectCaloriesList;

    public static int bestScore;

    public static void hamburgerCombination(int selectIdx, int elementIdx, int sumScore, int sumCalorie) {
        if(sumCalorie > limitCalories) {
            return;
        }
        
        if(selectIdx == selectElementCount) {
            bestScore = Math.max(bestScore, sumScore);
            return;
        }

        if(elementIdx == elementCount) {
            return;
        }


        selectCaloriesList[selectIdx] = caloriesList[elementIdx];
        hamburgerCombination(selectIdx + 1, elementIdx + 1, sumScore + scoreList[elementIdx], sumCalorie + caloriesList[elementIdx]);

        selectCaloriesList[selectIdx] = 0;
        hamburgerCombination(selectIdx, elementIdx + 1, sumScore, sumCalorie);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            elementCount = Integer.parseInt(st.nextToken());
            limitCalories = Integer.parseInt(st.nextToken());

            scoreList = new int[elementCount];
            caloriesList = new int[elementCount];
            for(int idx = 0; idx < elementCount; idx++) {
                st = new StringTokenizer(br.readLine().trim());
                scoreList[idx] = Integer.parseInt(st.nextToken());
                caloriesList[idx] = Integer.parseInt(st.nextToken());
            }

            bestScore = 0;

            for(int select = 1; select <= elementCount; select++) {
                selectElementCount = select;
                selectCaloriesList = new int[selectElementCount];
                hamburgerCombination(0, 0, 0, 0);
            }

            sb.append("#").append(tc).append(" ").append(bestScore).append("\n");
        }

        System.out.println(sb);
    }
}