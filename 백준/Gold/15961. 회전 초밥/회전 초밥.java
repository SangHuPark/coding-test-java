import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * - 접시의 수 N, 2 <= N <= 3,000,000
 * - 초밥의 가짓수 d, 2 <= d <= 3,000
 * - 연속해서 먹는 접시의 수 k, 2 <= k <= 3,000 (k <= N)
 * - 쿠폰 번호 c, 1 <= c <= d
 *
 * 1. 접시, 초밥의 가짓수, 연속해서 먹는 접시, 쿠폰 번호를 저장하는 plateCount, sushiCount, eatSushiCount, couponNum
 * 2. 벨트에 놓인 초밥을 저장하는 sushiList
 * 3. 초밥 조합을 저장하는 selectSushiList
 * 	3-1. 초밥의 가짓수+1의 길이
 * 4. 초밥의 최대 개수를 저장하는 maxSushiCount
 * 5. 최초의 초밥 조합을 저장
 * 6. 만들어진 조합에서 초밥의 개수를 계산
 * 	6-1. 중복된 초밥은 하나만 카운팅
 * 	6-2. 쿠폰을 추가할 수 있는지 검사 후 추가할 수 있다면 개수 +1
 * 7. 벨트가 돌며 마지막 초밥 + (eatSushiCount-1) 이 나올때까지 새로운 조합 생성
 * 	7-1. 현재 조합에서 첫번째 초밥 제외 후 다음 초밥 추가
 * 	7-2. 6. 수행
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int plateCount, sushiCount, eatSushiCount, couponNum;

    public static int[] sushiList;
    public static int[] selectSushiList;

    public static int nowSushiCount;
    public static int maxSushiCount;

    public static void main(String[] args) throws IOException {
        // 1. 접시, 초밥의 가짓수, 연속해서 먹는 접시, 쿠폰 번호를 저장하는 plateCount, sushiCount, eatSushiCount, couponNum
        st = new StringTokenizer(br.readLine().trim());
        plateCount = Integer.parseInt(st.nextToken());
        sushiCount = Integer.parseInt(st.nextToken());
        eatSushiCount = Integer.parseInt(st.nextToken());
        couponNum = Integer.parseInt(st.nextToken());

        // 2. 벨트에 놓인 초밥을 저장하는 sushiList
        sushiList = new int[plateCount];

        // 3. 초밥 조합을 저장하는 selectSushiList
        selectSushiList = new int[sushiCount + 1];

        // sushiList 에 원소 저장
        for(int idx = 0; idx < plateCount; idx++) {
            sushiList[idx] = Integer.parseInt(br.readLine().trim());
        }

        // 4. 초밥의 최대 개수를 저장하는 maxSushiCount
        maxSushiCount = 0;

        // 쿠폰 초밥 추가
        // 연속으로 먹을 경우 무조건 나오므로 처음부터 조합에 추가하여 시작
        selectSushiList[couponNum]++;
        nowSushiCount = 1;

        // 5. 최초의 초밥 조합을 저장
        for(int idx = 0; idx < eatSushiCount; idx++) {
            selectSushiList[sushiList[idx]]++;
            // 새로운 종류의 초밥이라면 가짓수 +1
            if (selectSushiList[sushiList[idx]] == 1) {
                nowSushiCount++;
            }
        }

        // 최대값 갱신
        if(maxSushiCount < nowSushiCount) {
            maxSushiCount = nowSushiCount;
        }

        // 7. 벨트가 돌며 마지막 초밥 ~ eatSushiCount-1 이 나올때까지 새로운 조합 생성
        for(int idx = eatSushiCount; idx < plateCount + eatSushiCount; idx++) {
            // 7-1. 현재 조합에서 첫번째 초밥 제외 후 다음 초밥 추가

            // 현재 조합의 첫번째를 가르킨다.
            int firstSushi = idx - eatSushiCount;
            selectSushiList[sushiList[firstSushi]]--;

            // 현재 삭제한 초밥이 조합에서 유일했다면 가짓수 -1
            if(selectSushiList[sushiList[firstSushi]] == 0) {
                nowSushiCount--;
            }

            // 마지막 인덱스 저장
            int lastSushi = idx % plateCount;

            selectSushiList[sushiList[lastSushi]]++;

            // 마지막으로 추가한 초밥이 새로운 종류의 초밥이면 가짓수 +1
            if(selectSushiList[sushiList[lastSushi]] == 1) {
                nowSushiCount++;
            }

            // 최대값 갱신
            if(maxSushiCount < nowSushiCount) {
                maxSushiCount = nowSushiCount;
            }
        }

        System.out.println(maxSushiCount);
    }
}