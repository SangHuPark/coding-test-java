import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int A, B, C;

    public static void main(String[] args) throws IOException {
        while(true) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            int tmp = 0;
            if (A > C) {
                tmp = A;
                A = C;
                C = tmp;
            }
            else if (B > C) {
                tmp = B;
                B = C;
                C = tmp;
            }

            if (A == 0 && B == 0 && C == 0)
                break;

            A = A*A;
            B = B*B;
            C = C*C;

            if (A + B == C || (A == B && B == C))
                sb.append("right").append("\n");
            else
                sb.append("wrong").append("\n");
        }

        System.out.println(sb);
    }
}