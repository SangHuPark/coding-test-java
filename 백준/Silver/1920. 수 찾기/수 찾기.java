import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. N 입력, N 개의 정수 배열 answerArr에 저장
 * 2. N 개의 정수 배열 오름차순 정렬
 * 3. M 입력, M 개의 정수를 입력받으며 이분탐색
 *
 * [ binarySearch(left, right, target) ]
 * 4. 임의의 mid, left + right / 2 설정
 * 5. answerArr[mid] 가 현재 탐색 중인 수와 같다면 1 출력 후 종료
 *  5-1. answerArr[mid] 가 더 작다면 left 를 mid + 1 로 재귀 호출
 *  5-2. answerArr[mid] 가 더 크다면 right 를 mid - 1 로 재귀 호출
 * 6. left > right 일 때 종료
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] answerArr;

    public static void binarySearch(int left, int right, int target) {
        if (left > right) {
            sb.append(0).append("\n");
            return;
        }

        int mid = (left + right) >> 1;

        if (answerArr[mid] < target)
            binarySearch(mid + 1, right, target);
        else if (answerArr[mid] > target)
            binarySearch(left, mid - 1, target);
        else
            sb.append(1).append("\n");
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        answerArr = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            answerArr[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(answerArr);

        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < M; idx++) {
            binarySearch(0, answerArr.length-1, Integer.parseInt(st.nextToken()));
        }
    }
}