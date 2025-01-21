import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] numArr;
    static int answerLeftIdx, answerRightIdx;

    public static void twoPointer() {
        int min = Integer.MAX_VALUE;

        int lt = 0, rt = N-1;
        while (lt < rt) {
            int sum = numArr[lt] + numArr[rt];

            int absSum = Math.abs(sum);
            if (absSum < min) {
                answerLeftIdx = lt;
                answerRightIdx = rt;
                min = absSum;
            }

            if (sum >= 0)
                rt--;
            else
                lt++;
        }

        System.out.println(numArr[answerLeftIdx] + " " + numArr[answerRightIdx]);
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
    }

}