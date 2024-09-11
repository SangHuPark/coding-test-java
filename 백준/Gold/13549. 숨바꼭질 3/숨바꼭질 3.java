import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 */
public class Main {
    static class Pos {
        int idx;
        int sec;

        public Pos(int idx, int sec) {
            this.idx = idx;
            this.sec = sec;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int start, dest;

    static Deque<Pos> q;

    public static void bfs(Pos startPos) {
        q = new ArrayDeque<Pos>();
        boolean[] visted = new boolean[1000000];

        q.push(startPos);
        visted[startPos.idx] = true;

        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            // idx 가 도착지면 종료
            if (curPos.idx == dest) {
                System.out.println(curPos.sec);
                return;
            }

            // 순간이동
            if (curPos.idx << 1 < 1000000 && !visted[curPos.idx << 1]) {
                q.add(new Pos(curPos.idx << 1, curPos.sec));
                visted[curPos.idx << 1] = true;
            }

            // X-1 이동
            if (curPos.idx > 0 && !visted[curPos.idx-1]) {
                q.add(new Pos(curPos.idx - 1, curPos.sec + 1));
                visted[curPos.idx-1] = true;
            }

            // X+1 이동
            if (curPos.idx < 999999 && !visted[curPos.idx+1]) {
                q.add(new Pos(curPos.idx + 1, curPos.sec + 1));
                visted[curPos.idx+1] = true;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        bfs(new Pos(start, 0));
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
    }
}