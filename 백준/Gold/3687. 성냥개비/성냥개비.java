import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String sticksToNum(int sticks) {
        switch (sticks) {
            case 2: return "1";
            case 3: return "7";
            case 4: return "4";
            case 5: return "2";
            case 6: return "0";
            case 7: return "8";
            default: return "";
        }
    }

    static boolean compare(String a, String b) {
        // 앞자리 0이면 무조건 패스
        if (a.charAt(0) == '0') return false;
        if (b.charAt(0) == '0') return true;

        if (a.length() != b.length()) return a.length() < b.length();
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) throws IOException {
        // 1. 최소값 초기화
        String[] minNum = new String[101];
        Arrays.fill(minNum, "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        minNum[2] = "1";
        minNum[3] = "7";
        minNum[4] = "4";
        minNum[5] = "2";
        minNum[6] = "6";
        minNum[7] = "8";

        for (int num = 8; num <= 100; num++) {
            for (int sticks = 2; sticks <= 7; sticks++) {
                if (num - sticks < 2) continue;

                String temp = minNum[num - sticks] + sticksToNum(sticks);

                if (compare(temp, minNum[num])) {
                    minNum[num] = temp;
                }
            }
        }

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int T = 0; T < testCase; T++) {
            int num = Integer.parseInt(br.readLine().trim());

            // 최소값 삽입
            sb.append(minNum[num]).append(" ");

            // 2. 최대값 구하기 -> 2, 3 으로만 구성
            StringBuilder max = new StringBuilder();
            if (num % 2 == 1) {
                int cnt = (num-3) >> 1;
                max.append(7);
                for (int repeat = 1; repeat <= cnt; repeat++) {
                    max.append(1);
                }
            } else {
                int cnt = num >> 1;
                for (int repeat = 0; repeat < cnt; repeat++) {
                    max.append(1);
                }
            }

            sb.append(max).append("\n");
        }
        System.out.println(sb);

    }

}