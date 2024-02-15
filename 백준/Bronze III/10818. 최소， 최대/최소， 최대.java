import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5
 * 20 10 35 30 7
 */
public class Main {
    public static BufferedReader br;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        int max = -1000001;
        int min = 1000001;
        for(int idx = 0; idx < count; idx++) {
            int inputNum = Integer.parseInt(st.nextToken());
            if (max < inputNum) max = inputNum;
            if (min > inputNum) min = inputNum;
        }

        System.out.println(min + " " + max);
    }
}