import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int chuCnt;
    static List<Integer> chuList;

    public static void main(String[] args) throws IOException {
        init();

        int sum = 0;
        for (int chu : chuList) {
            if (sum + 1 < chu)
                break;

            sum += chu;
        }

        System.out.println(sum+1);
    }

    public static void init() throws IOException {
        chuCnt = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        chuList = new ArrayList<>();
        for (int idx = 0; idx < chuCnt; idx++) {
            int input = Integer.parseInt(st.nextToken());
            chuList.add(input);
        }
        Collections.sort(chuList);
    }
}