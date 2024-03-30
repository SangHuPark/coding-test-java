import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 구슬을 쏠 수 있는 횟수 N, 벽돌들의 정보를 담은 맵의 크기 W, H 를 shootCnt, rowSize, colSize
 * 2. 벽돌의 정보를 저장하는 originBrickMap
 * 3. 3. 모든 열 colSize(elementCount)에서 shootCnt(selectCount) 까지 쏠 수 있는 중복순열 생성
 * 	3-1. 생성된 중복순열의 원소는 구슬을 쏠 열 번호
 * 	3-2. 구슬 쏘기가 진행되는 동안 업데이트된 벽돌 정보를 저장하는 curBrickMap
 * 	3-3. 첫번째 원소의 열부터 구슬 쏘기, BFS 시작
 * 4. curBrickMap에 숫자만큼 4방으로 깨지는 벽돌 자리를 저장하는 crushMap
 * 	4-1. 큐가 비면 한 번의 구슬을 쏜 것이므로 crushMap 에 체크된 위치의 curBrickMap 값을 총합
 * 	4-2. 모든 열을 돌며 공중에 있는 벽돌(벽돌 아래가 0) 인 벽돌을 아래로 옮기는 moveBrick 함수
 * 5. 아래 방향으로만 검사하며 다음 자리에 다른 벽돌이 나오면 그 전 위치로 벽돌 이동
 * 	4-3. curBrickMap 을 전달
 * 6. 2차원 배열을 복사하는 arrayCopy 함수
 * 7. 남은 벽돌의 개수 구하기
 * 	7-1. 입력 시 벽돌의 총합 저장
 * 	7-2. 가장 많은 벽돌을 깬 숫자 계산
 * 	7-3. 총합 벽돌에서 최대 벽돌 개수 빼기
 */
public class Solution {
    static class Brick {
        int row;
        int col;
        int range;

        public Brick(int row, int col, int range) {
            this.row = row;
            this.col = col;
            this.range = range;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int shootCnt, rowSize, colSize;
    public static int[][] originBrickMap;
    public static int minBrick;
    public static int[][] curBrickMap;

    // 순열을 만드는데 사용
    public static int[] selectList;
    public static Deque<Brick> brickQueue;

    public static int[] deltaRow = {1, 0, -1, 0};
    public static int[] deltaCol = {0, -1, 0, 1};

    public static boolean print = false;

    public static void ballPermu(int selectIdx) {
        // 기저 조건 -> 구슬을 쏘는 횟수만큼 쏠 열 번호를 모두 선택 시
        if(selectIdx == shootCnt) {
            // 3-2. 구슬 쏘기가 진행되는 동안 업데이트된 벽돌 정보를 저장하는 curBrickMap
            curBrickMap = new int[rowSize][colSize];
            // 기존의 맵 정보 복사
            arrayCopy();

            // 이번 구슬 쏘기의 점수 총합 저장
            int curBrick = 0;

//            selectList = new int[] {2, 2, 6};

            // 3-1. 생성된 순열의 원소는 구슬을 쏠 열 번호
            for(int idx = 0; idx < shootCnt; idx++) {
                int shootColNum = selectList[idx];

                // 구슬을 쏠 열에서 가장 위의 벽돌부터 BFS 로 벽돌 깨기 진행
                shootBFS(shootColNum);

                // 남은 벽돌 이동
                moveBrick();
            }

            print = true;

            // 남은 벽돌 계산
            curBrick += getTotalBrick();

            minBrick = Math.min(minBrick, curBrick);

            return;
        }

        // 순열에 들어갈 원소는 모든 열(0 ~ colSize-1)
        for(int element = 0; element < colSize; element++) {
            selectList[selectIdx] = element;
            ballPermu(selectIdx + 1);
        }
    }

    public static void shootBFS(int shootColNum) {
        // 해당 열에서 가장 위에 있는 벽돌 찾기
        int shootRowNum = 0;
        for(int row = 0; row < rowSize; row++) {
            if(curBrickMap[row][shootColNum] > 0) {
                shootRowNum = row;
                break;
            }
        }

        // 만약 해당 열에 벽돌이 없다면 종료
        if(curBrickMap[shootRowNum][shootColNum] == 0) {
            return;
        }

        brickQueue = new ArrayDeque<>();

        Brick startBrick = new Brick(shootRowNum, shootColNum, curBrickMap[shootRowNum][shootColNum]);
        brickQueue.add(startBrick);


        // 이동하려는 위치가 1보다 크면 다음 방문지
        while(!brickQueue.isEmpty()) {
            Brick curBrick = brickQueue.poll();
            int curRow = curBrick.row;
            int curCol = curBrick.col;
            int range = curBrick.range;
            curBrickMap[curRow][curCol] = 0;

            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curRow;
                int nextCol = curCol;

                // 현재 방향으로 범위까지 이동
                for (int rangeIdx = 1; rangeIdx < range; rangeIdx++) {
                    nextRow += deltaRow[deltaIdx];
                    nextCol += deltaCol[deltaIdx];

                    // 인덱스를 벗어났거나 방문한 곳이면 패스
                    if(isArrange(nextRow, nextCol)) {
                        break;
                    }

                    // 연쇄적으로 깨질 경우 범위가 1보다 커야 하므로
                    if(curBrickMap[nextRow][nextCol] > 1) {
                        brickQueue.add(new Brick(nextRow, nextCol, curBrickMap[nextRow][nextCol]));
                    }

                    curBrickMap[nextRow][nextCol] = 0;
                }

            }

        }

    }

    // 벽돌 점수 계산
    public static int getTotalBrick() {
        int brickSum = 0;

        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(curBrickMap[row][col] != 0) {
                    brickSum++;
                }
            }
        }

        return brickSum;
    }

    // curBrickMap 의 벽돌을 아래로 이동
    public static void moveBrick() {
        // 마지막 행부터 아래로 체크
        for(int col = 0; col < colSize; col++) {
            for(int row = rowSize-1; row >= 0; row--) {
                // 빈 공간일 때
                if(curBrickMap[row][col] == 0) {
                    // 옮겨야할 현재 값
                    int brickNum = curBrickMap[row][col];

                    int nextRow = row;

                    // 인덱스 끝까지 이동
                    while (0 <= nextRow) {

                        // 계속 위로 이동하여 처음에 마주치는 벽돌을 가져온다
                        if (curBrickMap[nextRow][col] > 0) {
                            curBrickMap[row][col] = curBrickMap[nextRow][col];
                            curBrickMap[nextRow][col] = 0;
                            break;
                        }

                        // 위로 이동
                        nextRow--;
                    }
                }
            }
        }

//        if (!print) {
//            for (int row = 0; row < rowSize; row++) {
//                for (int col = 0; col < colSize; col++) {
//                    System.out.print(curBrickMap[row][col] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------이동 후");
//        }
    }

    public static void arrayCopy() {
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                curBrickMap[row][col] = originBrickMap[row][col];
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            // 1. 구슬을 쏠 수 있는 횟수 N, 벽돌들의 정보를 담은 맵의 크기 W, H 를 shootCnt, rowSize, colSize
            st = new StringTokenizer(br.readLine().trim());
            shootCnt = Integer.parseInt(st.nextToken());
            colSize = Integer.parseInt(st.nextToken());
            rowSize = Integer.parseInt(st.nextToken());

            // 2. 벽돌의 정보를 저장하는 originBrickMap
            originBrickMap = new int[rowSize][colSize];
            for(int row = 0; row < rowSize; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int col = 0; col < colSize; col++) {
                    originBrickMap[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 선택한 원소를 담을 배열 세팅
            selectList = new int[shootCnt];

            // 남은 벽돌의 최소값 저장
            minBrick = Integer.MAX_VALUE;

            // 3. 모든 열 colSize(elementCount)에서 shootCnt(selectCount) 까지 쏠 수 있는 중복순열 생성
            ballPermu(0);

            sb.append("#").append(tc).append(" ").append(minBrick).append("\n");
        }

        System.out.println(sb);
    }

}