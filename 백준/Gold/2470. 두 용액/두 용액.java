import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] nums;
    static int min, minLeftIdx, minRightIdx;

    public static void binarySearch(int selectedIdx) {
        int start = selectedIdx+1, end = N-1;

        while (start <= end) {
            int mid = start + ((end - start) >> 1);

            int sum = nums[selectedIdx] + nums[mid];
            int absSum = Math.abs(sum);
            if (absSum < min) {
                minLeftIdx = selectedIdx;
                minRightIdx = mid;
                min = absSum;
            }

            if (sum >= 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        min = Integer.MAX_VALUE;
        for (int selectedIdx = 0; selectedIdx < N; selectedIdx++) {
            binarySearch(selectedIdx);
        }

        System.out.println(nums[minLeftIdx] + " " + nums[minRightIdx]);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        nums = new int[N];
        for (int idx = 0; idx < N; idx++) {
            nums[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

    }

}