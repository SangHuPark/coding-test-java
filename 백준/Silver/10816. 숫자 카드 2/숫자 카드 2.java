import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] arr;
    static int M;
    static int[] targets;

    public static int lowerBound(int key) {
        int left = 0;
        int right = N;
        int mid;

        while (left < right) {
            mid = left + ((right - left) >> 1);

            if (key <= arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int upperBound(int key) {
        int left = 0;
        int right = N;
        int mid;

        while (left < right) {
            mid = left + ((right - left) >> 1);

            if (key < arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void solution() {

        for (int key = 0; key < M; key++) {
            sb.append(upperBound(targets[key]) - lowerBound(targets[key])).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        solution();

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        targets = new int[M];
        for (int idx = 0; idx < M; idx++) {
            targets[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
    }
}