import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        int answer = 0;
        String input = br.readLine().trim();
        for (int idx = 0; idx < input.length(); idx++) {
            answer += input.charAt(idx) - '0';
        }
        System.out.println(answer);
    }
}