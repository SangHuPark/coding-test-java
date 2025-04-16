import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        init();

        int answer = 1_000_000;
        int start = 0, end = 0, length = 1;
        long sum = nums[0];
        while (end < N && start <= end) {
            if (sum >= S) {
                answer = Math.min(answer, length);
                sum -= nums[start++];
                length--;
            } else {
                end++;
                if (end < N)
                    sum += nums[end];
                length++;
            }
        }
        if (answer == 1_000_000)
            answer = 0;

        System.out.println(answer);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        nums = new int[N];
        for (int idx = 0; idx < N; idx++) {
            nums[idx] = Integer.parseInt(st.nextToken());
        }
    }
}