import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * - 각 이닝에는 아웃을 기록하는 타자가 최소 한 명 존재
 * - 입력 데이터: 1번 ~ 9번 선수가 각 이닝에서 얻은 결과
 * - 0 이 세 번 들어오면 이닝 하나가 종료
 * - 1번 타자는 4번 타자 고정
 * - 0: 아웃, 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런
 *
 * 1. 이닝 수 N 을 저장하는 inningCount
 * 2. 각 선수의 이닝 결과를 저장하는 hitterList
 * 3. 1번 선수를 4번 타자로 고정
 * 4. 나머지 8명 선수로 타순 순열을 만들어 득점 최대값 찾기
 * 	4-1. 이미 0의 개수가 3의 배수로 inningCount 만큼 나오면 더 만들 필요 없음
 * 	4-2. 만들어진 순열로 야구를 진행하는 play 메서드
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int inningCount;
    public static int[][] hitterList;

    public static final int PLAYER_COUNT = 9;
    public static final int BASE_COUNT = 4;

    public static final int ELEMENT_COUNT = 8;
    public static final int SELECT_COUNT = 8;

    public static final int[] ELEMENT_LIST = {0, 1, 2, 4, 5, 6, 7, 8};
    public static int[] selectList;
    public static boolean[] usedCheck;

    public static List<Integer> resultHitterList;

    public static boolean[] baseInfoList;

    public static int curScore;

    public static int maxScore;

    public static void hitterPermu(int selectIdx) {
        if(selectIdx == SELECT_COUNT) {
            resultHitterList = new ArrayList<>();
			for(int idx = 0; idx < SELECT_COUNT; idx++) {
                resultHitterList.add(selectList[idx]);
			}

            resultHitterList.add(3, 3);

            // 현재 출루 정보를 저장.
            baseInfoList = new boolean[BASE_COUNT-1];
            // 해당 타순 득점
            curScore = 0;

            // 경기 진행
            play();

            // 최대 점수 기록
            maxScore = Integer.max(maxScore, curScore);

            return;
        }

        for(int elementIdx = 0; elementIdx < ELEMENT_COUNT; elementIdx++) {
            if(usedCheck[elementIdx]) {
                continue;
            }

            selectList[selectIdx] = ELEMENT_LIST[elementIdx];
            usedCheck[elementIdx] = true;

            hitterPermu(selectIdx + 1);

            usedCheck[elementIdx] = false;
        }
    }

    public static void play() {
        int outCount = 0;
        int hitterIdx = 0;
        int batIdx = 0;

        for(int inning = 0; inning < inningCount; inning++) {
            // base 정보 초기화
            for(int idx = 0; idx < BASE_COUNT-1; idx++) {
                baseInfoList[idx] = false;
            }
            // outCount 초기화
            outCount = 0;

            while(outCount < 3) {
                int nowHitter = hitterList[resultHitterList.get(hitterIdx)][inning];

                switch(nowHitter) {
                    case 0: outCount++; break;
                    case 1: oneHit(); break;
                    case 2: twoHit(); break;
                    case 3: threeHit(); break;
                    case 4: homeRun(); break;
                }

                hitterIdx = (hitterIdx+1) % 9;

                if(hitterIdx == 0) {
                    batIdx = (batIdx + 1) % inningCount;
                }
            }
        }

    }

    public static void oneHit() {
        if(baseInfoList[2]) {
            curScore++;
            baseInfoList[2] = false;
        }

        if(baseInfoList[1]) {
            baseInfoList[1] = false;
            baseInfoList[2] = true;
        }

        if(baseInfoList[0]) {
            baseInfoList[0] = false;
            baseInfoList[1] = true;
        }

        baseInfoList[0] = true;
    }

    public static void twoHit() {
        if(baseInfoList[2]) {
            curScore++;
            baseInfoList[2] = false;
        }

        if(baseInfoList[1]) {
            curScore++;
            baseInfoList[1] = false;
        }

        if(baseInfoList[0]) {
            baseInfoList[0] = false;
            baseInfoList[2] = true;
        }

        baseInfoList[1] = true;
    }

    public static void threeHit() {
        if(baseInfoList[2]) {
            curScore++;
            baseInfoList[2] = false;
        }

        if(baseInfoList[1]) {
            curScore++;
            baseInfoList[1] = false;
        }

        if(baseInfoList[0]) {
            curScore++;
            baseInfoList[0] = false;
        }

        baseInfoList[2] = true;
    }

    public static void homeRun() {
        // 주자 득점
        for(int idx = 0; idx < BASE_COUNT - 1; idx++) {
            if(baseInfoList[idx]) {
                curScore++;
                baseInfoList[idx] = false;
            }
        }

        // 타자 득점
        curScore++;
    }

    public static void main(String[] args) throws IOException {
        // 1. 이닝 수 N 을 저장하는 inningCount
        inningCount = Integer.parseInt(br.readLine().trim());

        // 2. 각 선수의 이닝 결과를 저장하는 hitterList
        hitterList = new int[PLAYER_COUNT][inningCount];
        for(int col = 0; col < inningCount; col++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int row = 0; row < PLAYER_COUNT; row++) {
                hitterList[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. 1번 선수를 4번 타자로 고정
        for(int inningIdx = 0; inningIdx < inningCount; inningIdx++) {
            int temp = hitterList[0][inningIdx];
            hitterList[0][inningIdx] = hitterList[3][inningIdx];
            hitterList[3][inningIdx] = temp;
        }

        selectList = new int[SELECT_COUNT];
        usedCheck = new boolean[ELEMENT_COUNT];
        maxScore = Integer.MIN_VALUE;
        hitterPermu(0);

        System.out.println(maxScore);
    }

}