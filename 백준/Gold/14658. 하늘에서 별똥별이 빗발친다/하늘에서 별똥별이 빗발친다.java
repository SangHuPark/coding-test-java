import java.io.*;
import java.util.*;

/**
 * 1. colSize, rowSize, range, starCount를 저장한다.
 * 2. 각 별똥별의 좌표를 -1 씩 하여 map과 리스트에 저장한다.
 * 3. 리스트에서 별똥별의 (x, y)를 하나씩 빼서, 해당 좌표를 시작점으로 (x - range ~ x + range, y - range ~ y + range) 범위의 별똥별을 카운팅한다.
 * 4. 리스트.size()에서 별똥별 수의 최대값을 뺀 값을 출력한다.
 */
public class Main {
    static class Star implements Comparable<Star> {
        int row;
        int col;

        Star(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int compareTo(Star o) {
            if (this.row == o.row)
                return Integer.compare(this.col, o.col);

            return Integer.compare(this.row, o.row);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int colSize, rowSize, range, starCnt;
    static List<Star> stars;

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());
        starCnt = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();
        for (int star = 0; star < starCnt; star++) {
            st = new StringTokenizer(br.readLine().trim());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            stars.add(new Star(row, col));
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        int maxStarCnt = Integer.MIN_VALUE;
        for (int curIdx = 0; curIdx < starCnt; curIdx++) {
            for (int otherIdx = 0; otherIdx < starCnt; otherIdx++) {
                maxStarCnt = Math.max(maxStarCnt, getStar(stars.get(curIdx).row, stars.get(otherIdx).col));
            }
        }
        System.out.println(starCnt - maxStarCnt);
    }

    public static int getStar(int x, int y) {
        int cnt = 0;
        int maxCnt = Integer.MIN_VALUE;

        for (Star star : stars) {
            if (star.row >= x && star.row <= x + range && star.col >= y && star.col <= y + range)
                cnt++;
        }

        maxCnt = Math.max(maxCnt, cnt);

        return maxCnt;
    }
}