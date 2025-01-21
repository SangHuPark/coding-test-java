import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 용액 수 N 입력
 * 2. 용액의 특성값 배열 입력
 *  2-1. 특성값은 항상 오름차순으로 들어온다.
 * 3. lt = 0, rt = 1 에서 시작
 *  3-1. 현재 두 용액의 합 sum(|lt + rt| 에서 시작) 저장
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] numArr;
    static int[] answers;

    public static void twoPointer() {
        long min = Long.MAX_VALUE;

        int lt = 0, rt = N-1;
        while (lt < rt) {
            long sum = numArr[lt] + numArr[rt];

            long absSum = Math.abs(sum);
            if (absSum < min) {
                answers[0] = lt;
                answers[1] = rt;
                min = absSum;
            }

            if (sum >= 0)
                rt--;
            else
                lt++;
        }

        System.out.println(numArr[answers[0]] + " " + numArr[answers[1]]);
    }

    public static void main(String[] args) throws IOException {
        init();

        twoPointer();

    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        numArr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }

        answers = new int[2];
    }

}