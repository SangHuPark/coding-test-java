import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 1. 1일, 1달, 3달, 1년 이용권 금액을 입력받는다.
 * 2. 12개월 이용 계획을 입력받는다.
 *
 * [ makePlan() ]
 * 1. 1일, 1달, 3달 을 이용하는 경우를 재귀 호출한다.
 *  1-1. 3달은 10, 11, 12월만 이용 가능 하므로 현재 month 가 10 이하일 때만 가능하다.
 *  1-2. 1일은 계획이 1일 경우에만 다음 호출에 month + 1 을 한다.
 * 2. 각 이용권의 금액을 누적해 전달한다.
 * 3. 12 월에 도착하면 종료한다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static final int TICKET_COUNT = 4;
    public static final int MONTH = 12;

    public static int[] tickets;
    public static int[] plan;

    public static int minFee;

    public static void makePlan(int curMonth, int curTotalFee) {
        if(curMonth >= MONTH) {
            minFee = Math.min(minFee, curTotalFee);
            return;
        }

        // 만약 이용 계획이 없는 달이라면 다음 달로 이동
        if(plan[curMonth] == 0) {
            makePlan(curMonth + 1, curTotalFee);
        } else {
            // 1일 이용권 사용 경우
            makePlan(curMonth + 1, curTotalFee + (plan[curMonth] * tickets[0]));
            // 1달 이용권 사용 경우
            makePlan(curMonth + 1, curTotalFee + tickets[1]);
            // 3달 이용권 사용 경우
            makePlan(curMonth + 3, curTotalFee + tickets[2]);
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            // 1년 이용권 금액을 초기값으로 저장
            minFee = tickets[3];
            makePlan(0, 0);

            sb.append("#").append(tc).append(" ").append(minFee).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        tickets = new int[TICKET_COUNT];
        plan = new int[MONTH];

        st = new StringTokenizer(br.readLine().trim());
        for (int ticketIdx = 0; ticketIdx < TICKET_COUNT; ticketIdx++) {
            tickets[ticketIdx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int planIdx = 0; planIdx < MONTH; planIdx++) {
            plan[planIdx] = Integer.parseInt(st.nextToken());
        }
    }
}