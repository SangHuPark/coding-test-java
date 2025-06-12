import java.io.*;
import java.util.*;

/**
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String N;

    public static void main(String[] args) throws IOException {
        N = br.readLine().trim();

        int nIdx = 0, idx = 1;
        for (idx = 1; ; idx++) {
            String tmp = String.valueOf(idx);
            for (int at = 0; at < tmp.length(); at++) {
                if (tmp.charAt(at) == N.charAt(nIdx))
                    nIdx++;

                if (nIdx == N.length()) {
                    System.out.println(idx);
                    return;
                }
            }
        }
    }
}