import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 앱은 활성화하는데 m 바이트를 사용하며 비용 c 를 가진다.
 * - M 메모리 확보를 위해 비활성화하는데 c 의 합을 최소로 가진 경우
 *
 * 1. 활성화된 앱의 개수 N, 필요한 메모리 M 을 저장하는 appCount, needMemory
 * 2. 각 앱의 필요 메모리와 소요 비용을 저장하는 memoryList, priceList
 *  2-1. 비용의 총합을 저장하는 priceSum
 * 3. 앱을 반복하며 현재 앱을 선택한 경우와 그렇지 않은 경우의 메모리 최대값으로 dp 갱신
 *  3-1. 총합 비용만큼 반복하며 현재 앱의 비용과 비교
 *  3-2. 현재 갱신 비용이 현재 앱의 비용보다 크거나 같다면 선택 가능
 * 4. dp 를 돌며 필요 메모리를 충족한 경우 결과 출력
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int appCount, needMemory;
    public static int[] memoryList, priceList;
    public static int priceSum;
    public static int[][] dp;

    public static int minPrice;

    public static void main(String[] args) throws IOException {
        // 1. 활성화된 앱의 개수 N, 필요한 메모리 M 을 저장하는 appCount, needMemory
        st = new StringTokenizer(br.readLine().trim());
        appCount = Integer.parseInt(st.nextToken());
        needMemory = Integer.parseInt(st.nextToken());

        // 2. 각 앱의 필요 메모리와 소요 비용을 저장하는 memoryList, priceList
        memoryList = new int[appCount+1];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx < appCount+1; idx++) {
            memoryList[idx] = Integer.parseInt(st.nextToken());
        }

        priceList = new int[appCount+1];
        priceSum = 0;
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx < appCount+1; idx++) {
            priceList[idx] = Integer.parseInt(st.nextToken());
            // 2-1. 비용의 총합을 저장하는 priceSum
            priceSum += priceList[idx];
        }

        dp = new int[appCount+1][priceSum+1];
        for (int appIdx = 1; appIdx < appCount+1; appIdx++) {
            int curMemory = memoryList[appIdx];
            int curPrice = priceList[appIdx];

            for (int priceIdx = 0; priceIdx < priceSum+1; priceIdx++) {
                if(priceIdx >= curPrice) {
                    dp[appIdx][priceIdx] = Math.max(dp[appIdx-1][priceIdx], dp[appIdx-1][priceIdx - curPrice] + curMemory);
                } else {
                    dp[appIdx][priceIdx] = dp[appIdx-1][priceIdx];
                }
            }
        }

        for (int priceIdx = 0; priceIdx < priceSum+1; priceIdx++) {
            if (dp[appCount][priceIdx] >= needMemory) {
                System.out.println(priceIdx);
                break;
            }
        }
    }

}