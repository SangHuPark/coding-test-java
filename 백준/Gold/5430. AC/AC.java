import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 *
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static char[] operations;
    static int N;
    static Deque<Integer> list;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            operations = br.readLine().trim().toCharArray();

            N = Integer.parseInt(br.readLine().trim());
            list = new LinkedList<>();

            if (N != 0) {
                String tmp = br.readLine().trim().replace("[", "").replace("]", "");
                String[] input = new String[N];
                if (tmp.contains(",")) {
                    input = tmp.split(",");
                } else {
                    input[0] = tmp;
                }

                for (int idx = 0; idx < N; idx++) {
                    list.add(Integer.parseInt(input[idx]));
                }
            } else {
                br.readLine().trim();
            }
            play();
        }
        System.out.println(sb);
    }

    static void play() {
        boolean isReversed = false;
        for (char operation : operations) {
            // 뒤집기: 방향만 뒤집음
            if (operation == 'R') {
                isReversed = !isReversed;
            } // 방향에 따라 가장 앞 혹은 가장 뒤를 뺌
            else {
                if (list.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                // false 면 뒤집힌 적이 없으므로 앞의 것을 뺌
                if (!isReversed)
                    list.pollFirst();
                // true 면 뒤집힌 적이 있으므로 앞의 것이 가장 뒤로 와 있음
                else
                    list.pollLast();
            }
        }

        sb.append("[");
        while (!list.isEmpty()) {
            // 뒤집힌 상태라면 뒤에서부터 뺌
            if (isReversed)
                sb.append(list.pollLast());
            // 아니라면 앞에서부터
            else
                sb.append(list.pollFirst());

            if (!list.isEmpty())
                sb.append(",");
        }
        sb.append("]").append("\n");
    }
}