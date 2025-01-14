import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 4 11
 * 802
 * 743
 * 457
 * 539
 *
 * 200
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] sanggeun, cards;

    public static void binarySearch() {
        int start, end;

        for (int card : cards) {
            start = 0; end = N-1;

            boolean flag = false;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);

                if (sanggeun[mid] < card) {
                    start = mid+1;
                } else if (sanggeun[mid] == card) {
                    sb.append(1).append(" ");
                    flag = true;
                    break;
                } else {
                    end = mid-1;
                }
            }

            if (!flag)
                sb.append(0).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        sanggeun = new int[N];
        for (int idx = 0; idx < N; idx++) {
            sanggeun[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sanggeun);

        M = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        cards = new int[M];
        for (int idx = 0; idx < M; idx++) {
            cards[idx] = Integer.parseInt(st.nextToken());
        }
    }

}