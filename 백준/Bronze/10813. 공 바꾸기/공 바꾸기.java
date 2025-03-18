import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for (int idx = 1; idx <= N; idx++) {
            arr[idx] = idx;
        }
        
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
        }

        for (int idx = 1; idx <= N; idx++) {
            System.out.print(arr[idx] + " ");
        }
    }
}