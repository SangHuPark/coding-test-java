import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        String input;
        while ((input = br.readLine()) != null) {
            sb.append(input).append("\n");
        }

        System.out.print(sb);
    }

    public static void init() throws IOException {

    }
}