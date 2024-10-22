import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] trees;
    static int minTreeHeight;
    static int maxTreeHeight;

    // upperBound
    public static void cutTree() {
        int left = 0;
        int right = maxTreeHeight;
        int mid;
        long sum = 0;

        while (left < right) {
            mid = left + ((right - left) >> 1);

            for (int idx = 0; idx < N; idx++) {
                if (trees[idx] > mid)
                    sum += trees[idx] - mid;
            }

            if (M > sum) {
                right = mid;
            } else {
                left = mid + 1;
            }

            sum = 0;
        }

        System.out.println(right-1);
    }

    public static void main(String[] args) throws IOException {
        init();

        cutTree();

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        minTreeHeight = Integer.MAX_VALUE;
        maxTreeHeight = 0;
        trees = new int[N];
        for (int idx = 0; idx < N; idx++) {
            trees[idx] = Integer.parseInt(st.nextToken());
            minTreeHeight = Math.min(minTreeHeight, trees[idx]);
            maxTreeHeight = Math.max(maxTreeHeight, trees[idx]);
        }

    }
}
