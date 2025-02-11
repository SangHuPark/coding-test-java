import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Cal {
        int num;
        String command;

        Cal(int num, String command) {
            this.num = num;
            this.command = command;
        }

        public static int D(int num) {
            num = num << 1;
            if (num > 9999)
                num %= 10000;

            return num;
        }

        public static int S(int num) {
            if (num < 1)
                return 9999;
            else
                return num - 1;
        }

        public static int L(int num) {
            return (num / 1000) + (num % 1000 * 10);
        }

        public static int R(int num) {
            return (num / 10) + (num % 10 * 1000);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int start, answer;

    static Deque<Cal> q;

    public static void play(Cal start) {
        q = new ArrayDeque<>();
        boolean[] checked = new boolean[10000];

        q.add(start);
        checked[start.num] = true;

        while (!q.isEmpty()) {
            Cal cal = q.poll();

            if (cal.num == answer) {
                sb.append(cal.command).append("\n");
                return;
            }

            // D
            int nextNum = Cal.D(cal.num);

            if (!checked[nextNum]) {
                checked[nextNum] = true;
                q.add(new Cal(nextNum, cal.command + "D"));
            }

            // S
            nextNum = Cal.S(cal.num);

            if (!checked[nextNum]) {
                checked[nextNum] = true;
                q.add(new Cal(nextNum, cal.command + "S"));
            }

            // L
            nextNum = Cal.L(cal.num);

            if (!checked[nextNum]) {
                checked[nextNum] = true;
                q.add(new Cal(nextNum, cal.command + "L"));
            }

            // R
            nextNum = Cal.R(cal.num);

            if (!checked[nextNum]) {
                checked[nextNum] = true;
                q.add(new Cal(nextNum, cal.command + "R"));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            st = new StringTokenizer(br.readLine().trim());
            start = Integer.parseInt(st.nextToken());
            answer = Integer.parseInt(st.nextToken());

            play(new Cal(start, ""));
        }

        System.out.println(sb);
    }
}