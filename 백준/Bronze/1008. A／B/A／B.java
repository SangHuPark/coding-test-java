import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static double result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        result = 1.0 * A / B;
        System.out.println(result);
    }
}