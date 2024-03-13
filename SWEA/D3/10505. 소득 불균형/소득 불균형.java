import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 각 테스트 케이스 수를 저장
 * 2. 입력으로 들어오는 소득을 배열에 저장
 *  2-1. 동시에 총합 계산
 * 3. 소득 총합 / 테스트 케이스 수 저장
 *  3-1. 배열에 요소 중 이보다 작은 경우를 카운팅
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int totalHumanCount;
    public static int[] humanList;
    public static int sumMoney;
    public static int moneyAvg;
    public static int lessHumanCount;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            totalHumanCount = Integer.parseInt(br.readLine().trim());

            sumMoney = 0;
            st = new StringTokenizer(br.readLine().trim());
            humanList = new int[totalHumanCount];
            for (int idx = 0; idx < totalHumanCount; idx++) {
                humanList[idx] = Integer.parseInt(st.nextToken());
                sumMoney += humanList[idx];
            }

            moneyAvg = sumMoney / totalHumanCount;

            lessHumanCount = 0;
            for (int human : humanList) {
                if(human <= moneyAvg) {
                    lessHumanCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(lessHumanCount).append("\n");
        }

        System.out.println(sb);
    }

}