import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스의 수를 저장하는 testCase
 * 2. 식재료의 수를 저장하는 materialCount
 * 3. 시너지를 저장하는 materialSynergyList
 * 4. 요리의 수(식재료의 수 / 2)를 저장하는 selectMaterialCount
 * 5. materialSynergyList 중 selectMaterialCount 개 만큼 조합을 저장하는 selectFirstList
 * 6. 남은 식재료를 판별하기 위한 selectUsedCheckList
 * 7. 남은 식재료를 전체 원소로 저장하는 secondMaterialList
 *  7-1. 조합을 저장하는 selectSecondList
 * 8. 각 요리의 조합의 합의 차가 가장 작은 경우를 구한다.
 */
public class Solution {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int materialCount;
    public static int selectMaterialCount;

    public static int[][] materialSynergyList;
    public static boolean[] selectUsedCheckList;

    public static int[] selectFirstList;

    public static int[] selectSecondList;

    public static int resultMinSynergy;

    public static void foodCombination(int selectFirstIdx, int materialIdx) {
        if(selectFirstIdx == selectMaterialCount) {
            int selectSecondIdx = 0;
            for(int idx = 0; idx < materialCount; idx++) {
                if(!selectUsedCheckList[idx]) {
                    selectSecondList[selectSecondIdx++] = idx;
                }
            }

            int sumFirstFood = 0;
            for(int row : selectFirstList) {
                for(int col : selectFirstList) {
                    if(row == col) {
                        continue;
                    }

                    sumFirstFood += materialSynergyList[row][col];
                }
            }

            int sumSecondFood = 0;
            for(int row : selectSecondList) {
                for(int col : selectSecondList) {
                    if(row == col) {
                        continue;
                    }

                    sumSecondFood += materialSynergyList[row][col];
                }
            }

            resultMinSynergy = Math.min(resultMinSynergy, Math.abs(sumFirstFood - sumSecondFood));

            return;
        }

        if(materialIdx == materialCount) {
            return;
        }

        selectFirstList[selectFirstIdx] = materialIdx;
        selectUsedCheckList[materialIdx] = true;
        foodCombination(selectFirstIdx + 1, materialIdx + 1);
        selectUsedCheckList[materialIdx] = false;

        selectFirstList[selectFirstIdx] = 0;
        foodCombination(selectFirstIdx, materialIdx + 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            materialCount = Integer.parseInt(br.readLine().trim());
            materialSynergyList = new int[materialCount][materialCount];

            for(int row = 0; row < materialCount; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int col = 0; col < materialCount; col++) {
                    materialSynergyList[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            selectUsedCheckList = new boolean[materialCount];

            selectMaterialCount = materialCount >> 1;
            selectFirstList = new int[selectMaterialCount];
            selectSecondList = new int[selectMaterialCount];
            resultMinSynergy = Integer.MAX_VALUE;

            foodCombination(0, 0);

            sb.append("#").append(tc).append(" ").append(resultMinSynergy).append("\n");
        }

        System.out.println(sb);
    }
}