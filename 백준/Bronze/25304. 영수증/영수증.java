import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int target, priceCnt, sum;

    public static void main(String[] args) throws IOException {
        target = Integer.parseInt(br.readLine().trim());

        priceCnt = Integer.parseInt(br.readLine().trim());
        sum = 0;
        for (int idx = 0; idx < priceCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int price = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            sum += price * cnt;
        }
        System.out.println(target == sum ? "Yes" : "No");
    }


}