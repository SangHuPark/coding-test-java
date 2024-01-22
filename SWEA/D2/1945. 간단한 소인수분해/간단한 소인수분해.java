import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 1945. 간단한 소인수분해
 * 1. 소인수분해할 숫자 originalNum 생성
 * 2. 소인수 2, 3, 4, 7, 11 을 갖는 배열 divideArr 생성
 * 3. 각 제곱을 저장할 resultArr 생성
 * 4. originalNum 을 divideArr 의 0번째 인덱스를 나눈 나머지 값 검사
 *  4-1. 나머지가 0 일 때마다 resultArr 의 인덱스 값을 +1
 *  4-2. 나머지가 0 이 아니라면 더 이상 나눠지지 않는 것이므로 다음 인덱스로 이동
 *  4-3. originalNum 은 / divideArr[idx] 로 변경
 * 5. originalNum 이 1이 될 때까지 진행
 */
public class Solution {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String args[]) throws Exception
    {
        br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            // 1. 소인수분해할 숫자 originalNum 생성
            int originalNum = Integer.parseInt(br.readLine().trim());
            // 2. 소인수 2, 3, 4, 7, 11 을 갖는 배열 divideArr 생성
            int[] divideArr = {2, 3, 5, 7, 11};
            // 3. 각 제곱을 저장할 resultArr 생성
            int[] resultArr = new int[5];
            int idx = 0;

            // 5. originalNum 이 1이 될 때까지 진행
            while(originalNum != 1) {
                // 4. originalNum 을 divideArr 의 0번째 인덱스를 나눈 나머지 값 검사
                if (originalNum % divideArr[idx] == 0) {
                    resultArr[idx]++;
                    originalNum /= divideArr[idx];
                } else {
                    idx++;
                }
            }

            System.out.printf("#%d ", tc);
            for (int printIdx = 0; printIdx < 5; printIdx++) {
                System.out.printf("%d ", resultArr[printIdx]);
            }
            System.out.println();
        }
    }
}