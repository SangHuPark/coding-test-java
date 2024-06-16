import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. min, max 입력
 *  1-1. 1조 1_000_000 까지 입력받도록 long 사용
 * 2. max - min + 1 의 numCount 생성
 *
 * [ solution ]
 * - 에라토스테네스의 체에서 2 이상인 소수의 배수는 모두 제외하는 아이디어 적용
 * 3. 제곱ㅇㅇ수를 찾아 전체 숫자에서 빼면 정답
 *  3-1. 2부터 max 까지 진행
 *  3-2. 1보다 큰 정수 제곱근(Math.sqrt(max) 까지) 그 수의 배수는 제곱ㅇㅇ수 이므로 해당하는 수를 모두 카운팅
 *
 * [ main ]
 * 4. numCount - targetCount 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long min, max;
    static long numCount, targetCount;
    static boolean[] checked;

    public static void solution() {
        long sqrtMax = (long) Math.sqrt(max);

        for (long num = 2; num <= sqrtMax; num++) {
            long powDivideNum = num * num;
            long startNum = min / powDivideNum;
            if (min % powDivideNum != 0) {
                startNum++;
            }
            
            for (long divideNum = startNum; divideNum*powDivideNum <= max; divideNum++) {
                int sqrtIdx = (int) (divideNum * powDivideNum - min);
                if (!checked[sqrtIdx]) {
                    checked[sqrtIdx] = true;
                    numCount--;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        solution();

        System.out.println(numCount);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        numCount = max - min + 1;
        checked = new boolean[(int) numCount];
    }
}