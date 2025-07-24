import java.io.*;
import java.util.*;

/**
 * BOJ_2166_다각형의면적
 * @author tkdgn407
 *
 * 1. 입력의 좌표를 리스트에 저장한다.
 *  1-1. 첫 번째 좌표를 마지막에 한 번 더 저장한다.
 * 2. 신발끈 공식을 활용해 면적을 계산한다.
 *  2-1. (첫 번째 x 좌표 * 두 번째 y 좌표) - (두 번째 x 좌표 * 첫 번째 y 좌표)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static List<Pos> list;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        list = new ArrayList<>();
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Pos(x, y));
        }
        list.add(list.get(0));

        long sum = 0;
        for (int idx = 0; idx < N; idx++) {
            Pos cur = list.get(idx);
            Pos next = list.get(idx+1);
            sum += ((long) cur.x * next.y) - ((long) next.x * cur.y);
        }
        double answer = Math.abs(sum) / 2.0;

        System.out.printf("%.1f", answer);
    }
}