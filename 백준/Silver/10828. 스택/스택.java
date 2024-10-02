import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final String push = "push";
    static final String top = "top";
    static final String size = "size";
    static final String empty = "empty";
    static final String pop = "pop";
    static int cmdCount;
    static Stack<Integer> stack;

    public static void solution(String input, int num) {
        switch (input) {

            case push:
                stack.push(num);
                break;

            case pop:
                if (stack.empty())
                    sb.append(-1);
                else
                    sb.append(stack.pop());
                sb.append("\n");
                break;

            case size:
                sb.append(stack.size());
                sb.append("\n");
                break;

            case empty:
                if (stack.empty())
                    sb.append(1);
                else
                    sb.append(0);
                sb.append("\n");
                break;

            case top:
                if (stack.empty())
                    sb.append(-1);
                else
                    sb.append(stack.peek());
                sb.append("\n");
                break;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(sb);

    }

    public static void init() throws IOException {
        cmdCount = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        for (int idx = 0; idx < cmdCount; idx++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens())
                num = Integer.parseInt(st.nextToken());

            solution(input, num);
        }
    }
}