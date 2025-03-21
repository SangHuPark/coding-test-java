import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] arr;

    public static void main(String[] args) throws IOException {
        arr = new int[31];

        for (int idx = 0; idx  < 28; idx++) {
            int num = Integer.parseInt(br.readLine().trim());

            arr[num] = 1;
        }

        for (int idx = 1; idx  <= 30; idx++) {
            if (arr[idx] == 0)
                System.out.println(idx);

        }
    }
}