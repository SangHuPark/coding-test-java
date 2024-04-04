import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* 1. 지도의 정보와 경사로의 길이를 입력받는다.
* 2. 지도의 정보를 입력받는다.
*  2-1. 행, 열 별로 저장한다.
* 3. 각 행, 열을 검사한다.
*  3-1. 높이가 동일한 순간을 카운팅한다.
*  3-2. 이전 인덱스에서 현재 인덱스의 높이가 높아졌다면
*   3-2-1. 이전까지 연속된 길이가 경사로의 길이만큼 있는지 검사한다.
*   3-2-2. 카운팅을 초기화한다.
*  3-3. 이전 인덱스에서 현재 인덱스의 높이가 낮아졌다면
*   3-3-1. 현재 인덱스부터 이후에 경사로의 길이가 남아있는지 검사한다.
*   3-3-2. 검사 중 높이가 변화가 일어난다면 설치가 불가능하다.
*  3-4. 검사를 통과한 횟수를 카운팅한다.
*/
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int mapSize, slideLeng;
    public static int[][] rowMap;
    public static int[][] colMap;

    public static int checkCount;

    public static boolean checkLoad(int[] curLoad) {
        for (int idx = 0; idx < mapSize; idx++) {
//            System.out.print(curLoad[idx] + " ");
        }
//        System.out.println();

        int sameCount = 1;

        for (int idx = 1; idx < mapSize; idx++) {
            // 높이가 같다면 연속된 길이를 카운팅 한다.
            if(curLoad[idx-1] == curLoad[idx]) {
                sameCount++;
            } // 3-2. 이전 인덱스에서 현재 인덱스의 높이가 높아졌다면
            else if (curLoad[idx-1] + 1 == curLoad[idx]) {
                // 3-2-1. 이전까지 연속된 길이가 경사로의 길이만큼 있는지 검사한다.
                if((sameCount-slideLeng) < 0) { // 경사로를 설치할 수 없는 길이라면 false
                    return false;
                }

                // 카운팅 초기화
                sameCount = 1;
            } // 3-3. 이전 인덱스에서 현재 인덱스의 높이가 낮아졌다면
            else if (curLoad[idx-1] - 1 == curLoad[idx]) {
                // 3-3-1. 현재 인덱스부터 이후에 경사로의 길이가 남아있는지 검사한다.
                for (int col = 1; col < slideLeng; col++) {
                    if(idx + col >= mapSize) { // 인덱스를 벗어난다면 false
                        return false;
                    }

                    // 3-3-2. 검사 중 높이가 변하면 설치가 불가능하다.
                    if(curLoad[idx] != curLoad[idx+col]) {
                        return false;
                    }
                }

                // 검사를 통과했다면
                sameCount = 0; // 카운팅을 초기화
                idx = idx + slideLeng - 1; // 인덱스를 다음으로 이동
            } // 높이가 2 이상의 변화가 있다면 경사로 설치 불가
            else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            // 행, 열 검사 후 결과 카운팅
            checkCount = 0;
            for (int idx = 0; idx < mapSize; idx++) {
                if(checkLoad(rowMap[idx])) checkCount++;
                if(checkLoad(colMap[idx])) checkCount++;
            }

            sb.append("#").append(tc).append(" ").append(checkCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        slideLeng = Integer.parseInt(st.nextToken());

        rowMap = new int[mapSize][mapSize];
        colMap = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                rowMap[row][col] = colMap[col][row] = Integer.parseInt(st.nextToken());
            }
        }
    }
}