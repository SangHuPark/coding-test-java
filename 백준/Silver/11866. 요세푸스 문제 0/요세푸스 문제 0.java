import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static Deque<Integer> q;

    public static void main(String[] args) throws IOException {
        init();

        sb.append("<");

        int orderCnt = 0;
        while (q.size() > 1) {
            orderCnt++;

            if (orderCnt == K) {
                sb.append(q.poll()).append(", ");
                orderCnt = 0;
            } else {
                q.offer(q.poll());
            }
        }

        sb.append(q.poll()).append(">");

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();
        for (int num = 1; num <= N; num++) {
            q.offer(num);
        }
    }
}
