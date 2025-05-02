import java.io.*;
import java.util.*;

/**
 * 1. 단어들을 입력받아 배열에 저장하고, 원래 입력 순서도 함께 기록한다.
 * 2. 단어들을 사전순으로 정렬하면, 공통 접두사가 긴 단어쌍은 인접하게 위치하게 된다.
 * 3. 정렬된 배열에서 인접한 단어 쌍들 간 공통 접두사 길이를 구하며 최댓값을 갱신한다.
 * 4. 공통 접두사 길이가 최대인 쌍이 여러 개라면, 원래 입력 순서를 기준으로 가장 앞선 쌍을 선택한다.
 * 5. 최종적으로 조건을 만족하는 두 단어를 입력 순서대로 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[] sorted;

    public static void main(String[] args) throws IOException {
        init();

        int maxLen = -1;
        String a = "", b = "";
        for (int curIdx = 0; curIdx < N - 1; curIdx++) {
            String cur = sorted[curIdx];
            for (int nextIdx = 1; nextIdx < N; nextIdx++) {
                if (curIdx == nextIdx)
                    continue;

                String next = sorted[nextIdx];
                int len = 0;
                for (int ch = 0; ch < Math.min(cur.length(), next.length()); ch++) {
                    if (cur.charAt(ch) != next.charAt(ch))
                        break;

                    len++;
                }

                if (maxLen < len) {
                    maxLen = len;
                    a = cur;
                    b = next;
                }
            }
        }
        System.out.println(a + "\n" + b);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        // 1. 단어들을 입력받아 배열에 저장하고, 원래 입력 순서도 함께 기록한다.
        sorted = new String[N];
        for (int idx = 0; idx < N; idx++) {
            sorted[idx] = br.readLine().trim();
        }
    }

}