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

    static int K, N;
    static long[] lengths;
    static long start, end;
    static long answer;

    public static int getCount(long capacity) {
        int cnt = 0;

        for (long length : lengths) {
            cnt += (int) (length / capacity);
        }

        return cnt;
    }

    public static void binarySearch() {
        answer = 0;

        while (start <= end) {
            long mid = start + ((end - start) >> 1);

            if (getCount(mid) >= N) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        System.out.println(answer);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lengths = new long[K];
        start = 1; end = 0;
        for (int idx = 0; idx < K; idx++) {
            lengths[idx] = Integer.parseInt(br.readLine().trim());
            end += lengths[idx];
        }
    }

}