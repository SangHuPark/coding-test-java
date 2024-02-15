import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        int[] inputNum = new int[count];
        for(int idx = 0; idx < count; idx++) {
            inputNum[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputNum);

        System.out.println(inputNum[0] + " " + inputNum[count-1]);
    }
}