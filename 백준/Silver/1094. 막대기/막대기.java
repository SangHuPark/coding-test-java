import java.io.*;
import java.util.*;

/**
 * 1. bit는 64 시작으로 N과 같아질 때 까지 진행한다.
 * 2. bit를 2로 나눈 값이 N보다 크다면 나눈 값만큼 버린다.
 * 3. N보다 크지 않다면 카운팅 및 누적합한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    public static int countOne() {
        int cnt = 0;
        int bit = 1;

        while (bit <= N) {
            if ((bit & N) != 0) {
                cnt++;
            }

            bit <<= 1;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(countOne());
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
    }
}