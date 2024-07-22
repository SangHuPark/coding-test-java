import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int T, P;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        StringTokenizer st2 = new StringTokenizer(br.readLine().trim());
        T = Integer.parseInt(st2.nextToken());
        P = Integer.parseInt(st2.nextToken());
        int countT = 0;

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (num <= T && num > 0)
                countT++;
            else {
                countT += num / T;
                if (num % T > 0)
                    countT++;
            }
        }

        sb.append(countT).append("\n");
        sb.append(N / P).append(" ").append(N % P);
        System.out.println(sb);
    }

}