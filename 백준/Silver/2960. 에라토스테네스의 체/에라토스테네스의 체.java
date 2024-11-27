import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static boolean[] primeCheck;
    static int removeCount;

    public static void main(String[] args) throws IOException {
        init();

        removeCount = 0;
        for (int prime = 2; prime <= N; prime++) {

            for (int multiple = prime; multiple <= N; multiple+=prime) {
                if (primeCheck[multiple])
                    continue;

                primeCheck[multiple] = true;
                removeCount++;
                if (removeCount == K) {
                    System.out.println(multiple);
                    return;
                }
            }
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        primeCheck = new boolean[N + 1];
    }

}