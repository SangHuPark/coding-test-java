import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static BigInteger A, B;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        A = new BigInteger(st.nextToken());
        B = new BigInteger(st.nextToken());
        System.out.println(A.add(B));
    }
}