import java.io.*;
import java.util.*;

/**
 * 1. bit를 1부터 시작하여, 각 자리의 반복 주기를 2씩 증가시킨다.
 * 2. 각 반복 구간에서 1이 등장하는 개수를 계산한다.
 * 3. target을 기준으로 주기 반복을 구하고, 남은 비트도 체크한다.
 * 4. 각 반복 구간에서 등장하는 1의 개수를 누적합에 더한다.
 * 5. 최종적으로 모든 비트 자리의 누적합을 반환한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long N, M;

    /**
     * 1. bit를 1부터 시작하여, 각 자리 수의 1이 등장하는 주기를 계산한다.
     * 2. bit가 1일 때, 2개의 묶음마다 1이 한 번씩 등장한다.
     * 3. bit가 2일 때, 4개의 묶음마다 1이 두 번씩 등장한다.
     * 4. 이런 식으로 bit를 1씩 왼쪽으로 shift하며 누적합을 계산한다.
     * 5. 최종적으로 모든 자리의 누적합을 반환한다.
     */
    public static long countOne(long target) {
        long oneCnt = 0;
        long bit = 1;

        while (bit <= target) {
            // 반복 주기(section) 계산
            // 1. (target + 1) / (bit * 2) → 이만큼씩 반복됨
            long section = (target + 1) / (bit << 1);

            // 2. 해당 bit가 켜진 개수 = section * bit
            oneCnt += section * bit;

            // 3. 남은 잔여 부분이 bit보다 크면 추가로 더함
            if ((target + 1) % (bit << 1) > bit) {
                oneCnt += (target + 1) % (bit << 1) - bit;
            }

            // 다음 비트로 넘어감
            bit <<= 1;
        }

        return oneCnt;
    }

    public static void main(String[] args) throws IOException {
        init();

//        int cnt = 0;
//        for (int idx = 1; idx <= 24; idx++) {
//            cnt += Integer.toBinaryString(idx).replace("0", "").length();
//        }
//        System.out.println(cnt);

        long answer = countOne(M) - countOne(N-1);
        System.out.println(answer);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
    }
}