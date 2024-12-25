import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();

        Arrays.sort(arr);
        for (int idx = 0; idx < N; idx++) {
            sb.append(arr[idx]).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        arr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(br.readLine().trim());
        }
    }
}
