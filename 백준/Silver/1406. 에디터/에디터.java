import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1406
 * ---
 * 내가 예상한 카테고리: 구현
 * ---
 * 자연어 풀이
 * 1. 커서를 기준으로 연결 리스트 left, right 를 관리한다.
 * 2. 'L': left 가 empty 면 무시한다.
 *  2-1. left 마지막 문자를 right 로 이동한다.
 * 3. 'D': right 가 empty 면 무시한다.
 *  3-1. right 첫번째 문자를 left 로 이동한다.
 * 4. 'P (문자)': left 에 문자를 추가한다.
 * 5. 'B': left 가 empty 면 무시한다.
 *  5-1. left 마지막 문자를 삭제한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static LinkedList<Character> left, right;

    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        left = new LinkedList<>();
        for (int idx = 0; idx < input.length(); idx++) {
            left.add(input.charAt(idx));
        }

        right = new LinkedList<>();
        int commandCnt = Integer.parseInt(br.readLine().trim());
        for (int command = 0; command < commandCnt; command++) {
            st = new StringTokenizer(br.readLine().trim());
            switch (st.nextToken()) {
                case "L": L(); break;
                case "D": D(); break;
                case "B": B(); break;
                case "P": P(st.nextToken().charAt(0)); break;
            }
        }

        for (char ch : left) {
            sb.append(ch);
        }

        for (char ch : right) {
            sb.append(ch);
        }
        System.out.println(sb);
    }

    public static void L() {
        if (left.isEmpty())
            return;

        right.addFirst(left.removeLast());
    }

    public static void D() {
        if (right.isEmpty())
            return;

        left.addLast(right.removeFirst());
    }

    public static void B() {
        if (left.isEmpty())
            return;

        left.removeLast();
    }

    public static void P(char x) {
        left.addLast(x);
    }

}