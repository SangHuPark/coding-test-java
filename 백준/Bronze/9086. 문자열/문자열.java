import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            String input = br.readLine().trim();

            System.out.println(input.charAt(0) + "" + input.charAt(input.length()-1));
        }
    }

    public static void init() throws IOException {

    }
}