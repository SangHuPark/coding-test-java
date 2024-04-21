import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 소수를 판별할 범위의 시작 숫자, 마지막 숫자를 입력받는다.
 * 2. primeList 배열을 true 로 초기화
 *
 * [ isPrime() ]
 * 3. 소수 중 가장 작은 2 부터 소수일 때 해당 수의 배수들을 모두 false 로 전환
 *  3-1. 소수 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int startNum, endNum;
    static boolean[] primeList;

    public static void isPrime() {
        for (int num = 2; num <= Math.sqrt(endNum); num++) {
            if (primeList[num]) {

                for (int excludeNum = num * num; excludeNum <= endNum; excludeNum += num) {
                    primeList[excludeNum] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        isPrime();

        for (int num = startNum; num <= endNum; num++) {
            if (primeList[num])
                sb.append(num).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        startNum = Integer.parseInt(st.nextToken());
        endNum = Integer.parseInt(st.nextToken());

        primeList = new boolean[endNum+1];
        for (int idx = 0; idx < endNum+1; idx++) {
            primeList[idx] = true;
        }

        primeList[0] = false;
        primeList[1] = false;
    }
}