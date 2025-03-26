import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize, maxScore, minScore;
    static int[] nums;
    static int[][] maxDP, minDP;

    public static void main(String[] args) throws IOException {
        init();

        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < 3; col++) {
                nums[col] = Integer.parseInt(st.nextToken());
            }

            int prev = (row + 1) % 2;
            int cur = row % 2;

            // 시작, 중간, 끝 인덱스 처리
            maxDP[cur][0] = Math.max(maxDP[prev][0], maxDP[prev][1]) + nums[0];
            maxDP[cur][1] = Math.max(Math.max(maxDP[prev][0], maxDP[prev][1]), maxDP[prev][2]) + nums[1];
            maxDP[cur][2] = Math.max(maxDP[prev][1], maxDP[prev][2]) + nums[2];

            // 시작, 중간, 끝 인덱스 선처리
            minDP[cur][0] = Math.min(minDP[prev][0], minDP[prev][1]) + nums[0];
            minDP[cur][1] = Math.min(Math.min(minDP[prev][0], minDP[prev][1]), minDP[prev][2]) + nums[1];
            minDP[cur][2] = Math.min(minDP[prev][1], minDP[prev][2]) + nums[2];

        }

        int score = mapSize % 2 == 1 ? 0 : 1;
        maxScore = 0;
        minScore = Integer.MAX_VALUE;
        for (int col = 0; col < 3; col++) {
            maxScore = Math.max(maxScore, maxDP[score][col]);
            minScore = Math.min(minScore, minDP[score][col]);
        }

        System.out.println(maxScore + " " + minScore);
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        nums = new int[3];
        maxDP = new int[2][3];
        minDP = new int[2][3];
    }
}