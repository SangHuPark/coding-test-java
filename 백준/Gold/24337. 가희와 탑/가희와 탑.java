import java.io.*;
import java.util.*;

/**
 * BOJ_24337_가희와탑
 * @author tkdgn407
 *
 * 1. 입력에 대해 N < A + B - 1면 -1 출력 후 종료
 * 2. 사전순 가장 앞선 조건 만족 배열 초기 구성
 *  2-1. 길이는 a+b-1
 * 3. 첫 번째 높이 삽입
 * 4. 두 번째부터 남은 빈 칸이 있다면
 *  4-1. '1'을 출력으로 삽입해 N 길이로 만들기
 *  4-2. 어느 위치던 1은 보이지 않거나 1개로 취급하기 때문에 바로 출력 가능
 *  4-3. 1을 넣기 때문에 사전순 최소도 유지
 * 5. 이후 설정한 높이들 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, A, B, C;
    static List<Integer> heights;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // 1. 입력에 대해 N < A + B - 1면 -1 출력 후 종료
        if (N < A + B - 1) {
            System.out.println(-1);
            return;
        }

        // 2. 사전순 가장 앞선 조건 만족 배열 초기 구성
        heights = new ArrayList<>();
        // A-1개 오름차순 높이 삽입
        for (int height = 1; height < A; height++) {
            heights.add(height);
        }
        heights.add(Math.max(A, B)); // 최고 높이 삽입
        // B-1개 내림차순 높이 삽입
        for (int height = B - 1; height >= 1; height--) {
            heights.add(height);
        }

        // 3. 첫 번째 높이 삽입
        sb.append(heights.get(0));

        // 4. 두 번째부터 남은 빈 칸이 있다면
        int remainCnt = N - heights.size(); // 설정하고 남은 자리 개수
        for (int idx = 0; idx < remainCnt; idx++) {
            sb.append(" ").append(1);
        }

        // 5. 이후 설정한 높이들 출력
        for (int idx = 1; idx < heights.size(); idx++) {
            sb.append(" ").append(heights.get(idx));
        }

        System.out.println(sb);
    }
}