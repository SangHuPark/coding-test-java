import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        Integer input1 = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());
        Integer input2 = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());

        if (input1 > input2) {
            System.out.println(input1);
        } else {
            System.out.println(input2);
        }
    }

    public static void init() throws IOException {

    }
}