import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * - 불가능한 경우: 5판을 안했거나, 승 패 총합 (30 - 무)경기가 안되거나, 각 승 패 합이 같지 않거나
 *
 * 1. 각 국가의 승,무,패 정보를 저장하는 WorldCup 클래스
 * 2. 6개국의 경기 스케줄 생성
 *  2-1. 경기 수는 6C2 로 15
 * 3. 불가능한 경우 검사
 *  3-1. 5판을 안한 경우
 *  3-2. 승 패 총합이 (30 - 무 총합) 과 다른 경우
 *  3-3. 각 승 패 합이 같지 않은 경우
 * 4. 스케줄에 따라 나라별 경기 진행
 */
public class Main {
    static class WorldCup {
        int win;
        int draw;
        int lose;

        public WorldCup(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static final int TEST_CASE = 4; // 총 테스트 케이스는 4 고정
    public static final int WORLD_COUNT = 6; // 참여한 나라 수
    public static final int MATCH_COUNT = 15; // 총 경기 수

    // 경기 스케줄 저장
    public static List<int[]> scheduleList = new ArrayList<>();

    // 각 나라의 승, 무, 패 결과
    public static WorldCup[] matchResult = new WorldCup[WORLD_COUNT];

    // 경우의 수에 따라 경기 진행 후 승, 무, 패 결과
    public static WorldCup[] curMatch = new WorldCup[WORLD_COUNT];

    public static boolean answer = false;

    /* 대진표 생성
    A - B C D E F
    B - C D E F
    C - D E F
    D - E F
    E - F
     */
    public static void makeSchedule() {
        for(int curTeamIdx = 0; curTeamIdx < WORLD_COUNT; curTeamIdx++) {
            for(int matchTeamIdx = curTeamIdx + 1; matchTeamIdx < WORLD_COUNT; matchTeamIdx++) {
                scheduleList.add(new int[] {curTeamIdx, matchTeamIdx});
            }
        }
    }

    public static void matchRecursive(int curMatchCount) {
        if(answer) {
            return;
        }

        if(curMatchCount == MATCH_COUNT) {
            if(isMatch()) {
                answer = true;
            }
            return;
        }

        int curWorld = scheduleList.get(curMatchCount)[0]; // 첫 번째 나라부터 대진 진행
        int targetWorld = scheduleList.get(curMatchCount)[1]; // 현재 나라의 상대 나라

        // 현재 나라가 이길 경우
        curMatch[curWorld].win++;
        curMatch[targetWorld].lose++;
        if(!answer) {
            matchRecursive(curMatchCount + 1);
        }
        // 이긴 경우의 15 경기가 끝나면 돌려준다.
        curMatch[curWorld].win--;
        curMatch[targetWorld].lose--;

        // 현재 나라가 비길 경우
        curMatch[curWorld].draw++;
        curMatch[targetWorld].draw++;
        if(!answer) {
            matchRecursive(curMatchCount + 1);
        }
        // 비긴 경우의 15 경기가 끝나면 돌려준다.
        curMatch[curWorld].draw--;
        curMatch[targetWorld].draw--;

        // 현재 나라가 진 경우
        curMatch[curWorld].lose++;
        curMatch[targetWorld].win++;
        if(!answer) {
            matchRecursive(curMatchCount + 1);
        }
        // 진 경우의 15 경기가 끝나면 돌려준다.
        curMatch[curWorld].lose--;
        curMatch[targetWorld].win--;
    }

    // 유효한 대진인지 검사
    public static boolean isMatch() {
        for(int worldIdx = 0; worldIdx < WORLD_COUNT; worldIdx++) {
            if(matchResult[worldIdx].win != curMatch[worldIdx].win) {
                return false;
            }

            if(matchResult[worldIdx].draw != curMatch[worldIdx].draw) {
                return false;
            }

            if(matchResult[worldIdx].lose != curMatch[worldIdx].lose) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // 2. 6개국의 경기 스케줄 생성
        makeSchedule();

        for(int tc = 1; tc <= TEST_CASE; tc++) {

            st = new StringTokenizer(br.readLine().trim());
            for(int worldIdx = 0; worldIdx < WORLD_COUNT; worldIdx++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                matchResult[worldIdx] = new WorldCup(win, draw, lose);
            }

            for(int matchIdx = 0; matchIdx < WORLD_COUNT; matchIdx++) {
                curMatch[matchIdx] = new WorldCup(0, 0, 0); // 진행할 대진표의 승, 무, 패를 0으로 초기화
            }

            answer = false;

            matchRecursive(0);

            if (answer) {
                sb.append(1).append(" ");
                continue;
            }

            sb.append(0).append(" ");
        }

        System.out.println(sb);
    }
}