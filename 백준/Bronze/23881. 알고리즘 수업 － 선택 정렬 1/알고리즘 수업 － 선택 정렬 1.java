import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static int[] selectionSort;

    public static void main(String[] args) throws IOException {
        init();

        int switchCnt = 0;
        for (int last = N - 1; last >= 0; last--) {
            int curMax = selectionSort[last];
            int curMaxIdx = last;
            for (int idx = 0; idx <= last; idx++) {
                if (curMax < selectionSort[idx]) {
                    curMax = selectionSort[idx];
                    curMaxIdx = idx;
                }
            }

            if (last != curMaxIdx) {
                switchCnt++;
                int tmp = selectionSort[last];
                selectionSort[last] = curMax;
                selectionSort[curMaxIdx] = tmp;

                if (switchCnt == K) {
                    System.out.println(selectionSort[curMaxIdx] + " " + selectionSort[last]);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        selectionSort = new int[N];
        for (int idx = 0; idx < N; idx++) {
            selectionSort[idx] = Integer.parseInt(st.nextToken());
        }
    }
}
