import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            st = new StringTokenizer(br.readLine().trim());
            int loop = Integer.parseInt(st.nextToken());
            char[] inputArr = st.nextToken().toCharArray();

            for (char input : inputArr) {
                for (int idx = 0; idx < loop; idx++) {
                    sb.append(input);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws IOException {

    }
}