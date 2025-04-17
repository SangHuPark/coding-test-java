import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        int end = input.length() - 1;
        for (int start = 0; start < input.length() >> 1; start++) {
            if (input.charAt(start) != input.charAt(end--)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
    
}