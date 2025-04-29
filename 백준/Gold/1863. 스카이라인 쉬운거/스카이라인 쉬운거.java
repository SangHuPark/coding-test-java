import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int skyline, buildingCnt;
    static Stack<Integer> heights;

    public static void main(String[] args) throws IOException {
        skyline = Integer.parseInt(br.readLine().trim());

        heights = new Stack<>();
        buildingCnt = 0;
        for (int idx = 0; idx < skyline; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!heights.isEmpty() && heights.peek() > y) {
                heights.pop();
            }

            if (heights.isEmpty() || heights.peek() < y) {
                if (y != 0) {
                    heights.push(y);
                    buildingCnt++;
                }
            }
        }
        System.out.println(buildingCnt);

    }

}