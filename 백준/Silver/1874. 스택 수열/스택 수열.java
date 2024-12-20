import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [K 번째 큰 수]
 * 1. 숫자의 개수 N, 뽑을 개수 K
 * 2. N 개의 숫자를 배열에 저장
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] answer;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        init();

        stack = new Stack<>();
        int answerIdx = 0;
        for (int num = 1; num <= N; num++) {
            stack.push(num);
            sb.append("+").append("\n");

            while (!stack.isEmpty() && stack.peek() == answer[answerIdx]) {
                stack.pop();
                sb.append("-").append("\n");
                answerIdx++;
            }
        }

        if (answerIdx == N)
            System.out.println(sb);
        else
            System.out.println("NO");
//
//        System.out.println(answerIdx);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        answer = new int[N];
        for (int idx = 0; idx < N; idx++) {
            answer[idx] = Integer.parseInt(br.readLine().trim());
        }

    }

}