import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 질투심은 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수이므로 각 학생에게 보석을 질투심 이하로 나누어 주어야 한다.
 * 2. 이를 이용해서 Left는 1, Right는 가장 많은 보석의 개수로 두고 이분 탐색을 한다.
 * 3. 중간값으로 보석을 나눠준다고 가정할 때 보석을 받아야 하는 학생의 수가 실제 학생의 수보다 작아야 보석을 나눠줄 수 있다.
 * 4. 보석을 나눠줄 수 있는 경우 중 최솟값을 찾는다.
 */

/**
 * 이분 탐색 설정:
 * 질투심의 최솟값을 찾기 위해 각 학생이 가져갈 수 있는 보석의 최대 개수를 기준으로 이분 탐색을 합니다.
 * low는 최소 질투심(1)이고, high는 가장 많은 보석의 수(max(보석 개수들))입니다.
 *
 * 보석 분배가 가능한지 확인:
 * 각 보석의 색상마다 그 보석을 나누어 줄 학생 수를 계산합니다. 이때, 주어진 최대 질투심 값(mid)를 기준으로 한 학생이 가져갈 수 있는 보석의 개수를 제한합니다.
 * 각 색상별로 필요한 학생 수를 구하고, 그 합이 N명 이하라면 해당 mid 값으로 보석을 나누어 줄 수 있는 것입니다.
 *
 * 이분 탐색 수행:
 * 가능한 최대 질투심을 줄이기 위해 이분 탐색으로 mid 값을 줄여 나가면서 가능한 최소 질투심을 찾습니다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] colorList;
    static int left, right, mid, sum, answer;

    public static void solution() {

        while (left <= right) {
            mid = (left + right) >> 1;
            sum = 0;

            // 각 보석의 색상마다 그 보석을 나누어 줄 학생 수를 계산합니다. 이때, 주어진 최대 질투심 값(mid)를 기준으로 한 학생이 가져갈 수 있는 보석의 개수를 제한합니다.
            for (int idx = 0; idx < M; idx++) {
                sum += colorList[idx] / mid;

                if (colorList[idx] % mid != 0) {
                    sum++;
                }
            }

            if (sum > N) {
                left = mid + 1;
            } // 각 색상별로 필요한 학생 수를 구하고, 그 합이 N명 이하라면 해당 mid 값으로 보석을 나누어 줄 수 있는 것입니다.
            else {
                right = mid - 1;
                answer = mid;
            }

        }

    }

    public static void main(String[] args) throws IOException {

        init();

        solution();

        System.out.println(answer);

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        left = 1; right = 0; mid = 0; sum = 0; answer = 0;

        colorList = new int[M];
        for (int idx = 0; idx < M; idx++) {
            colorList[idx] = Integer.parseInt(br.readLine());
            right = Math.max(right, colorList[idx]);
        }

    }
}