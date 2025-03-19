import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int K;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine().trim());

        String binary = Integer.toBinaryString(K + 1).replace('0', '4').replace('1', '7');

        for (int idx = 1; idx < binary.length(); idx++)
            System.out.print(binary.charAt(idx));
    }
}