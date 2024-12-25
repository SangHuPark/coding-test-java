import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        pq = new PriorityQueue<>();
        for (int idx = 0; idx < N; idx++) {
            pq.offer(Integer.parseInt(br.readLine().trim()));
        }
    }
}
