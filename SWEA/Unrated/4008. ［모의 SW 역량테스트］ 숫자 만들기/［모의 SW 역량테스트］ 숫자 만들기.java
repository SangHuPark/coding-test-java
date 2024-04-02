import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 수식에 사용되는 숫자의 개수를 입력받는다.
 * 2. 4 종류의 각 연산자 개수를 입력받는다.
 *  2-1. 4 의 길이를 가진다.
 * 3. 수식에 사용되는 숫자를 입력받는다.
 * 4. 입력의 모든 연산자를 사용한 수식을 만드는 함수를 만든다.
 *  4-1. 연산자의 개수를 모두 선택하면 종료한다.
 *  4-2. 현재 연산 결과의 최대값과 최소값을 갱신한다.
 * 5. 최댓값과 최솟값의 차를 출력한다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int NUM_COUNT;
    public static final int OPERATOR_COUNT = 4;

    public static int[] operatorList;
    public static int[] numList;

    public static int[] selectOperatorList;

    public static int maxOperateNum, minOperateNum;
    public static int subtractResult;

    public static void operateDFS(int selectIdx) {
        // 4-1. 연산자의 개수를 모두 선택하면 종료한다.
        if (selectIdx == NUM_COUNT-1) {
            calculate();

            return;
        }

        for (int operater = 0; operater < OPERATOR_COUNT; operater++) {
            // 해당 연산자가 주어지지 않은 연산자라면 패스
            if (operatorList[operater] == 0) {
                continue;
            }

            // 사용한 의미로 해당 연산자 인덱스 값 제거
            operatorList[operater]--;
            // 현재 선택한 연산자를 저장
            selectOperatorList[selectIdx] = operater;
            operateDFS(selectIdx + 1);
            // 해당 연산자 사용 복구
            operatorList[operater]++;
            selectOperatorList[selectIdx] = 0;
        }
    }

    public static void calculate() {
        // 초기값 세팅
        int calculateResult = numList[0];

        for (int idx = 0; idx < NUM_COUNT-1; idx++) {
            // 선택한 연산자를 하나씩 받아 연산
            switch (selectOperatorList[idx]) {
                case 0 :  calculateResult += numList[idx+1]; break;
                case 1 :  calculateResult -= numList[idx+1]; break;
                case 2 :  calculateResult *= numList[idx+1]; break;
                case 3 :  calculateResult /= numList[idx+1]; break;
            }
        }

        maxOperateNum = Math.max(maxOperateNum, calculateResult);
        minOperateNum = Math.min(minOperateNum, calculateResult);
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            // 1. 수식에 사용되는 숫자의 개수를 입력받는다.
            NUM_COUNT = Integer.parseInt(br.readLine().trim());
            operatorList = new int[OPERATOR_COUNT];
            numList = new int[NUM_COUNT];

            // 2. 4 종류의 각 연산자 개수를 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < OPERATOR_COUNT; idx++) {
                operatorList[idx] = Integer.parseInt(st.nextToken());
            }

            // 3. 수식에 사용되는 숫자를 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < NUM_COUNT; idx++) {
                numList[idx] = Integer.parseInt(st.nextToken());
            }

            maxOperateNum = Integer.MIN_VALUE;
            minOperateNum = Integer.MAX_VALUE;

            selectOperatorList = new int[NUM_COUNT-1];

            operateDFS(0);

            subtractResult = maxOperateNum - minOperateNum;

            sb.append("#").append(tc).append(" ").append(subtractResult).append("\n");
        }

        System.out.println(sb);
    }
}