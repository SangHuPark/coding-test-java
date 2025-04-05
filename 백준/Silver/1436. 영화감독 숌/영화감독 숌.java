import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());

        int cnt = 0;
        int num = 666;
        while (true) {
            if (String.valueOf(num).contains("666"))
                cnt++;

            if (cnt == n) break;

            num++;
        }

        System.out.println(num);
    }
}