import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final String PUSH_FRONT = "push_front", PUSH_BACK = "push_back", POP_FRONT = "pop_front", POP_BACK = "pop_back", SIZE = "size", EMPTY = "empty", FRONT = "front", BACK = "back";

    static int N;
    static Deque<Integer> deque;

    public static void main(String[] args) throws IOException {
        init();

        int input = 0;
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            String command = st.nextToken();

            switch (command) {
                case PUSH_FRONT:
                    input = Integer.parseInt(st.nextToken());
                    deque.addFirst(input);
                    break;
                case PUSH_BACK:
                    input = Integer.parseInt(st.nextToken());
                    deque.addLast(input);
                    break;
                case POP_FRONT:
                    if (deque.isEmpty())
                        sb.append(-1);
                    else
                        sb.append(deque.pollFirst());
                    sb.append("\n");

                    break;
                case POP_BACK:
                    if (deque.isEmpty())
                        sb.append(-1);
                    else
                        sb.append(deque.pollLast());
                    sb.append("\n");

                    break;
                case SIZE:
                    sb.append(deque.size()).append("\n");
                    break;
                case EMPTY:
                    if (deque.isEmpty())
                        sb.append(1);
                    else
                        sb.append(0);
                    sb.append("\n");

                    break;
                case FRONT:
                    if (deque.isEmpty())
                        sb.append(-1);
                    else
                        sb.append(deque.peekFirst());
                    sb.append("\n");

                    break;
                case BACK:
                    if (deque.isEmpty())
                        sb.append(-1);
                    else
                        sb.append(deque.peekLast());
                    sb.append("\n");

                    break;
            }
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        deque = new ArrayDeque<>();
    }
}
