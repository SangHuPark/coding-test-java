import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int ITEM_ATTRIBUTE = 2;

    static int itemCnt, limitWeight;
    static int[][] items;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        init();

        for (int item = 1; item <= itemCnt; item++) {
             for (int weight = 1; weight <= limitWeight; weight++) {
                // 현재 짐을 넣을 수 없는 경우
                if (items[item][0] > weight)
                    dp[item][weight] = dp[item-1][weight];
                // 있는 경우
                else
                    dp[item][weight] = Math.max(dp[item-1][weight], dp[item-1][weight - items[item][0]] + items[item][1]);

            }
        }

        System.out.println(dp[itemCnt][limitWeight]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        itemCnt = Integer.parseInt(st.nextToken());
        limitWeight = Integer.parseInt(st.nextToken());

        items = new int[itemCnt+1][ITEM_ATTRIBUTE];
        for (int idx = 1; idx <= itemCnt; idx++) {
            st = new StringTokenizer(br.readLine());
            items[idx][0] = Integer.parseInt(st.nextToken()); // 무게
            items[idx][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // 무게 기준으로 오름차순 정렬
//        Arrays.sort(items, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o1[0], o2[0]);
//            }
//        });

//        for (int idx = 0; idx < itemCnt; idx++) {
//            System.out.println(items[idx][0] + " " + items[idx][1]);
//        }

        dp = new int[itemCnt+1][limitWeight+1];
    }
}