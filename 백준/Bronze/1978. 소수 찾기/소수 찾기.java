import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;

    static int result;

    public static void prime(int num) {
        if (num == 1)
            return;
        
        for (int divider = 2; divider < num; divider++) {
            if (num % divider == 0)
                return;
        }

        result++;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(result);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        result = 0;
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(st.nextToken());
            prime(num);
        }
    }
}