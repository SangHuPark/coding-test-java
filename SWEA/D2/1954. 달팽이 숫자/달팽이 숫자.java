import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 테스트 케이스를 저장하는 testCase 선언
 * 2. 달팽이의 크기를 저장하는 snailSize 선언
 * 3. 숫자를 저장하는 snailNumArray 선언
 * 4. 출발은 오른쪽 방향부터 진행하며 숫자 저장
 * 	4-1. 배열을 벗어나는 범위거나, 다음 인덱스에 값이 있다면 방향 전환(현재 방향 +1 의 4로 나눈 나머지)
 */
public class Solution {
    public static BufferedReader br;
    public static StringBuilder sb;

    public static int testCase;
    public static int snailSize;
    public static int[][] snailNumArray;

    // 달팽이 모양의 방향으로 오른쪽부터
    public static int[] deltaRow = {0, 1, 0, -1};
    public static int[] deltaCol = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 테스트 케이스를 저장하는 testCase 선언
        testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append("\n");
            // 2. 달팽이의 크기를 저장하는 snailSize 선언
            snailSize = Integer.parseInt(br.readLine().trim());
            // 3. 숫자를 저장하는 snailNumArray 선언
            snailNumArray = new int[snailSize][snailSize];

            int deltaIdx = 0; // 현재 진행할 방향
            int snail = 1; // 배열에 들어갈 달팽이 숫자
            // 현재 좌표
            int nowRow = 0;
            int nowCol = 0;
            // 이동할 곳의 좌표
            int newRow, newCol;

            // 4. 출발은 오른쪽 방향부터 진행하며 숫자 저장
            while(snail <= snailSize*snailSize) {
                snailNumArray[nowRow][nowCol] = snail++;
                newRow = nowRow + deltaRow[deltaIdx];
                newCol = nowCol + deltaCol[deltaIdx];
                // 4-1. 배열을 벗어나는 범위거나, 다음 인덱스에 값이 있다면 방향 전환(현재 방향 +1 의 4로 나눈 나머지)
                if(isArrange(newRow, newCol) || snailNumArray[newRow][newCol] != 0) {
                    deltaIdx = (deltaIdx + 1) % 4;
                }
                nowRow += deltaRow[deltaIdx];
                nowCol += deltaCol[deltaIdx];
            }

            for(int row = 0; row < snailSize; row++) {
                for(int col = 0; col < snailSize; col++) {
                    sb.append(snailNumArray[row][col]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    // 이동할 인덱스가 배열의 범위 밖인지 검사
    public static boolean isArrange(int newRow, int newCol) {
        return newRow < 0 || newCol < 0 || newRow >= snailSize || newCol >= snailSize;
    }
}