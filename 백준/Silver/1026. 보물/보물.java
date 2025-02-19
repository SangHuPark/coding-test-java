import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numCnt;
    static List<Integer> aList;
    static List<Integer> bList;

    public static void main(String[] args) throws IOException {
        init();

        int total = 0;
        for (int idx = 0; idx < numCnt; idx++) {
            int a = aList.get(idx);
            int b = bList.get(idx);

            total += a * b;
        }

        System.out.println(total);

    }

    public static void init() throws IOException {
        numCnt = Integer.parseInt(br.readLine().trim());

        aList = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numCnt; idx++) {
            aList.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(aList);

        bList = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numCnt; idx++) {
            bList.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(bList, Collections.reverseOrder());

    }
}