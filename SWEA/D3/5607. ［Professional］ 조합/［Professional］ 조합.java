import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - nCr = n! / r! * (n-r)!
 * - n! * ((n-r)! * (r!))^(p-2)
 *
 * 1. factorialTable 을 생성한다.
 *  1-1. 조합의 분모 부분을 저장한다.
 * 2. 조합에 이용할 N, R 을 입력받는다.
 * 3. 페르마의 소정리에서 a (mod p) 의 역원이 a^(p-2) (mod p) 임을 이용해 (n-r)! * (r!) 의 p-2 승을 구한다.
 *  3-1. 두 수를 입력받아 지수를 반환하는 pow 함수
 * 4. N * (구한 분모) % 1234567891 를 출력한다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static final int MOD_NUM = 1234567891;

    public static long[] factorialTable;

    public static int ELEMENT_NUM, SELECT_NUM;

    public static long denom, powerResult, modResult;

    public static long pow(long target, int power) {
        // 0 이면 모든 수의 0 승인 1 반환
        if (power == 0) {
            return 1;
        } // 1 이면 target 반환
        else if (power == 1) {
            return target;
        }

        // 지수가 짝수일 때
        if (power % 2 == 0) {
            long tmp = pow(target, power >> 1);
            return (tmp * tmp) % MOD_NUM;
        }

        // 짝수가 아니라면
        long tmp = pow(target, power - 1);
        return (tmp * target) % MOD_NUM;
    }

    public static void main(String[] args) throws IOException {
        // 1. factorialTable 을 생성한다.
        factorialTable = new long[1000001];
        factorialTable[1] = 1;
        for (int factorial = 2; factorial <= 1000000; factorial++) {
            factorialTable[factorial] = (factorialTable[factorial - 1] * factorial) % MOD_NUM;
        }

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            ELEMENT_NUM = Integer.parseInt(st.nextToken());
            SELECT_NUM = Integer.parseInt(st.nextToken());

            // 분모 생성
            denom = (factorialTable[ELEMENT_NUM - SELECT_NUM] * factorialTable[SELECT_NUM]) % MOD_NUM;

            // 페르마 소정리를 이용한 지수 저장
            powerResult = pow(denom, MOD_NUM - 2);

            modResult = (factorialTable[ELEMENT_NUM] * powerResult) % MOD_NUM;

            sb.append("#").append(tc).append(" ").append(modResult).append("\n");
        }

        System.out.println(sb);
    }
}