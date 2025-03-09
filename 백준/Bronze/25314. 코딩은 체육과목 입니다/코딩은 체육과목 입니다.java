import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int byteN;

    public static void main(String[] args) throws IOException {
        byteN = Integer.parseInt(br.readLine().trim());

        int size = byteN / 4;
        for (int idx = 0; idx < size; idx++) {
            sb.append("long ");
        }
        sb.append("int");
        System.out.println(sb);
    }

}