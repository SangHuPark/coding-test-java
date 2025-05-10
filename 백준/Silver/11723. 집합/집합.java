import java.io.*;
import java.util.*;

/**
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static Set<Integer> set;

    public static void check(int input) {
        if (set.contains(input))
            sb.append(1);
        else
            sb.append(0);

        sb.append("\n");
    }

    public static void toggle(int input) {
        if (set.contains(input))
            set.remove(input);
        else
            set.add(input);
    }

    public static void all() {
        for (int num = 1; num <= 20; num++) {
            set.add(num);
        }
    }

    public static void empty() {
        set.clear();
    }

    public static void callFunc(String command, int input) {
        switch (command) {
            case "add": set.add(input); break;
            case "remove": set.remove(input); break;
            case "check": check(input); break;
            case "toggle": toggle(input); break;
            case "all": all(); break;
            case "empty": empty(); break;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        /**
         * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
         * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
         * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
         * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
         * all: S를 {1, 2, ..., 20} 으로 바꾼다.
         * empty: S를 공집합으로 바꾼다.
         */
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            String command = st.nextToken();
            int input = 0;
            if (st.hasMoreTokens())
                input = Integer.parseInt(st.nextToken());
            callFunc(command, input);
        }
        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        set = new HashSet<>();
    }

}