import java.io.*;
import java.util.*;

/**
 * BOJ_1011_Fly me to the Alpha Centauri
 * @author tkdgn407
 *
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int x, y, d, k;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            st = new StringTokenizer(br.readLine().trim());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            d = y - x;
            k = (int) Math.sqrt(d);
            
            int answer = 0;
            if (d <= k * k) answer = (k << 1) - 1;
            else if (k * k < d && d <= k * (k + 1)) answer = k << 1;
            else if (d > k * (k + 1)) answer = (k << 1) + 1;
            
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}