import java.io.*;
import java.util.*;

/**
 * 1. 입력된 학생 번호 배열에서, 순서를 유지하면서 증가하는 가장 긴 부분 수열(LIS)을 찾는다.
 * 2. 이를 위해 `dp[i]`에 **i번째 학생이 마지막일 때 만들 수 있는 LIS 길이**를 저장한다.
 * 3. 각 i에 대해, 0부터 i-1까지 j를 돌면서 `arr[j] < arr[i]`면 `dp[i] = max(dp[i], dp[j] + 1)`을 수행한다.
 * 4. dp에서 가장 큰 값이 LIS의 길이이며, 이 숫자는 **제자리에 둘 수 있는 최대 학생 수**를 의미한다.
 * 5. 따라서 정답은 전체 학생 수 N에서 LIS 길이를 뺀 `N - LIS`이다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws IOException {
        init();

        st = new StringTokenizer(br.readLine().trim());
        int num = 0;
        for (int idx = 0; idx < 5; idx++) {
            int tmp = Integer.parseInt(st.nextToken());
            num += tmp * tmp;
        }
        System.out.println(num % 10);
    }

    public static void init() throws IOException {
    }
}