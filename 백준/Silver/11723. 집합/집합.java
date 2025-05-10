import java.io.*;
import java.util.*;

/**
 * 1. 20개의 숫자를 `bitmask`로 표현하여 비트마스킹 처리한다.
 * 2. `add`는 해당 위치의 비트를 켜고, `remove`는 끈다.
 * 3. `check`는 비트가 켜져 있는지 확인하고, `toggle`은 반전시킨다.
 * 4. `all`은 20개의 비트를 전부 1로 켜고, `empty`는 0으로 초기화한다.
 * 5. 모든 연산을 **O(1)**로 빠르게 처리한다
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, bitmask;

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
    }

    public static void main(String[] args) throws IOException {
        init();

        bitmask = 0;
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

    public static void callFunc(String command, int input) {
        switch (command) {
            case "add": add(input); break;
            case "remove": remove(input); break;
            case "check": check(input); break;
            case "toggle": toggle(input); break;
            case "all": all(); break;
            case "empty": empty(); break;
        }
    }

    // add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
    // 해당 위치에 비트를 킨다.
    public static void add(int x) {
        bitmask = bitmask | (1 << (x-1));
    }

    // remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
    // 해당 위치에 비트를 끈다.
    public static void remove(int x) {
        bitmask = bitmask & ~(1 << (x - 1));
    }

    // check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
    // 만약 4를 추가한다면 1000. 하지만 1000은 8 이므로 & 연산의 결과가 0이 아닐 때 해당 비트가 켜져있는 것
    public static void check(int x) {
        sb.append((bitmask & (1 << (x - 1))) != 0 ? 1 : 0).append("\n");
    }

    // toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
    // XOR 연산
    public static void toggle(int x) {
        bitmask = bitmask ^ (1 << (x-1));
    }

    // all: S를 {1, 2, ..., 20} 으로 바꾼다.
    public static void all() {
        bitmask = bitmask | ((1 << 20) - 1);
    }

    // empty: S를 공집합으로 바꾼다.
    public static void empty() {
        bitmask = 0;
    }
}