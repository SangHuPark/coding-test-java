import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Deque<Integer> q;
    static boolean pollStatus;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        q = new ArrayDeque<>();

        for(int idx = 1; idx <= N; idx++) {
            q.add(idx);
        }

        pollStatus = false;

        while(q.size() > 1) {
            if(pollStatus) {
                q.offer(q.poll());
            } else {
                q.poll();
            }
            pollStatus = !pollStatus;
        }

        System.out.println(q.poll());
    }
}