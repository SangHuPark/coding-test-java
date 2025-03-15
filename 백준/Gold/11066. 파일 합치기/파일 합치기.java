import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int fileCnt;
    static int[] fileList;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            fileCnt = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim());
            fileList = new int[fileCnt + 1];
            for (int idx = 1; idx <= fileCnt; idx++) {
                // 파일은 서로 인접한 것끼리만 합쳐질 수 있으므로 미리 누적합 계산
                fileList[idx] = Integer.parseInt(st.nextToken()) + fileList[idx-1];
            }

            dp = new int[fileCnt + 1][fileCnt + 1];
            // round: 몇 개의 구간을 잘라 파일을 합칠지
            for (int round = 1; round < fileCnt; round++) {
                for (int start = 1; start+round <= fileCnt; start++) {
                    int end = start + round; // 현재 시작에서 자를 범위까지 이동
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + (fileList[end] - fileList[start - 1]));
                    }
                }
            }
            System.out.println(dp[1][fileCnt]);
        }
    }


}