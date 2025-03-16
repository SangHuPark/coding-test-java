import sun.util.locale.StringTokenIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int cupCnt, inputCnt;
    static int[] cupList;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        cupCnt = Integer.parseInt(st.nextToken());
        inputCnt = Integer.parseInt(st.nextToken());

        cupList = new int[cupCnt + 1];
        for (int idx = 0; idx < inputCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            for (int cup = start; cup <= end; cup++)
                cupList[cup] = num;
        }

        for (int idx = 1; idx <= cupCnt; idx++) {
            sb.append(cupList[idx] + " ");
        }
        System.out.println(sb);
    }


}