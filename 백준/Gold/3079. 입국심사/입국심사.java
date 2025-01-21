import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 심사대 N, 사람 M 입력
 * 2. 각 심사대의 심사 소요 시간 입력
 *  2-1. 배열에 저장하고 오름차순 정렬
 * 3. 총 심사 시간 totalTime 을 최소로 하는 것을 목적으로 이분 탐색
 *  3-1. start 는 min 심사 시간(1), end 는 max 심사 시간(마지막 인덱스 * 사람 수)
 *  3-2. totalTime 는 end 로 초기값 설정
 *  3-3. 현재 mid를 기준으로 심사
 *  3-4. 가능하다면 end 를 mid - 1
 *  3-5. 불가능하면 start 를 mid + 1
 * 4. getCount(int capacity) 에서 mid 로 심사 시 모두 심사할 수 있는지 판단
 *  4-1. capacity / 심사대별 소요 시간을 누적하여 카운트 반환
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static long totalTime;
    static int[] judgeTimeArr;

    public static long getCount(long capacity) {
        long judgeCnt = 0;

        for (int idx = 0; idx < N; idx++) {
            judgeCnt += (long) (capacity / judgeTimeArr[idx]);
        }

        return judgeCnt;
    }

    public static void binarySearch() {
        long start = judgeTimeArr[0], end = (long) M * judgeTimeArr[0];
        totalTime = end;

        while (start <= end) {
            long mid = start + ((end - start) >> 1);

            long cnt = getCount(mid);
            if (cnt >= M) {
                totalTime = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        System.out.println(totalTime);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        judgeTimeArr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            judgeTimeArr[idx] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(judgeTimeArr);
    }

}