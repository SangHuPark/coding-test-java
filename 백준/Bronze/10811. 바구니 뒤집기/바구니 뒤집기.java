import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int basketSize, switchCnt;
    static int[] baskets;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        init();

        map = new HashMap<>();
        for (int switchIdx = 0; switchIdx < switchCnt; switchIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int[] tmp = new int[basketSize + 1];
            int tmpIdx = start;
            for (int idx = end; idx >= start; idx--) {
                tmp[tmpIdx++] = baskets[idx];
            }

            for (int idx = start; idx <= end; idx++) {
                baskets[idx] = tmp[idx];
            }
        }

        for (int idx = 1; idx <= basketSize; idx++) {
            System.out.print(baskets[idx] + " ");
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        basketSize = Integer.parseInt(st.nextToken());
        switchCnt = Integer.parseInt(st.nextToken());

        baskets = new int[basketSize+1];
        for (int idx = 1; idx <= basketSize; idx++) {
            baskets[idx] = idx;
        }
    }
}