import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 일 ~ 월요일의 문자열을 저장하는 dayList
 * 2. 입력으로 들어온 문자열 중 dayList 에 해당하는 인덱스를 구한 후 7에서 인덱스를 빼준다.
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static String[] dayList = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    public static String day;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            day = br.readLine().trim();
            for (int idx = 0; idx < 7; idx++) {
                if(day.equals(dayList[idx])) {
                    sb.append(7 - idx).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}