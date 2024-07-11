import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if (A == B && B == C) {
            System.out.println(10000 + A * 1000);
        } else if (A == B || B == C || C == A) {
            if (A == B || A == C)
                System.out.println(1000 + A * 100);
            else
                System.out.println(1000 + B * 100);
        } else if (A != B && C != B) {
            int max = Math.max(A, Math.max(B, C));
            System.out.println(max * 100);
        }
    }
}