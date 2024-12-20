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

    static int testCase;
    static String parentheses;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            parentheses = br.readLine().trim();

            stack = new Stack<>();

            boolean flag = true;
            int length = parentheses.length();
            for (int idx = 0; idx < length; idx++) {
                char parenthesis = parentheses.charAt(idx);
                if (parenthesis == '(') {
                    stack.push(1);
                } else if (parenthesis == ')') {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }

                    stack.pop();
                }
            }

            if (!stack.isEmpty() || !flag)
                sb.append("NO").append("\n");
            else
                sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }

}