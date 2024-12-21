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
    static StringTokenizer st;

    static final String PUSH = "push", POP = "pop", SIZE = "size", EMPTY = "empty", FRONT = "front", BACK = "back";

    static int orderCnt;
    static Deque<Integer> q;
    static int num;

    public static void main(String[] args) throws IOException {
        orderCnt = Integer.parseInt(br.readLine().trim());

        q = new ArrayDeque<>();
        for (int order = 1; order <= orderCnt; order++) {
            st = new StringTokenizer(br.readLine().trim());

            switch (st.nextToken()) {
                case PUSH:
                    num = Integer.parseInt(st.nextToken());
                    q.push(num);
                    break;
                case POP:
                    if (q.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(q.pollLast()).append("\n");
                    break;
                case SIZE:
                    sb.append(q.size()).append("\n");
                    break;
                case EMPTY:
                    if (q.isEmpty())
                        sb.append(1).append("\n");
                    else
                        sb.append(0).append("\n");
                    break;
                case FRONT:
                    if (q.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(q.peekLast()).append("\n");
                    break;
                case BACK:
                    if (q.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(q.peekFirst()).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }

}