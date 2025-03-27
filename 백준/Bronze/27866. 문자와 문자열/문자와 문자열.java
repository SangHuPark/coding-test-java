import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        int at = Integer.parseInt(br.readLine().trim());

        System.out.println(input.charAt(at-1));
    }

    public static void init() throws IOException {

    }
}