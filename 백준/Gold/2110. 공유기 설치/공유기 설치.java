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

    static int N, C;
    static int[] disArr;
    static int start, end;
    static int answer;

    // 현재 mid 를 가장 인접한 공유기로 고르면 가능한지 체크
    public static int getCount(int target) {
        int cnt = 1;
        int lastDistance = disArr[0];

        for (int idx = 1; idx < N; idx++) {
            if (disArr[idx] - lastDistance >= target) {
                cnt++;
                lastDistance = disArr[idx];
            }
        }

        return cnt;
    }

    public static void binarySearch() {
        answer = 0;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);

            int wifiCnt = getCount(mid);
            if (wifiCnt >= C) {
                answer = Math.max(answer, mid);
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
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        disArr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            disArr[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(disArr);

        start = 1;
        end = disArr[N-1] - disArr[0];
    }

}